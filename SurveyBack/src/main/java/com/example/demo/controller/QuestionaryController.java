package com.example.demo.controller;

import com.example.demo.data_tables.mapping.DataTablesInput;
import com.example.demo.data_tables.mapping.DataTablesOutput;
import com.example.demo.models.Questionary;
import com.example.demo.models.QuestionaryResponse;
import com.example.demo.services.QuestionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.EmitterProcessor;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping(value = "questionary")

public class QuestionaryController {

    private EmitterProcessor<QuestionaryResponse> emitterProcessor;

    public QuestionaryController() {
        this.emitterProcessor = EmitterProcessor.create(false);
    }


    @Autowired
    private QuestionaryService service;

    @PostMapping(value = "/paginated")
    public DataTablesOutput<Questionary> getQuestionariesPaginated(@RequestBody DataTablesInput filter) {
        return service.getQuestionaryOfUser(filter);
    }
    @PostMapping(value = "/all")
    public Iterable<Questionary> getAllSurveys() {
        return service.getAllQuestionary();
    }
    @GetMapping(value = "/maxVoters")
    public int getMaxNumberVoters() {
        return service.maximumNumberOfVoters();
    }
    @GetMapping(value = "/byId/{id}")
    public Questionary getQuestionaryById(@PathVariable String id) {
        return service.getQuestionaryById(id);
    }

    @PostMapping()
    public Questionary addQuestionary(@RequestBody Questionary questionary) {
        Questionary que = service.postQuestionary(questionary);
        emitterProcessor.onNext(new QuestionaryResponse("save", que));
        return que;
    }

    @PostMapping("/vote/{id}")
    public Questionary vote(@PathVariable String id, @RequestBody Questionary votes) {
        return service.vote(id, votes.getQuestions());
    }

    @PostMapping(value = "/{id}")
    public Questionary editQuestionary(@PathVariable String id, @RequestBody Questionary questionary) throws Exception {
        Questionary que = service.editQuestionary(questionary, id);
        emitterProcessor.onNext(new QuestionaryResponse("edit", que));
        return que;
    }

    @DeleteMapping(value = "/del/{id}")
    public void deleteQuestionary(@PathVariable String id) {
        Questionary que = service.deleteQuestionary(id);
        emitterProcessor.onNext(new QuestionaryResponse("delete", que));
        ;
    }

    @GetMapping(value = "/getUpdates", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> getUpdates() {
        return Flux.merge(emitterProcessor
                        .map(cappedDataObject -> ServerSentEvent.builder(cappedDataObject).build()),
                Flux.interval(Duration.ZERO, Duration.ofSeconds(15)).map(aLong -> "ping")
                        .map(aLong -> ServerSentEvent.builder(aLong).build())
        );
    }
}

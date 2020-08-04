package com.example.demo.services;

import com.example.demo.data_tables.mapping.DataTablesInput;
import com.example.demo.data_tables.mapping.DataTablesOutput;
import com.example.demo.models.QuestionAndAnswers;
import com.example.demo.models.Questionary;
import com.example.demo.repositories.QuestionablyRepos;
import io.netty.util.internal.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class QuestionaryService {


    @Autowired
    private QuestionablyRepos repos;

    public Iterable<Questionary> getAllQuestionary() {
        return repos.findAll();
    }

    public DataTablesOutput<Questionary> getQuestionaryOfUser(DataTablesInput filter) {
        return repos.findAll(filter);
    }

    public Questionary getQuestionaryById(String id) {
        return repos.findById(id).orElse(null);
    }

    public Questionary vote(String id, QuestionAndAnswers[] votes) {
        Questionary result = repos.findById(id).orElse(null);
        assert result != null;
        result.setQuestions(votes);
        return repos.save(result);
    }

    public Questionary postQuestionary(Questionary questionary) {
        return repos.save(questionary);
    }


    public DataTablesOutput<Questionary> searchQuestionariesOnlyDescription(DataTablesInput filter) {
        return this.repos.findAll(filter);
    }

    public Questionary editQuestionary(Questionary questionary, String id) throws Exception {
        try {
            questionary.setId(id);
            return repos.save(questionary);
        } catch (Exception exception) {
            throw new Exception("error");
        }

    }

    public Questionary deleteQuestionary(String id) {
        Questionary questionary = repos.findById(id).orElse(null);
        repos.deleteById(id);
        return questionary;
    }

    public Integer maximumNumberOfVoters() {
        List<Questionary> result = StreamSupport
                .stream(this.repos.findAll().spliterator(), false)
                .collect(Collectors.toList());
        List<Integer> votes = result.stream().map(Questionary::getVoters).collect(Collectors.toList());
        int sum = votes.stream().mapToInt(Integer::intValue).sum();
        return sum;
    }
}

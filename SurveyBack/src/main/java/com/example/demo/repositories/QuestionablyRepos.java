package com.example.demo.repositories;

import com.example.demo.data_tables.repository.DataTablesRepository;
import com.example.demo.models.Questionary;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface QuestionablyRepos extends DataTablesRepository<Questionary, String> {
    public List<Questionary> findAllByUserId(String userId);
    public List<Questionary> findAllByDescriptionIsLikeAndUserId(String description, String userID);
    public List<Questionary> findAllByDescriptionIsLike(String description);
}

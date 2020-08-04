package com.hh.aws.service;

import com.hh.aws.model.PageData;
import com.hh.aws.model.Question;

import java.util.Map;

public interface QuestionService {
    PageData<Question> getQuestionList(Integer page, Integer size, Map<String, String> param, String sortName, String sortType);
    Question findQuestionById(long id);

    void save(Question question);
}

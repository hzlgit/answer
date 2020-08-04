package com.hh.aws.repository;

import com.hh.aws.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface QuestionRepository extends JpaRepository<Question, String>, JpaSpecificationExecutor<Question> {
}

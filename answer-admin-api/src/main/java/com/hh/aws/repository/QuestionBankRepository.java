package com.hh.aws.repository;

import com.hh.aws.model.QuestionBank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionBankRepository extends JpaRepository<QuestionBank, String> {
}


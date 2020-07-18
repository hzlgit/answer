package com.hh.aws.repository;

import com.hh.aws.model.QuestionRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRecordRepository extends JpaRepository<QuestionRecord, String> {
}

package com.hh.aws.repository;

import com.hh.aws.model.TestRecord;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TestRecordRepository extends JpaRepository<TestRecord, String> {
}

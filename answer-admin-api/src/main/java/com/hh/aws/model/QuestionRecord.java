package com.hh.aws.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class QuestionRecord {
    @Id
    @GeneratedValue
    private long id;

}

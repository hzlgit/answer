package com.hh.aws.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Question {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String qusType;
    @Column
    private String qusDesc;
    @Column
    private String title;
    @Column
    private int score;
    @Column
    private String choiceA;
    @Column
    private String choiceB;
    @Column
    private String choiceC;
    @Column
    private String choiceD;
    @Column
    private String choiceE;
    // 正确答案
    @Column
    private String qusAnswer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQusType() {
        return qusType;
    }

    public void setQusType(String qusType) {
        this.qusType = qusType;
    }

    public String getQusDesc() {
        return qusDesc;
    }

    public void setQusDesc(String qusDesc) {
        this.qusDesc = qusDesc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getChoiceA() {
        return choiceA;
    }

    public void setChoiceA(String choiceA) {
        this.choiceA = choiceA;
    }

    public String getChoiceB() {
        return choiceB;
    }

    public void setChoiceB(String choiceB) {
        this.choiceB = choiceB;
    }

    public String getChoiceC() {
        return choiceC;
    }

    public void setChoiceC(String choiceC) {
        this.choiceC = choiceC;
    }

    public String getChoiceD() {
        return choiceD;
    }

    public void setChoiceD(String choiceD) {
        this.choiceD = choiceD;
    }

    public String getChoiceE() {
        return choiceE;
    }

    public void setChoiceE(String choiceE) {
        this.choiceE = choiceE;
    }

    public String getQusAnswer() {
        return qusAnswer;
    }

    public void setQusAnswer(String qusAnswer) {
        this.qusAnswer = qusAnswer;
    }
}

package com.hh.aws.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AnswerRecord {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="qus_bank_id")
    private QuestionBank questionBank;
    @Column
    private String answer;
    @Column
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public QuestionBank getQuestionBank() {
        return questionBank;
    }

    public void setQuestionBank(QuestionBank questionBank) {
        this.questionBank = questionBank;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

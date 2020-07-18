package com.hh.aws.model;

import javax.persistence.*;
import java.util.Date;

/**
 * 测试记录
 */
@Entity
public class TestRecord {
    @Id
    @GeneratedValue
    private long id;

    @OneToOne
    @JoinColumn(name="answer_record_id")
    private AnswerRecord answerRecord;

    @Column
    private int userId;
    @Column
    private Date createTime;
    @Column
    private Date doneTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AnswerRecord getAnswerRecord() {
        return answerRecord;
    }

    public void setAnswerRecord(AnswerRecord answerRecord) {
        this.answerRecord = answerRecord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDoneTime() {
        return doneTime;
    }

    public void setDoneTime(Date doneTime) {
        this.doneTime = doneTime;
    }
}

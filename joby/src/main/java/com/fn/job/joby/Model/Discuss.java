package com.fn.job.joby.Model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "discuss")
public class Discuss {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobId")
    private Integer jobId;

    @Column(name = "message")
    private String message;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "date")
    private Date date;

    @Transient
    private User user;

    public Discuss(Integer id, Integer jobId, String message, Integer userId, Date date, User user) {
        this.id = id;
        this.jobId = jobId;
        this.message = message;
        this.userId = userId;
        this.date = date;
        this.user = user;
    }

    public Discuss() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

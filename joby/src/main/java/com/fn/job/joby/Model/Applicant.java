package com.fn.job.joby.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "applicant")
public class Applicant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "jobcode")
    private Integer jobcode;

    @Column(name = "appId")
    private Integer appId;

    @Column(name = "status")
    private String status;

    @Transient
    private Job job;

    @Transient
    private User user;
    public Applicant(Integer id, Integer jobcode, Integer appId, String status) {
        this.id = id;
        this.jobcode = jobcode;
        this.appId = appId;
        this.status = status;
    }

    public Applicant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJobcode() {
        return jobcode;
    }

    public void setJobcode(Integer jobcode) {
        this.jobcode = jobcode;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
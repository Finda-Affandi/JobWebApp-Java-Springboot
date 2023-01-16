package com.fn.job.joby.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "job")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobcode;

    @Column(name = "creatorId")
    private Integer creatorId;

    @Column(name = "position")
    private String position;

    @Column(name = "company")
    private String company;

    @Column(name = "location")
    private String location;

    @Column(name = "type")
    private String type;

    @Column(name = "minSalary")
    private Integer minSalary;

    @Column(name = "maxSalary")
    private Integer maxSalary;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Transient
    private String salary;

    public Job(Integer jobcode, Integer creatorId, String position, String company, String location, String type, Integer minSalary, Integer maxSalary, String description, String status) {
        this.jobcode = jobcode;
        this.creatorId = creatorId;
        this.position = position;
        this.company = company;
        this.location = location;
        this.type = type;
        this.minSalary = minSalary;
        this.maxSalary = maxSalary;
        this.description = description;
        this.status = status;
    }

    public Job() {
    }

    public Integer getJobcode() {
        return jobcode;
    }

    public void setJobcode(Integer jobcode) {
        this.jobcode = jobcode;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Integer minSalary) {
        this.minSalary = minSalary;
    }

    public Integer getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Integer maxSalary) {
        this.maxSalary = maxSalary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
}
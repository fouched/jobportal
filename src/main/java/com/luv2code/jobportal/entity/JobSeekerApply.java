package com.luv2code.jobportal.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "job_seeker_apply",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"user_id", "job"})
        }
)
public class JobSeekerApply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Below - No need for referencedColumnName - the default joins to the primary key
    //TODO really bad variable naming
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private JobSeekerProfile userId;

    //TODO really bad variable naming
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job")
    private JobPostActivity job;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date applyDate;

    private String coverLetter;

    public JobSeekerApply() {
    }

    public JobSeekerApply(Integer id, JobSeekerProfile userId, JobPostActivity job, Date applyDate, String coverLetter) {
        this.id = id;
        this.userId = userId;
        this.job = job;
        this.applyDate = applyDate;
        this.coverLetter = coverLetter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public JobSeekerProfile getUserId() {
        return userId;
    }

    public void setUserId(JobSeekerProfile userId) {
        this.userId = userId;
    }

    public JobPostActivity getJob() {
        return job;
    }

    public void setJob(JobPostActivity job) {
        this.job = job;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public String getCoverLetter() {
        return coverLetter;
    }

    public void setCoverLetter(String coverLetter) {
        this.coverLetter = coverLetter;
    }

    @Override
    public String toString() {
        return "JobSeekerApply{" +
                "id=" + id +
                ", userId=" + userId +
                ", job=" + job +
                ", applyDate=" + applyDate +
                ", coverLetter='" + coverLetter + '\'' +
                '}';
    }
}

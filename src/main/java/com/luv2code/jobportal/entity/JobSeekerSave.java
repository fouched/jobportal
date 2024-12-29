package com.luv2code.jobportal.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "job_seeker_save",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "job"})
        }
)
public class JobSeekerSave {

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

    public JobSeekerSave() {
    }

    public JobSeekerSave(Integer id, JobSeekerProfile userId, JobPostActivity jobPost) {
        this.id = id;
        this.userId = userId;
        this.job = jobPost;
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

    @Override
    public String toString() {
        return "JobSeekerSave{" +
                "id=" + id +
                ", userId=" + userId +
                ", job=" + job +
                '}';
    }
}

package com.luv2code.jobportal.entity;

import jakarta.persistence.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "job_post_activity")
public class JobPostActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobPostId;

    // Below No need for referencedColumnName - the default joins to the primary key
    // @JoinColumn(name = "posted_by_id", referencedColumnName = "userId")
    // see: https://codingnomads.com/spring-data-jpa-joincolumn-configuration
    // same applies to the others below
    @ManyToOne
    @JoinColumn(name = "posted_by_id")
    private Users postedById;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_location_id")
    private JobLocation jobLocationId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_company_id")
    private JobCompany jobCompanyId;

    @Length(max = 10000)
    private String descriptionOfJob;

    private String jobTitle;
    private String jobType;
    private String salary;
    private String remote;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date postedDate;

    @Transient
    private Boolean isActive;

    @Transient
    private Boolean isSaved;

    public JobPostActivity() {
    }

    public JobPostActivity(Integer jobPostId, Users postedById, JobLocation jobLocationId, JobCompany jobCompanyId, String descriptionOfJob, String jobTitle, String jobType, String salary, String remote, Date postedDate, Boolean isActive, Boolean isSaved) {
        this.jobPostId = jobPostId;
        this.postedById = postedById;
        this.jobLocationId = jobLocationId;
        this.jobCompanyId = jobCompanyId;
        this.descriptionOfJob = descriptionOfJob;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.salary = salary;
        this.remote = remote;
        this.postedDate = postedDate;
        this.isActive = isActive;
        this.isSaved = isSaved;
    }

    public Integer getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Integer jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Users getPostedById() {
        return postedById;
    }

    public void setPostedById(Users postedById) {
        this.postedById = postedById;
    }

    public JobLocation getJobLocationId() {
        return jobLocationId;
    }

    public void setJobLocationId(JobLocation jobLocationId) {
        this.jobLocationId = jobLocationId;
    }

    public JobCompany getJobCompanyId() {
        return jobCompanyId;
    }

    public void setJobCompanyId(JobCompany jobCompanyId) {
        this.jobCompanyId = jobCompanyId;
    }

    public String getDescriptionOfJob() {
        return descriptionOfJob;
    }

    public void setDescriptionOfJob(String descriptionOfJob) {
        this.descriptionOfJob = descriptionOfJob;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRemote() {
        return remote;
    }

    public void setRemote(String remote) {
        this.remote = remote;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsSaved() {
        return isSaved;
    }

    public void setIsSaved(Boolean saved) {
        isSaved = saved;
    }

    @Override
    public String toString() {
        return "JobPostActivity{" +
                "jobPostId=" + jobPostId +
                ", postedById=" + postedById +
                ", jobLocationId=" + jobLocationId +
                ", jobCompanyId=" + jobCompanyId +
                ", descriptionOfJob='" + descriptionOfJob + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobType='" + jobType + '\'' +
                ", salary='" + salary + '\'' +
                ", remote='" + remote + '\'' +
                ", postedDate=" + postedDate +
                ", isActive=" + isActive +
                ", isSaved=" + isSaved +
                '}';
    }
}

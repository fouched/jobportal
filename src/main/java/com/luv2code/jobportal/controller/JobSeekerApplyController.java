package com.luv2code.jobportal.controller;

import com.luv2code.jobportal.entity.*;
import com.luv2code.jobportal.services.*;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class JobSeekerApplyController {

    private final JobPostActivityService jobPostActivityService;
    private final UsersService usersService;
    private final JobSeekerApplyService jobSeekerApplyService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final RecruiterProfileService recruiterProfileService;
    private final JobSeekerProfileService jobSeekerProfileService;


    public JobSeekerApplyController(JobPostActivityService jobPostActivityService, UsersService usersService, JobSeekerApplyService jobSeekerApplyService, JobSeekerSaveService jobSeekerSaveService, RecruiterProfileService recruiterProfileService, JobSeekerProfileService jobSeekerProfileService) {
        this.jobPostActivityService = jobPostActivityService;
        this.usersService = usersService;
        this.jobSeekerApplyService = jobSeekerApplyService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.recruiterProfileService = recruiterProfileService;
        this.jobSeekerProfileService = jobSeekerProfileService;
    }

    @GetMapping("/job-details-apply/{id}")
    public String display(@PathVariable("id") int id, Model model) {
        JobPostActivity jobPost = jobPostActivityService.getOne(id);
        List<JobSeekerApply> jobSeekerApplyList = jobSeekerApplyService.getApplicationsByJobPost(jobPost);
        List<JobSeekerSave> jobSeekerSaveList = jobSeekerSaveService.getJobsByJobPost(jobPost);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))) {
                RecruiterProfile user = recruiterProfileService.getCurrentRecuiterProfile();
                if (user != null) {
                    model.addAttribute("applyList", jobSeekerApplyList);
                }
            } else {
                JobSeekerProfile user = jobSeekerProfileService.getCurrentJobSeekerProfile();
                if (user != null) {
                    boolean hasApplied = false;
                    boolean hasSaved = false;
                    for (JobSeekerApply jobSeekerApply : jobSeekerApplyList) {
                        if (Objects.equals(jobSeekerApply.getUserId().getUserAccountId(), user.getUserAccountId())) {
                            hasApplied = true;
                            break;
                        }
                    }
                    for (JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
                        if (Objects.equals(jobSeekerSave.getUserId().getUserAccountId(), user.getUserAccountId())) {
                            hasSaved = true;
                            break;
                        }
                    }

                    model.addAttribute("alreadyApplied", hasApplied);
                    model.addAttribute("alreadySaved", hasSaved);

                }
            }
        }

        model.addAttribute("applyJob", new JobSeekerApply());
        model.addAttribute("jobDetails", jobPost);
        model.addAttribute("user", usersService.getCurrentUserProfile());

        return "job-details";
    }

    @PostMapping("job-details/apply/{id}")
    public String apply(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersService.findByEmail(currentUsername);
            Optional<JobSeekerProfile> jobSeekerProfile = jobSeekerProfileService.getOne(users.getUserId());
            JobPostActivity jobPostActivity = jobPostActivityService.getOne(id);
            JobSeekerApply jobSeekerApply = new JobSeekerApply();

            if (jobSeekerProfile.isPresent() && jobPostActivity != null) {
                jobSeekerApply.setUserId(jobSeekerProfile.get());
                jobSeekerApply.setJob(jobPostActivity);
                jobSeekerApply.setApplyDate(new Date());
            } else {
                //TODO WTF? Rather do proper error handling
                throw new RuntimeException("User not found");
            }
            jobSeekerApplyService.addNew(jobSeekerApply);
        }

        return "redirect:/dashboard/";
    }
}

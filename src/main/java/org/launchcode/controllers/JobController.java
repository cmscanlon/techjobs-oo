package org.launchcode.controllers;

import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.launchcode.models.Job;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.launchcode.models.*;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view

        Job job = jobData.findById(id);
        model.addAttribute("job", job);

        return "job-detail";
//        jobData theJob = jobData.getById(id);
//        model.addAttribute("job", theJob);
//        model.addAttribute("name", jobData.findAll().get(id).getName());
//        model.addAttribute("employer", jobData.findAll().get(id).getEmployer());
//        model.addAttribute("location", jobData.findAll().get(id).getLocation());
//        model.addAttribute("positionType", jobData.findAll().get(id).getPositionType());
//        model.addAttribute("coreCompetency", jobData.findAll().get(id).getCoreCompetency());
//        return "job-detail";
//        use the cheese app
    }

//    @RequestMapping(value = "/job?{id}", method = RequestMethod.POST)
//    public String indexId(int id, String name, Employer employer, Location location, PositionType positionType,
//                        CoreCompetency coreCompetency) {
//
//        jobData theJob = jobData.getById(id);
//        theJob.getName(name);
//        theJob.getEmployer(employer);
//        theJob.getLocation(location);
//        theJob.getPositionType(positionType);
//        theJob.getCoreCompetency(coreCompetency);
//
//        return "redirect:";
//    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @Valid JobForm jobForm, Errors errors, RedirectAttributes attributes) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
//        use the lineup card, cheese exactly like everywhere else

        if (errors.hasErrors()) {
            return "new-job";
        }

        // create job properties from job form
        String jobName = jobForm.getName();
        Employer jobEmp = jobData.getEmployers().findById(jobForm.getEmployerId());
        Location jobLoc = jobData.getLocations().findById(jobForm.getLocationId());
        PositionType jobPos = jobData.getPositionTypes().findById(jobForm.getPositionTypeId());
        CoreCompetency jobComp = jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId());

        // construct new job
        Job newJob = new Job(jobName, jobEmp, jobLoc, jobPos, jobComp);

        // add job to jobData
        jobData.add(newJob);

        // add job id to redirect
        attributes.addAttribute("id", newJob.getId());

        // display new job detail
        return "redirect:/job";

//        if(errors.hasErrors()) {

//            return "new-job";
//
//        } else {
//            Job newJob = new Job();
//            newJob.setName(jobForm.getName());
//            newJob.setEmployer(jobData.getEmployers().findById(jobForm.getEmployerId()));
//            newJob.setLocation(jobData.getLocations().findById(jobForm.getLocationId()));
//            newJob.setPositionType(jobData.getPositionTypes().findById(jobForm.getPositionTypeId()));
//            newJob.setCoreCompetency(jobData.getCoreCompetencies().findById(jobForm.getCoreCompetencyId()));
//
//
//            //add newJobs to jobData
//            jobData.add(newJob);
//
//            model.addAttribute("name", newJob.getName());
//            model.addAttribute("employer", newJob.getEmployer());
//            model.addAttribute("location", newJob.getLocation());
//            model.addAttribute("position", newJob.getPositionType());
//            model.addAttribute("skill", newJob.getCoreCompetency());
//
//            return "job-detail";

        }

    }

//return "redirect:/job?id=" + newJob.getId();
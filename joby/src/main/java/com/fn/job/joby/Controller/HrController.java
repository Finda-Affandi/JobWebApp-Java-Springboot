package com.fn.job.joby.Controller;

import com.fn.job.joby.Model.Applicant;
import com.fn.job.joby.Model.Job;
import com.fn.job.joby.Model.UserLoginSingleton;
import com.fn.job.joby.Repositories.ApplicantRepository;
import com.fn.job.joby.Repositories.JobRepository;
import com.fn.job.joby.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "hr")
public class HrController {
    @Autowired
    private JobRepository _jobRepository;

    @Autowired
    private ApplicantRepository _applicantRepository;

    @Autowired
    private UserRepository _userRepository;

    private UserLoginSingleton usrSingleton = UserLoginSingleton.getInstance();

    @RequestMapping(path = { "/", "index" })
    public String index(Model model) {
        List<Job> jobList = _jobRepository.findAll();
        List<Job> hireList = _jobRepository.findAllByCompany("Formulatrix");
        model.addAttribute("jobList", jobList);
        model.addAttribute("hireList", hireList);
        return "hr/index";
    }

    @RequestMapping(path = "addJob")
    public String addJob(Model model) {
        if (usrSingleton.value.getId() == null || !usrSingleton.value.getRole().equals("HR")) {
            return "redirect:/auth/";
        }

        Job job = new Job();
        job.setCreatorId(usrSingleton.value.getId());
        job.setCompany(usrSingleton.value.getCompany());

        model.addAttribute("fullname", usrSingleton.value.getFullname());
        model.addAttribute("job", job);
        return "hr/addJob";
    }

    @RequestMapping(path = "doAddJob", method = RequestMethod.POST)
    public String doAddJob(Job job) {
        if (job.getJobcode() != null) {
            Job temp = _jobRepository.findById(job.getJobcode()).get();
            temp.setPosition(job.getPosition());
            temp.setType(job.getType());
            temp.setMinSalary(job.getMinSalary());
            temp.setMaxSalary(job.getMaxSalary());
            temp.setDescription(job.getDescription());
            temp.setStatus(job.getStatus());
            job = temp;
        }

        _jobRepository.save(job);
        return "redirect:/auth/";
    }

    @RequestMapping(path = "edit")
    public String editJob(Model model, @RequestParam(name = "jobCode") String jobCode) {
        Job job = _jobRepository.findById(Integer.valueOf(jobCode)).get();
        model.addAttribute("job", job);
        return "hr/editJob";
    }

    @RequestMapping(path = "delete")
    public String doDeleteJob(@RequestParam(name = "jobCode") String jobCode) {
        _jobRepository.deleteById(Integer.valueOf(jobCode));
        return "redirect:/hr/manageJob";
    }

    @RequestMapping(path = "detail")
    public String jobDetail(Model model, @RequestParam(name = "jobCode") String jobCode) {
        Job job = _jobRepository.findById(Integer.valueOf(jobCode)).get();
        model.addAttribute("job", job);
        return "hr/jobDetails";
    }

    @RequestMapping(path = "manage")
    public String manageJob(Model model) {
        List<Job> jobList = _jobRepository.findAllByCompany(usrSingleton.value.getCompany());
        model.addAttribute("jobList", jobList);
        return "hr/manageJob";
    }

    @RequestMapping(path = "applicant")
    public String applicant(Model model) {
        List<Applicant> applicant = _applicantRepository.findAll();
        model.addAttribute("applicant", applicant);
        return "user/hr";
    }
}

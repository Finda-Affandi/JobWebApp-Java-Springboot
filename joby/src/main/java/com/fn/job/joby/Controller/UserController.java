package com.fn.job.joby.Controller;

import com.fn.job.joby.Model.Applicant;
import com.fn.job.joby.Model.Job;
import com.fn.job.joby.Model.UserLoginSingleton;
import com.fn.job.joby.Repositories.ApplicantRepository;
import com.fn.job.joby.Repositories.DiscussRepository;
import com.fn.job.joby.Repositories.JobRepository;
import com.fn.job.joby.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(path = "user")
public class UserController {

    @Autowired
    private JobRepository _jobRepository;

    @Autowired
    private ApplicantRepository _applicantRepository;

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private DiscussRepository _discussRepository;

    private UserLoginSingleton usrSingleton = UserLoginSingleton.getInstance();

    @RequestMapping(path = {"/", "index"})
    public String index(Model model) {
        List<Job> jobList = _jobRepository.findAll();
        model.addAttribute("jobList", jobList);
        return "user/index";
    }

    @RequestMapping(path = "view")
    public String user(Model model) {
        if (usrSingleton.value.getId()==null) return "redirect:/auth/";
        model.addAttribute("myUser", usrSingleton.value);

        if (usrSingleton.value.getRole().equals("HR")){
            List<Job> jobs = _jobRepository.findAllByCompany(usrSingleton.value.getCompany());

            model.addAttribute("jobs", jobs);
            return "user/hr";
        } else {
            List<Applicant> result = new ArrayList<>();
            List<Applicant> temp = _applicantRepository.findAllByAppId(usrSingleton.value.getId());
            for (Applicant e : temp) {
                e.setJob(_jobRepository.findById(e.getJobcode()).get());
                result.add(e);
            }

            model.addAttribute("applicants", result);
            return "user/jobseeker";
        }
    }

    @RequestMapping(path = "detail")
    public String showDetail(@RequestParam Integer id, Model model) {
        if (usrSingleton.value.getId()==null || !usrSingleton.value.getRole().equals("HR")) return "redirect:/auth/";
        Job job = _jobRepository.findById(id).get();

        List<Applicant> aplicant = new ArrayList<>();
        List<Applicant> temp = _applicantRepository.findAllByJobcode(id);
        for (Applicant a : temp) {
            a.setUser(_userRepository.findById(a.getAppId()).get());
            aplicant.add(a);
        }

        model.addAttribute("myUser", usrSingleton.value);
        model.addAttribute("job", job);
        model.addAttribute("applicants", aplicant);

        return "user/detail";
    }

    @RequestMapping(path = "recruit")
    public String recruit(@RequestParam Integer id, @RequestParam String acceptance) {
        if (usrSingleton.value.getId()==null || !usrSingleton.value.getRole().equals("HR")) return "redirect:/auth/";
        Applicant temp = _applicantRepository.findById(id).get();

        if (acceptance.equals("Accepted")) {
            List<Applicant> applicants = new ArrayList<>();
            List<Applicant> tempApplicants = _applicantRepository.findAllByJobcode(id);
            for (Applicant a : tempApplicants) {
                a.setStatus("Rejected");
                applicants.add(a);
//                _applicantRepository.save(a);
            }

            _applicantRepository.saveAll(applicants);

            Job job = _jobRepository.findById(temp.getJobcode()).get();
            job.setStatus("Close");
            _jobRepository.save(job);
        }

        temp.setStatus(acceptance);
        _applicantRepository.save(temp);


        return "redirect:/user/view";
    }

    @RequestMapping(path = "apply")
    public String apply(@RequestParam Integer jobcode, Model model) {
        if (usrSingleton.value.getId()==null || !usrSingleton.value.getRole().equals("User")) return "redirect:/auth/";

        model.addAttribute("myUser", usrSingleton.value);
        model.addAttribute("job", _jobRepository.findById(jobcode).get());

        return "user/apply";
    }

    @RequestMapping(path = "doApply")
    public String doApply(@RequestParam Integer jobcode, Model model) {
        if (usrSingleton.value.getId()==null || !usrSingleton.value.getRole().equals("User")) return "redirect:/auth/";

        Applicant applicant = new Applicant();
        applicant.setJobcode(jobcode);
        applicant.setAppId(usrSingleton.value.getId());
        applicant.setStatus("Waiting");

        _applicantRepository.save(applicant);

        return "redirect:/auth/";
    }

}

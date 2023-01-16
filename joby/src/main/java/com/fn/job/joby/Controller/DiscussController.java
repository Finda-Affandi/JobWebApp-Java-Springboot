package com.fn.job.joby.Controller;

import com.fn.job.joby.Model.Discuss;
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
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(path = "discuss")
public class DiscussController {
    @Autowired
    private DiscussRepository _discussRepository;
    @Autowired
    private ApplicantRepository _aplicantRepository;
    @Autowired
    private UserRepository _userRepository;
    @Autowired
    private JobRepository _jobRepository;

    private UserLoginSingleton usrSingleton = UserLoginSingleton.getInstance();

    @RequestMapping(path = "show")
    public String showDiscuss(@RequestParam("id") Integer jobId, Model model){
        if (usrSingleton.value.getId()==null) return "redirect:/auth/";

        Job job = _jobRepository.findById(jobId).get();

        List<Discuss> discusses = new ArrayList<>();
        List<Discuss> tDiscusses = _discussRepository.findAllByJobId(jobId);
        for (Discuss e : tDiscusses) {
            e.setUser(_userRepository.findById(e.getUserId()).get());
            discusses.add(e);
        }

        Discuss discuss = new Discuss();
        discuss.setJobId(jobId);
        discuss.setUserId(usrSingleton.value.getId());

        model.addAttribute("myUser", usrSingleton.value);
        model.addAttribute("job", job);
        model.addAttribute("discusses", discusses);
        model.addAttribute("discuss", discuss);

        return "Discuss/discuss";
    }

    @RequestMapping(path = "add")
    public String addDiscuss(Discuss discuss){
        discuss.setDate(new Date());
        _discussRepository.save(discuss);

        return "redirect:/discuss/show?id=" + discuss.getJobId();
    }
}

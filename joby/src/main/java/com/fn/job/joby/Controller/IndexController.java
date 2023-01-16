package com.fn.job.joby.Controller;

import com.fn.job.joby.Model.UserLoginSingleton;
import com.fn.job.joby.Repositories.JobRepository;
import com.fn.job.joby.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "home")
public class IndexController {

    @Autowired
    private UserRepository _userRepository;

    @Autowired
    private JobRepository _jobRepository;

    private UserLoginSingleton usrSingleton = UserLoginSingleton.getInstance();

    @RequestMapping(path = {"/", "index"})
    public String index(Model model) {
        model.addAttribute("jobList", _jobRepository.findAllByStatus("Open"));
        model.addAttribute("myUser", usrSingleton.value);

        return "index";
    }

}

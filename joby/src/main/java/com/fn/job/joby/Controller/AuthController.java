package com.fn.job.joby.Controller;

import com.fn.job.joby.Model.Job;
import com.fn.job.joby.Model.User;
import com.fn.job.joby.Model.UserLoginSingleton;
import com.fn.job.joby.Repositories.JobRepository;
import com.fn.job.joby.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "auth")
public class AuthController {

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

    @GetMapping(path = "login")
    public String login(Model model) {
        return "auth/login";
    }

    @GetMapping(path = "doLogin")
    public String doLogin(Model model, @RequestParam("username") String username, @RequestParam("password") String password) {
        User user = _userRepository.findUserByUsernameAndPassword(username, password);
        if (user!=null) usrSingleton.value = user;
        return "redirect:/auth/";
    }

    @RequestMapping(path = "logout")
    public String logOut(){
        usrSingleton.value=new User();
        return "redirect:/auth/";
    }

    @RequestMapping(path = "hrSignup")
    public String hrSignup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/hrSignup";
    }

    @RequestMapping(path = "doHrSignup")
    public String doSignup(User user) {
        user.setRole("HR");
        _userRepository.save(user);
        return "redirect:/auth/login";
    }

    @RequestMapping(path = "userSignup")
    public String userSignup(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "auth/userSignup";
    }

    @RequestMapping(path = "doUserSignup")
    public String doUserSignup(User user) {
        user.setRole("User");
        _userRepository.save(user);
        return "redirect:/auth/login";
    }



}

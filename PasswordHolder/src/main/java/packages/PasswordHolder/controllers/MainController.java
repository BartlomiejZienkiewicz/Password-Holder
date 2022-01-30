package packages.PasswordHolder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import packages.PasswordHolder.components.mailer.SignUpMailer;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.UserRepository;
import packages.PasswordHolder.services.impl.InMemoryUserDetailsService;

import java.util.Optional;


@Controller
public class MainController {

    @Autowired
    InMemoryUserDetailsService detailsService;

    @Autowired
    SignUpMailer mailer;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/MainPage")
    private ModelAndView mainPage(ModelAndView mav){
        mav.setViewName("mainpage");
        return mav;
    }


    @RequestMapping("/AdminPanel")
    private ModelAndView adminPanel(ModelAndView mav){
        mav.setViewName("adminpanel");
        return mav;
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    private ModelAndView login (ModelAndView mav){
        mav.setViewName("login");
        return mav;
    }



}

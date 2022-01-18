package packages.PasswordHolder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import packages.PasswordHolder.entities.Password;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.PasswordRepository;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MainController {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordRepository passwordRepository;

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

package packages.PasswordHolder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MainController {



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

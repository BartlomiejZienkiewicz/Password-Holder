package packages.PasswordHolder.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

    @RequestMapping("")
    private ModelAndView mainPage(ModelAndView mav){
        mav.setViewName("MainPage");
        return mav;
    }
    @RequestMapping("/passwords")
    private ModelAndView passwords(ModelAndView mav){
        mav.setViewName("/passwords");
        return mav;
    }
    @RequestMapping("/AdminPanel")
    private ModelAndView adminPanel(ModelAndView mav){
        mav.setViewName("AdminPanel");
        return mav;
    }

}

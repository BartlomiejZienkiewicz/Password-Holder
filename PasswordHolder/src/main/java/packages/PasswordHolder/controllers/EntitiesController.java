package packages.PasswordHolder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import packages.PasswordHolder.entities.Password;
import packages.PasswordHolder.entities.User;
import packages.PasswordHolder.repositories.PasswordRepository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Controller
public class EntitiesController {

    private EntityManager entityManager;


    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordRepository passwordRepository;



    private String getNameOfLoggedUserUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = new String();

        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        String finalUsername = username;

        return finalUsername;

    }

    @RequestMapping("/passwords")
    private ModelAndView passwords(ModelAndView mav){

        List<Password> passwordList = new ArrayList<>();
        Password password2 = new Password();
        passwordRepository.findAll().forEach(passwordList::add);
        List<Password> passwordList2 = new ArrayList<>();




        passwordList2 =
                passwordList.stream().
                        filter(password -> password.getNameOfOwner().equals(getNameOfLoggedUserUser())).collect(Collectors.toList());
        mav.addObject("passwords",passwordList2);
        mav.setViewName("passwords");

        return mav;
    }

    @RequestMapping("/CreatePassword")
    public ModelAndView createPassword(ModelAndView mav){


        mav.addObject("password", new Password());
        mav.setViewName("createpassword");



        return mav;
    }

    @RequestMapping(value = "/passwords", method = RequestMethod.POST)
    public ModelAndView assignPassword(ModelAndView mav, Password password) {

        password.setNameOfOwner(getNameOfLoggedUserUser());



        passwordRepository.save(password);
        mav.setViewName("redirect:/passwords");
        return mav;
    }
}

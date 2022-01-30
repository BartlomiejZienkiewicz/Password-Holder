package packages.PasswordHolder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import packages.PasswordHolder.entities.Password;
import packages.PasswordHolder.repositories.PasswordRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class EntitiesController {

    @Autowired
    PasswordRepository passwordRepository;

    private Long idOfPassword;



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
    @RequestMapping(value = "/saveCreatedPassword")
    public ModelAndView assignPassword(ModelAndView mav,Password password) {

        if((password.getPasswordValue()=="")||(password.getDescription()=="")){
            mav.setViewName("createpassword");
            return mav;
        }

        password.setNameOfOwner(getNameOfLoggedUserUser());



        passwordRepository.save(password);
        mav.setViewName("redirect:/passwords");
        return mav;
    }


    @RequestMapping(value = "/passwords/delete/{passwordId}")
    public ModelAndView deletePassword(ModelAndView mav,@PathVariable("passwordId") Long id)
    {
        passwordRepository.deleteById(id);
        mav.setViewName("redirect:/passwords");
        return mav;
    }
    @RequestMapping(value = "/password")
    public ModelAndView editPassword(ModelAndView mav,@RequestParam("id") Long id){
        idOfPassword = id;
        Optional<Password> password = passwordRepository.findById(id);
        mav.addObject("password",password);
        mav.setViewName("password");
        return mav;
    }


    @RequestMapping(value = "/password", method = RequestMethod.POST)
    public ModelAndView saveEditedPassword(Password password,  ModelAndView mav) {
        if (password.getDescription() =="" || (password.getPasswordValue() == "")) {
            mav.setViewName("/password");
            return mav;
        } else {
            password.setPasswordId(idOfPassword);
            idOfPassword = null;
            password.setNameOfOwner(getNameOfLoggedUserUser());
            passwordRepository.save(password);
            mav.setViewName("redirect:/passwords");
            return mav;
        }
    }
    }



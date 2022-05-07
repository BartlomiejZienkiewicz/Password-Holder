package packages.PasswordHolder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import packages.PasswordHolder.component.mailer.SignUpMailer;
import packages.PasswordHolder.entity.User;
import packages.PasswordHolder.repository.UserRepository;
import packages.PasswordHolder.service.impl.InMemoryUserDetailsService;
import packages.PasswordHolder.service.impl.SignUpServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
public class SignUpController {


    User userToSignUp = null;

    private SignUpServiceImpl signUpService;

    private UserRepository userRepository;

    @Autowired
    InMemoryUserDetailsService detailsService;

    @Autowired
    SignUpMailer signUpMailer;




    @Autowired
    public SignUpController(SignUpServiceImpl signUpService, UserRepository userRepository) {
        this.signUpService = signUpService;
        this.userRepository = userRepository;

    }
    @GetMapping(value = "/sign_up")
    public ModelAndView signUp(ModelAndView mav){
        mav.setViewName("signup");
        return mav;

    }
    @PostMapping(value = "/sign_up")
    public ModelAndView signUpPost(ModelAndView mav, @RequestParam("username") String username,
                                   @RequestParam("password") String password, @RequestParam("email") String email)  {


        if((password == "") || (username == "" ) || (email == "" )){

            mav.setViewName("redirect:/sign_up");
            return mav;
        }
        if(username.equals("user1") ||username.equals("user2") |username.equals("admin")){
            mav.setViewName("redirect:/sign_up");
            return mav;
        }

        List<User> listOfAllUsers = (List<User>) userRepository.findAll();
        System.out.println(listOfAllUsers.size());
        for(User userOfList : listOfAllUsers){
            if(username.equals(userOfList.getUsername())){
                mav.setViewName("redirect:/sign_up");
                return mav;
            }
        }
        System.out.println(username + " registered");
        userToSignUp = new User(username, password,Arrays.asList("USER"),email);
        signUpService.signUpUser(new User(username, password,Arrays.asList("USER"),email));
        //userRepository.save(new User(username, password,Arrays.asList("USER"),email));


        mav.setViewName("redirect:/email_confirmation");


        return mav;

    }
    @RequestMapping("/confirm_email")
    public ModelAndView confirmEmail(ModelAndView mav,@RequestParam(name="token") String token){
        Optional<User> userFoundByConfirmationToken =  userRepository.findByConfirmationToken(token);

        if(userFoundByConfirmationToken.isPresent()){
            User user = userFoundByConfirmationToken.get();
            user.setEnabled(true);
            detailsService.addUser(userToSignUp);
            mav.setViewName("redirect:/email_confirmed");
            return mav;
        }else{
            mav.setViewName("token_does_not_exist");
            return mav;
        }
    }
    @RequestMapping("/email_confirmed")
    public ModelAndView emailConfirmed(ModelAndView mav){
        mav.setViewName("accountconfirmed");
        return mav;
    }
    @RequestMapping("/email_confirmation")
    public ModelAndView confirmYourEmail(ModelAndView mav){
        mav.setViewName("checkyouremail");
        return mav;

    }

}

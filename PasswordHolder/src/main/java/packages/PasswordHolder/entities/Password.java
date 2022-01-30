package packages.PasswordHolder.entities;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Passwords")
public class Password {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long passwordId;

    @NotNull
    @Size(min=1, message = "Password can not be empty")
    private String passwordValue;
    @NotNull
    @Size(min=1, message = "Description can not be empty")
    private String description;
    private Integer idOfUser;
    private String nameOfOwner;

    public String getPasswordValue() {
        return passwordValue;
    }



    public void setPasswordValue(String passwordValue) {
        this.passwordValue = passwordValue;
    }

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

    public Password() {
    }

    public Password(String nameOfOwner) {
        this.nameOfOwner = getNameOfLoggedUserUser();
    }

    public Integer getUserId(){
        return idOfUser;
    }

    public void setIdOfUser(Integer idOfUser) {
        this.idOfUser = idOfUser;
    }

    public Password(Integer userId, String password) {
        this.idOfUser = userId;
        this.passwordValue = password;

    }



    public String getPassword() {
        return passwordValue;
    }

    public void setPassword(String password) {
        this.passwordValue = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPasswordId() {
        return passwordId;
    }

    public void setPasswordId(Long passwordId) {
        this.passwordId = passwordId;
    }

    public Integer getIdOfUser() {
        return idOfUser;
    }

    public String getNameOfOwner() {
        return nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }
}

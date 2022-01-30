package packages.PasswordHolder.entities;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String password;
    private String email;
    @Transient
    private List<String> roles;

    private String confirmationToken;
    private boolean enabled;



    public User() {
    }



    public User(String name, String password, List<String> roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.enabled = false;
    }

    public User(String username, String password) {
        this.name = username;
        this.password = password;
        this.enabled = false;
    }
    public User(String username, String password, String email) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.enabled = false;
    }
    public User(String name, String password, List<String> roles, String email) {
        this.name = name;
        this.password = password;
        this.roles = roles;
        this.email = email;
        this.enabled = false;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return roles.stream().map(r -> new SimpleGrantedAuthority(r)).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}

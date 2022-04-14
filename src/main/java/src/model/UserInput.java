package src.model;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
public class UserInput {

    @NotNull(message = "User name must be provided")
    @NotEmpty(message = "User name can't be left blank")
    private String userName;

    @NotEmpty(message = "Email must be provided")
    @Email(message = "Please provide a valid email")
    private String emailAddress;

    @NotNull(message = "Password must be provided")
    private String password;

    @Valid
    private Set<Post> posts = new HashSet<>();

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Post> getPosts() {
        return posts;
    }

    public void setPosts(Set<Post> posts) {
        this.posts = posts;
    }
}

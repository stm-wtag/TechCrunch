package src.model.dto;

import org.hibernate.validator.constraints.Email;
import src.model.entities.Post;
import src.validator.EmailAddressConstraint;
import src.validator.UserNameConstraint;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
public class UserEdit {

    @UserNameConstraint(message = "Username can't be same as previous one")
    private String userName;

    @Email(message = "Please provide a valid email")
    @EmailAddressConstraint(message = "Email address can't be same as previous one")
    private String emailAddress;

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

    public String getPassword() {
        return password;
    }




}


package src.model.dto;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import src.model.entities.Post;
import src.validator.EmailAddressConstraint;
import src.validator.UserNameConstraint;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;
public class UserInput {

    @NotBlank(message = "User name can't be left blank")
    @UserNameConstraint(message = "Duplicate usernames are not allowed")
    private String userName;

    @NotBlank(message = "Email must be provided")
    @Email(message = "Please provide a valid email")
    @EmailAddressConstraint(message = "Email address must be unique")
    private String emailAddress;

    @NotBlank(message = "Password must be provided")
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

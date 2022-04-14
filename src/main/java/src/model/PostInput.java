package src.model;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.Valid;

public class PostInput {

    @NotEmpty(message = "title can't be left blank")
    @NotBlank(message = "title must be provided")
    private String title;

    @NotBlank(message = "Description must be provided")
    @NotEmpty(message = "Description can't be left blank")
    private String description;

    @Valid
    private User user;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

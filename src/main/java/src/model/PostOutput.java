package src.model;

import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PostOutput {


    private int id;

    @NotNull(message = "Need to provide a title")
    @NotBlank(message = "Title must be provided")
    private String title;

    @NotNull(message = "Need to provide a title")
    @NotBlank(message = "Title must be provided")
    private String description;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

}




package src.model.objectMapper;

import src.model.dto.*;
import src.model.entities.Post;
import src.model.entities.User;

public class FactoryObjectMapper {

    public static User convertUserInputToModel(UserInput userInput){
        User user = new User();
        user.setUserName(userInput.getUserName());
        user.setEmailAddress(userInput.getEmailAddress());
        user.setPassword(userInput.getPassword());
        return user;
    }

    public static UserOutput convertUserEntityToUserOutput(User user){
        UserOutput userOutput = new UserOutput();
        userOutput.setId(user.getId());
        userOutput.setUserName(user.getUserName());
        userOutput.setEmailAddress(user.getEmailAddress());
        return userOutput;
    }

    public static Post convertPostInputToModel(PostInput postInput){
        Post post = new Post();
        post.setTitle(postInput.getTitle());
        post.setDescription(postInput.getDescription());
        return post;
    }

    public static PostOutput convertPostModelToPostOutput(Post post){
        PostOutput postOutput = new PostOutput();
        postOutput.setId(post.getId());
        postOutput.setTitle(post.getTitle());
        postOutput.setDescription(post.getDescription());
        return postOutput;
    }

    public static User convertUserEditToUserModel(UserEdit userEdit){
        User user = new User();
        user.setUserName(userEdit.getUserName());
        user.setEmailAddress(userEdit.getEmailAddress());
        user.setPassword(userEdit.getPassword());
        return user;
    }

}

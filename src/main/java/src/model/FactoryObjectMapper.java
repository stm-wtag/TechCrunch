package src.model;

import javafx.geometry.Pos;

public class FactoryObjectMapper {

//    public static UserInput convertUserInputToObject(User user){
//        UserInput userInput = new UserInput();
//        userInput.setId(user.getId());
//        userInput.setUserName(user.getUserName());
//        userInput.setEmailAddress(user.getEmailAddress());
//        userInput.setPassword(user.getPassword());
//        return userInput;
//    }

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

}

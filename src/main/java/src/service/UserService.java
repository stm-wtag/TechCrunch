package src.service;

import src.model.User;
import src.model.UserInput;
import src.model.UserOutput;

public interface UserService {

    UserOutput saveUser(UserInput userInput);

    UserOutput getUserById(int userId);

    void deleteUserById(int userId);

    UserOutput updateUserById(User user, int id);

    User getUserInstanceById(int userId);
}

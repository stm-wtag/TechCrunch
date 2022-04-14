package src.service;

import src.model.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(int userId);

    void deleteUserById(int userId);

    User updateUserById(User user, int id);

}

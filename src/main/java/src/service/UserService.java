package src.service;

import src.model.entities.User;

public interface UserService {

    User saveUser(User user);

    User getUserById(int userId);

    void deleteUserById(int userId);

    User updateUserById(User user, int id);

    void saveTokenToBlackList();
//    String createJwt(int userId);

}

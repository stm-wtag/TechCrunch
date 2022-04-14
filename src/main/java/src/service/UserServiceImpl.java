package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import src.model.FactoryObjectMapper;
import src.model.User;
import src.model.UserInput;
import src.model.UserOutput;
import src.repository.UserRepository;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public UserOutput saveUser(UserInput userInput) {
        User user = FactoryObjectMapper.convertUserInputToModel(userInput);
        return userRepository.save(user);

    }

    @Transactional
    @Override
    public UserOutput getUserById(int userId) {
        User user = userRepository.findUser(userId);
        if (user != null){
            UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(user);
            return userOutput;
        }
        else {
            throw new NoSuchElementException("There is no such user");
        }
    }

    @Override
    public User getUserInstanceById(int userId){
        User user = userRepository.findUser(userId);
        if (user != null) return user;
        else throw new NoSuchElementException("There is no such user");
    }

    @Transactional
    @Override
    public void deleteUserById(int userId) {
        boolean isDeleted =  userRepository.delete(userId);
        if(!isDeleted) throw new NoSuchElementException("There is no such user");
    }

    @Transactional
    @Override
    public UserOutput updateUserById(User user, int id) {
        User userToUpdate = getUserInstanceById(id);
        if (user.getUserName() != null) userToUpdate.setUserName(user.getUserName());
        if (user.getEmailAddress() != null) userToUpdate.setEmailAddress(user.getEmailAddress());
        if (user.getPassword() != null) userToUpdate.setPassword(user.getPassword());

        userRepository.save(userToUpdate);
        return FactoryObjectMapper.convertUserEntityToUserOutput(userToUpdate);

    }
}
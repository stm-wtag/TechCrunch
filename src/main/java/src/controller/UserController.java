package src.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import src.exceptions.BadRequestException;
import src.model.FactoryObjectMapper;
import src.model.User;
import src.model.UserInput;
import src.model.UserOutput;
import src.service.UserService;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserOutput> createUser(@Valid @RequestBody UserInput userInput, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        User user = FactoryObjectMapper.convertUserInputToModel(userInput);
        User savedUser = userService.saveUser(user);
        UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(savedUser);
        return new ResponseEntity<>(userOutput, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserOutput> getUser(@PathVariable("userId") int userId) {
        User user= userService.getUserById(userId);
        UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(user);
        return ResponseEntity.ok(userOutput);

    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{userId}")
    public ResponseEntity<UserOutput> updateUser(@RequestBody User user, @PathVariable("userId") int userId) {
        User savedUser = userService.updateUserById(user, userId);
        UserOutput userOutput = FactoryObjectMapper.convertUserEntityToUserOutput(savedUser);
        return ResponseEntity.ok(userOutput);
    }


}


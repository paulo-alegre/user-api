package com.cognizant.sg.userapi.service;

import com.cognizant.sg.userapi.entity.User;
import com.cognizant.sg.userapi.exception.UserNotFoundException;
import com.cognizant.sg.userapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * User Service Implementation
 * @author Adrian Alegre
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    /**
     *  Constructor Injection
     * @param userRepository
     */
    UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * User find by Id
     * @throws Exception when Id is not found
     *
     * @param id
     * @return User
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Id-" + id + " Not Found"));
    }

    /**
     * Find all users that are within the range given
     * @throws Exception when the range given are not found in the database
     *
     * @param fromSalary
     * @param toSalary
     * @return List<User>
     */
    @Override
    public List<User> findBySalary(BigDecimal fromSalary, BigDecimal toSalary) {
        List<User> users = userRepository.findBySalary(fromSalary, toSalary);

        if(users.isEmpty())
            throw new UserNotFoundException("No users found in this salary range");

        return users;
    }

    /**
     * Finds all user saved in the database.
     * @throws Exception when users were not read in the csv file
     *
     * @return List<User>
     */
    @Override
    public List<User> findAll() {
       List<User> users = userRepository.findAll();

        if(users.isEmpty())
            throw new UserNotFoundException("Users not found.");

        return users;
    }
}

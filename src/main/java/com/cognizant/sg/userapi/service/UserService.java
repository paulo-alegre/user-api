package com.cognizant.sg.userapi.service;

import com.cognizant.sg.userapi.entity.User;

import java.math.BigDecimal;
import java.util.List;

/**
 * User Service
 *
 * @author Adrian Alegre
 */
public interface UserService {

    User findById(final Long id);
    List<User> findBySalary(final BigDecimal fromSalary, final BigDecimal toSalary);
    List<User> findAll();
}

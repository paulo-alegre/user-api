package com.cognizant.sg.userapi.service;

import com.cognizant.sg.userapi.entity.User;
import com.cognizant.sg.userapi.exception.UserNotFoundException;
import com.cognizant.sg.userapi.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;


/**
 * Test class for (@link UserServiceImpl)
 *
 * @author Adrian Alegre
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    List<User> users = null;

    @BeforeEach
    void setUp() throws Exception {
        users = Arrays.asList(
                new User(1L, "Kyrie", BigDecimal.valueOf(4000)),
                new User(2L, "Steph", BigDecimal.valueOf(1500)),
                new User(3L, "Dame", BigDecimal.valueOf(2800)),
                new User(4L, "Irvin", BigDecimal.valueOf(3400)),
                new User(5L, "Kevin", BigDecimal.valueOf(5500))
        );
    }

    @Test
    public void testGetUserById() {
        User user = new User("Kyrie", BigDecimal.valueOf(2110));

        given(userRepository.findById(2L)).willReturn(Optional.of(user));
        User expectedUser = userService.findById(2L);

        assertNotNull(expectedUser);
        assertEquals(expectedUser.getName(), user.getName());
    }

    @Test
    public void testGetUserById_UserNotFoundException() {
        assertThatThrownBy(() -> userService.findById(2L))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void testUsersFindBySalary() {

        given(userRepository.findBySalary(BigDecimal.valueOf(0),BigDecimal.valueOf(4000))).willReturn(users);

        List<User> expected = userService.findBySalary(BigDecimal.valueOf(0),BigDecimal.valueOf(4000));

        assertEquals(expected, users);
    }

    @Test
    public void testUsersFindBySalary_UserNotFoundException() {
        assertThatThrownBy(() -> userService.findBySalary(BigDecimal.valueOf(5000), BigDecimal.valueOf(7000)))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void testFindAllUsers() {
        given(userRepository.findAll()).willReturn(users);

        List<User> expected = userService.findAll();

        assertEquals(expected, users);
    }

    @Test
    public void testFindAllUsers_UserNotFoundException() {
        assertThatThrownBy(() -> userService.findAll())
                .isInstanceOf(UserNotFoundException.class);
    }
}

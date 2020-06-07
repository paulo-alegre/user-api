package com.cognizant.sg.userapi.controller;

import com.cognizant.sg.userapi.entity.User;
import com.cognizant.sg.userapi.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test class for {@link UserController}
 *  *  *
 * @author Adrian Alegre
 */
@ExtendWith(SpringExtension.class)
@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    List<User> users = null;

    @BeforeEach
    void setUp() {
        users = Arrays.asList(
                new User(1L, "Kyrie", BigDecimal.valueOf(4000)),
                new User(2L, "Steph", BigDecimal.valueOf(1500)),
                new User(3L, "Dame", BigDecimal.valueOf(2800)),
                new User(4L, "Irvin", BigDecimal.valueOf(3400)),
                new User(5L, "Kevin", BigDecimal.valueOf(5500))
        );
    }

    @Test
    public void testGetAllUsers() throws Exception {
        doReturn(users).when(userService).findAll();

        this.mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.size()", is(5)))
                .andExpect(jsonPath("$.results[3].name", is("Irvin")))
                .andExpect(jsonPath("$.results[3].salary", is(3400)));
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = new User(2L, "Kyrie", BigDecimal.valueOf(2110));

        doReturn(user).when(userService).findById(2L);

        this.mockMvc.perform(get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(user.getName())));
    }

    @Test
    public void testGetUsersBySalary() throws Exception {
        doReturn(users).when(userService).findBySalary(BigDecimal.valueOf(0), BigDecimal.valueOf(4000));

        this.mockMvc.perform(get("/users/salary?from=0&to=4000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.results.size()", is(5)))
                .andExpect(jsonPath("$.results[2].name", is("Dame")))
                .andExpect(jsonPath("$.results[2].salary", is(2800)));
    }
}

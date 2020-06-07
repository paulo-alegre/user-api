package com.cognizant.sg.userapi.repository;

import com.cognizant.sg.userapi.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link UserRepository}
 *
 * @author Adrian Alegre
 */
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    public void setUp(){
        List<User> users = Arrays.asList(
                new User("Kyrie", BigDecimal.valueOf(4000)),
                new User("Steph", BigDecimal.valueOf(1500)),
                new User("Dame", BigDecimal.valueOf(2800)),
                new User("Irvin", BigDecimal.valueOf(3400)),
                new User("Kevin", BigDecimal.valueOf(5500))
        );

        users.forEach(user -> testEntityManager.persist(user));
    }

    @Test
    public void testUserWFindById() {
        User user = userRepository.findById(1L).get();

        assertThat(user.getName()).isEqualTo("Kyrie");
    }

    @Test
    public void testUsersFindBySalaryWithinSalaryRange() {
        List<User> users = userRepository.findBySalary(BigDecimal.valueOf(0), BigDecimal.valueOf(4000));

        assertThat(users).hasSize(4);
    }

    @Test
    public void testUserFindAllSize() {
        List<User> users = userRepository.findAll();

        assertThat(users).hasSize(5);
    }
}

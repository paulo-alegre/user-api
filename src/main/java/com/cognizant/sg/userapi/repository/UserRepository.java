package com.cognizant.sg.userapi.repository;

import com.cognizant.sg.userapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * User Repository
 * JPA Repository provides CRUD executions in database
 *
 * @author Adrian Alegre
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u from User u WHERE u.salary BETWEEN :fromSalary AND :toSalary")
    List<User> findBySalary(final  @Param("fromSalary") BigDecimal fromSalary, final  @Param("toSalary") BigDecimal toSalary);
}

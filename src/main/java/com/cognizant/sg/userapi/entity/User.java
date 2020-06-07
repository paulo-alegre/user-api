package com.cognizant.sg.userapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * POJO Class that is mapped to database table.
 *
 * @author Adrian Alegre
 */

@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "salary")
    private BigDecimal salary;

    public User(String name, BigDecimal salary) {
        this.name = name;
        this.salary = salary;
    }
}

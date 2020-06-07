package com.cognizant.sg.userapi.model;

import com.cognizant.sg.userapi.entity.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import java.util.List;

/**
 * User Response Model
 *
 * @author Adrian Alegre
 */
@Getter
@Setter
@AllArgsConstructor
public class UserResponse {

    @JsonProperty("results")
    @Valid
    List<User> results = null;
}

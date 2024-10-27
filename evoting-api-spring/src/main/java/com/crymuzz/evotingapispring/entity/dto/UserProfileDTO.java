package com.crymuzz.evotingapispring.entity.dto;

import com.crymuzz.evotingapispring.entity.enums.RoleEnum;
import lombok.Data;

@Data
public class UserProfileDTO {
    private Long id;
    private String email;
    private RoleEnum role;
    private String firstName;
    private String lastName;
}

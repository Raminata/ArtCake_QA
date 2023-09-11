package com.ArtCake.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GettingProfileDto {

    private Integer userId;
    private String firstName;
    private String lastName;
    private String email;
    private String town;
    private String zipCode;
    private String street;
    private Integer houseNumber;
    private String phoneNumber;
    private String role;
    private String state;

}

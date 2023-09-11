package com.ArtCake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserDto {

    private String town;

    private String zipCode;

    private String street;

    private Integer houseNumber;

    private String phoneNumber;

    private String state;

    private String role;
}

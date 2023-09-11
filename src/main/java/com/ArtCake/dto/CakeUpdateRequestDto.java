package com.ArtCake.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CakeUpdateRequestDto {
    private String name;
    private String ingredients;
    private Double price;
    private String state;

}

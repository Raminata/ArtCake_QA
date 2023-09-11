package com.ArtCake.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CakeResponseDto {
    private String id;
    private String name;
    private String ingredients;
    private Integer price;
    private String category;
    private String state;
}

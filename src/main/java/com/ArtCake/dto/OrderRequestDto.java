package com.ArtCake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderRequestDto {

    private Integer count;
    private String deadline;
    private String clientWishes;


}

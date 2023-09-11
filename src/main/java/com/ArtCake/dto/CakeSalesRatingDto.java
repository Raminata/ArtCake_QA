package com.ArtCake.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class CakeSalesRatingDto {

    private Integer cakeId;
    private Integer numberOfSales;
    private Integer totalQuantity;
}

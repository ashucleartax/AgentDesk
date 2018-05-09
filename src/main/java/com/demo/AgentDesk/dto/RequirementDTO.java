package com.demo.AgentDesk.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequirementDTO {

    @NotNull
    private Double latitude;

    @NotNull
    private Double longitude;

    //Custome distance could be input
    private Double distance;

    private BigDecimal minPrice;

    private BigDecimal maxPrice;

    private Integer minNumberOfBedrooms;

    private Integer maxNumberOfBedrooms;

    private Integer minNumberOfBathrooms;

    private Integer maxNumberOfBathrooms;

    private Integer page;
}

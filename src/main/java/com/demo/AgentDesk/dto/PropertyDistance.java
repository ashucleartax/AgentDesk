package com.demo.AgentDesk.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PropertyDistance {

    private long id;

    private String externalId;

    private double latitude;

    private double longitude;

    private BigDecimal price;

    private int numberOfBedrooms;

    private int numberOfBathrooms;

    private double distance;

}

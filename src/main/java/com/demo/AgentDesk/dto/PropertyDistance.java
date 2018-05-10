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
@NoArgsConstructor
@Builder
@ToString
public class PropertyDistance {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String externalId;

    @Getter @Setter
    private double latitude;

    @Getter @Setter
    private double longitude;

    @Getter @Setter
    private BigDecimal price;

    @Getter @Setter
    private int numberOfBedrooms;

    @Getter @Setter
    private int numberOfBathrooms;

    @Getter @Setter
    private double distance;

    //long, java.lang.String, double, double, java.math.BigDecimal, int, int, double
    public PropertyDistance(long id, String externalId, double latitude, double longitude,
                            BigDecimal price,  int numberOfBedrooms, int numberOfBathrooms, double distance){
        this.id = id;
        this.externalId = externalId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.price = price;
        this.numberOfBathrooms = numberOfBathrooms;
        this.numberOfBedrooms = numberOfBedrooms;
        this.distance = distance;
    }

}

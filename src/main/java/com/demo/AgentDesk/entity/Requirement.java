package com.demo.AgentDesk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
/*
@Entity
@Table(name = "property")
*/
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Requirement {

/*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
*/
    @JsonIgnore
    private long id;

    @NotNull
    @Getter
    @Setter
    private String externalId;

    @NotNull
    @Getter
    @Setter
    private double latitude;

    @NotNull
    @Getter
    @Setter
    private double longitude;

    @NotNull
    @Getter
    @Setter
    private BigDecimal minPrice;

    @NotNull
    @Getter
    @Setter
    private BigDecimal maxPrice;

    @NotNull
    @Getter
    @Setter
    private int minNumberOfBedrooms;

    @NotNull
    @Getter
    @Setter
    private int maxNumberOfBedrooms;

    @NotNull
    @Getter
    @Setter
    private int minNumberOfBathrooms;

    @NotNull
    @Getter
    @Setter
    private int maxNumberOfBathrooms;

}

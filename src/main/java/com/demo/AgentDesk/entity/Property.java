package com.demo.AgentDesk.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by ashutoshpandey on 08/05/18.
 */

@Entity
//Source is https://www.movable-type.co.uk/scripts/latlong-db.html
@org.hibernate.annotations.NamedQueries({
        @org.hibernate.annotations.NamedQuery(name = "Property.listAllPropertyInRadius",
                query="SELECT new com.demo.AgentDesk.dto.PropertyDistance( p.id, " +
                        "p.externalId, p.latitude, p.longitude, p.price, p.numberOfBedrooms, p.numberOfBathrooms, " +
                        "acos(sin(:lat)*sin(radians(p.latitude)) + cos(:lat)*cos(radians(p.latitude))*cos(radians(p.longitude)-:lon)) * :earthRadius As distance )" +
                        "FROM Property p " +
                        "WHERE  p.latitude BETWEEN :minLat and :maxLat " +
                        "AND p.longitude BETWEEN :minLon And :maxLon " +
                        "AND acos(sin(:lat)*sin(radians(p.latitude)) + cos(:lat)*cos(radians(p.latitude))*cos(radians(p.longitude)-:lon)) * :earthRadius < :radius " +
                        "ORDER by distance")
}
)
@Table(name = "property")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    private BigDecimal price;

    @NotNull
    @Getter
    @Setter
    private int numberOfBedrooms;

    @NotNull
    @Getter
    @Setter
    private int numberOfBathrooms;

    @CreationTimestamp
    @Getter
    private Timestamp createdAt;

    @UpdateTimestamp
    @Getter @Setter
    private Timestamp updatedAt;


}

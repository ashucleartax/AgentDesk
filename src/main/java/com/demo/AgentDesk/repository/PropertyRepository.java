package com.demo.AgentDesk.repository;

import com.demo.AgentDesk.dto.PropertyDistance;
import com.demo.AgentDesk.entity.Property;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
@Repository
@Transactional
public interface PropertyRepository extends JpaRepository<Property, Long> {

    Property findByExternalId(String externalId);

    @Query("select p from Property p where " +
            "latitude >= :minLat and latitude >= :maxLat " +
            "and longitude >= :minLong and logitude <= :maxLong " +
            "and price >= :minPrice and price <:maxPrice and " +
            "numberOfBedrooms >= :minBedroom and numberOfBedrooms <= :maxBedroom " +
            "and numberOfBathrooms >= :minBathroom and numberOfBathrooms <= :maxBathroom")
    List<Property> findAllCustom(double minLat, double maxLat, double minLong, double maxLong, double minPrice,
                                 double maxPrice, int minBedroom, int maxBedroom, int minBathroom, int maxBathroom,
                                 Pageable pageable);

    @Query("select p from Property p where " +
            "p.latitude between :minLat and :maxLat " +
            "and p.longitude between :minLong and :maxLong ")
    List<Property> findAllCustom(double minLat, double maxLat, double minLong, double maxLong);


    //https://www.movable-type.co.uk/scripts/latlong-db.html
    List<PropertyDistance> listAllPropertyInRadius(double lat, double lon,
                                                   double minLat, double maxLat, double minLon, double maxLon,
                                                   double radius, double earthRadius);

}

package com.demo.AgentDesk.service;

import com.demo.AgentDesk.dto.PropertyDistance;
import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.entity.Requirement;
import com.demo.AgentDesk.repository.PropertyRepository;
import com.demo.AgentDesk.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.util.List;

import static com.demo.AgentDesk.util.Constants.*;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
@Service
public class PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    public Iterable<Property> findAll(){
        return propertyRepository.findAll();
    }

    public Property find(String propertyId){
        return propertyRepository.findByExternalId(propertyId);
    }

    public Property create(Property property){
        return propertyRepository.save(property);
    }

    public Property update(String propertyId, Property property){
        Property currentProperty = propertyRepository.findByExternalId(propertyId);
        //Updation Logic
        return propertyRepository.save(currentProperty);
    }

    public void delete(String propertyId){
        Property property = propertyRepository.findByExternalId(propertyId);
        propertyRepository.delete(property);
    }

    public List<PropertyDistance> filterPropertyByRequirement(RequirementDTO requirementDTO){
        return findAllPropertiesWithinRange(requirementDTO.getLatitude(), requirementDTO.getLongitude(), DEFAULT_DISTANCE);
    }

    /**
     *
     * @param latitude in degrees
     * @param longitude in degrees
     * @param radius in kilometers
     * @return
     */
    private List<PropertyDistance> findAllPropertiesWithinRange(double latitude, double longitude, double radius) {

        double maxLat = latitude + Math.toDegrees( radius/ EARTH_RADIUS);
        double minLat = latitude - Math.toDegrees( radius/ EARTH_RADIUS);
        double maxLon = longitude + Math.toDegrees( Math.asin(radius/ EARTH_RADIUS) /Math.cos(Math.toRadians(latitude)));
        double minLon = longitude - Math.toDegrees( Math.asin(radius/ EARTH_RADIUS) /Math.cos(Math.toRadians(latitude)));

        return propertyRepository.listAllPropertyInRadius(latitude, longitude, minLat, maxLat, minLon, maxLon, radius, EARTH_RADIUS );
    }
}

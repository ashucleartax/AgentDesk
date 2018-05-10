package com.demo.AgentDesk.service;

import com.demo.AgentDesk.dto.PropertyDistance;
import com.demo.AgentDesk.dto.PropertyDistanceWrapper;
import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.repository.PropertyRepository;
import com.demo.AgentDesk.util.Constants;
import com.demo.AgentDesk.util.RangeFilterStates;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.websocket.server.PathParam;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.demo.AgentDesk.util.Constants.*;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
@Slf4j
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
        log.info("Saving new Property");
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

/*
    All matches above 40% can only be considered useful.
    If thedistance is within 2 miles, distance contribution for the match percentage is fully 30%
    If budget is within min and max budget, budget contribution for the match percentage is full 30%. If min or max is not given, +/- 10% budget is a full 30% match.
    If bedroom and bathroom fall between min and max, each will contribute full 20%. If min or max is not given, match percentage varies according to the value.
*/
    public List<PropertyDistanceWrapper> filterPropertyByRequirement(RequirementDTO requirementDTO){
        requirementDTO.validate();
        List<PropertyDistance> propertyDistances = findAllPropertiesWithinRange(requirementDTO);

        List<PropertyDistanceWrapper> propertyDistanceWrappers = new ArrayList<>();

        for(PropertyDistance propertyDistance : propertyDistances) {
            int matchPercentage = getMatchPercentage(requirementDTO, propertyDistance);
            if(matchPercentage >= 40) {
                propertyDistanceWrappers.add(new PropertyDistanceWrapper(matchPercentage, propertyDistance));
            }
        }
        Collections.sort(propertyDistanceWrappers);

        return propertyDistanceWrappers;
    }

    /**
     *
     * @param requirementDTO
     * @return
     */
    private List<PropertyDistance> findAllPropertiesWithinRange(RequirementDTO requirementDTO) {

        double latitude = requirementDTO.getLatitude();
        double longitude = requirementDTO.getLongitude();
        double radius = requirementDTO.getDistance();

        double maxLat = latitude + Math.toDegrees( radius/ EARTH_RADIUS);
        double minLat = latitude - Math.toDegrees( radius/ EARTH_RADIUS);
        double maxLon = longitude + Math.toDegrees( Math.asin(radius/ EARTH_RADIUS) /Math.cos(Math.toRadians(latitude)));
        double minLon = longitude - Math.toDegrees( Math.asin(radius/ EARTH_RADIUS) /Math.cos(Math.toRadians(latitude)));


        return propertyRepository.listAllPropertyInRadius(latitude, longitude,
                minLat, maxLat, minLon, maxLon, radius, EARTH_RADIUS);

/*
        return propertyRepository.listAllPropertyInRadiusAndConditions(latitude, longitude,
                minLat, maxLat, minLon, maxLon, radius, EARTH_RADIUS,
                requirementDTO.getMinPrice().doubleValue(), requirementDTO.getMaxPrice().doubleValue(),
                requirementDTO.getMinNumberOfBedrooms(), requirementDTO.getMaxNumberOfBedrooms(),
                requirementDTO.getMinNumberOfBathrooms(), requirementDTO.getMaxNumberOfBathrooms());
*/
    }

    /**
     *  If the distance is within 2 kilometer, distance contribution for the match percentage is fully 30%
     *  If budget is within min and max budget, budget contribution for the match percentage is full 30%. If min or max is not given, +/- 10% budget is a full 30% match
     *  If bedroom and bathroom fall between min and max, each will contribute full 20%. If min or max is not given, match percentage varies according to the value.
     *
     * @param requirementDTO
     * @param propertyDistance
     * @return
     */
    private int getMatchPercentage(RequirementDTO requirementDTO, PropertyDistance propertyDistance){

        int matchPercentage = 0;
        if(propertyDistance.getDistance() <= 2)
            matchPercentage += 30;

        if(requirementDTO.getPriceFilter() == RangeFilterStates.BOTH_PRESENT)
            matchPercentage += 30;
        else {
            BigDecimal cost = requirementDTO.getPriceFilter() == RangeFilterStates.MIN_PRESENT?
                    requirementDTO.getMinPrice() : requirementDTO.getMaxPrice();
            BigDecimal tempMin = cost.subtract(cost.multiply(BigDecimal.TEN).divide(Constants.ONE_HUNDRED));
            BigDecimal tempMax = cost.add(cost.multiply(BigDecimal.TEN).divide(Constants.ONE_HUNDRED));
            if(tempMin.compareTo(new BigDecimal(propertyDistance.getDistance())) < 0 &&
                    tempMax.compareTo(new BigDecimal(propertyDistance.getDistance())) > 0) {
                matchPercentage += 30;
            }
        }

        if(requirementDTO.getBedroomFilter() == RangeFilterStates.BOTH_PRESENT)
            matchPercentage += 20;
        else {
            int count = requirementDTO.getBedroomFilter() == RangeFilterStates.MIN_PRESENT?
                    requirementDTO.getMinNumberOfBathrooms() : requirementDTO.getMaxNumberOfBedrooms();
            //match percentage varies according to the value
            //Assumption if matches absolute value of input add 20 if one diff add 10 if two diff add 5
            if( count == propertyDistance.getNumberOfBedrooms())
                matchPercentage += 20;
            if( Math.abs(count - propertyDistance.getNumberOfBedrooms()) == 1)
                matchPercentage += 10;
            if( Math.abs(count - propertyDistance.getNumberOfBedrooms()) == 2)
                matchPercentage += 5;

        }

        if(requirementDTO.getBathroomFilter() == RangeFilterStates.BOTH_PRESENT)
            matchPercentage += 20;
        else {
            int count = requirementDTO.getBathroomFilter() == RangeFilterStates.MIN_PRESENT?
                    requirementDTO.getMinNumberOfBathrooms() : requirementDTO.getMaxNumberOfBathrooms();
            //match percentage varies according to the value
            //Assumption if matches absolute value of input add 20 if one diff add 10 if two diff add 5
            if( count == propertyDistance.getNumberOfBathrooms())
                matchPercentage += 20;
            if( Math.abs(count - propertyDistance.getNumberOfBathrooms()) == 1)
                matchPercentage += 10;
            if( Math.abs(count - propertyDistance.getNumberOfBathrooms()) == 2)
                matchPercentage += 5;

        }

        return matchPercentage;

    }
}

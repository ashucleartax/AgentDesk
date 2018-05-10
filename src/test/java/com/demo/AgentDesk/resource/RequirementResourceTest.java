package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.AgentDeskApplicationTests;
import com.demo.AgentDesk.dto.PropertyDistanceWrapper;
import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashutoshpandey on 10/05/18.
 */
public class RequirementResourceTest extends AgentDeskApplicationTests {

    @Test
    public void getRequiredProperty() {

        RequirementDTO requirementDTO = createProperty(50);
        //RequirementDTO requirementDTO = getRandomRequirement();
        ResponseEntity<ArrayList> response = restTemplate.postForEntity("/requirement", requirementDTO, ArrayList.class);
        System.out.println(response);
    }

    public RequirementDTO createProperty(int count) {
        Property propertyRequest = null;
        for(int i=1;i<count;i++){
            propertyRequest = getRandomProperty();
            ResponseEntity<Property> response = restTemplate.postForEntity("/property", propertyRequest, Property.class);
        }

        return RequirementDTO.builder()
                .latitude(propertyRequest.getLatitude())
                .longitude(propertyRequest.getLongitude())
                .minNumberOfBedrooms(propertyRequest.getNumberOfBedrooms())
                .minNumberOfBathrooms(propertyRequest.getNumberOfBathrooms())
                .minPrice(propertyRequest.getPrice())
                .distance(50D)
                .build();

    }
}

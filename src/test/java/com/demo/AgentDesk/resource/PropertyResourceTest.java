package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.AgentDeskApplicationTests;
import com.demo.AgentDesk.entity.Property;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by ashutoshpandey on 10/05/18.
 */
public class PropertyResourceTest extends AgentDeskApplicationTests {

    @Test
    public void createPropertyTest() {

        Property propertyRequest = getRandomProperty();
        ResponseEntity<Property> response = restTemplate.postForEntity("/property", propertyRequest, Property.class);

        Assert.assertTrue(response.getStatusCode() == HttpStatus.CREATED);
        Property responseProperty = response.getBody();
        Assert.assertTrue(responseProperty.getExternalId().equals(propertyRequest.getExternalId()));

    }
}
package com.demo.AgentDesk;

import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.util.GeographicPoint;
import com.demo.AgentDesk.util.GeographicSquare;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AgentDeskApplicationTests {

	@Autowired
	protected TestRestTemplate restTemplate;

	public ObjectMapper objectMapper = new ObjectMapper();

	@Test
	public void contextLoads() {
	}

	@Test
	public void health(){
		ResponseEntity<String> entity =
				restTemplate.getForEntity("/ping", String.class);
        Assert.isTrue(entity.getBody().equals("pong"), "Health check Success");
	}


	public static Property getRandomProperty(){

        GeographicPoint geographicPoint = getRandomGeographicalPoints(null, 1).get(0);

        return Property.builder()
                .latitude(geographicPoint.getLatitude())
                .longitude(geographicPoint.getLongitude())
                .externalId(UUID.randomUUID().toString())
                .numberOfBathrooms((int)Math.ceil(Math.random() * 10))
                .numberOfBedrooms((int)Math.ceil(Math.random() * 10))
                .price(new BigDecimal(100000 + Math.random()* 100000))
                .build();

    }

    public static RequirementDTO getRandomRequirement(){

        GeographicPoint geographicPoint = getRandomGeographicalPoints(null, 1).get(0);

        return RequirementDTO.builder()
                .latitude(geographicPoint.getLatitude())
                .longitude(geographicPoint.getLongitude())
                .minNumberOfBathrooms((int)Math.ceil(Math.random() * 10))
                .minNumberOfBedrooms((int)Math.ceil(Math.random() * 10))
                .minPrice(new BigDecimal(100000 + Math.random()* 100000))
                .build();

    }


    private static  ArrayList<GeographicPoint> getRandomGeographicalPoints(GeographicSquare mockCityGeo, int maximumPoints) {

        ArrayList<GeographicPoint> geographicPoints = new ArrayList<>();
        if(mockCityGeo == null)
            mockCityGeo = new GeographicSquare(
                new GeographicPoint(38.7, -77.0), new GeographicPoint(38.9,
                -76.9));

        Random randomGenerator = new Random();
        double deltaLong = mockCityGeo.getDeltaLongitude();
        double deltaLat = mockCityGeo.getDeltaLatitude();

        for(int i=1;i<= maximumPoints; i++) {
            GeographicPoint newLocation = new GeographicPoint(
                    mockCityGeo.getLowerLeftLatitude() + deltaLat
                            * randomGenerator.nextDouble(),
                    mockCityGeo.getLowerLeftLongitude() + deltaLong
                            * randomGenerator.nextDouble());

            geographicPoints.add(newLocation);
        }
        return geographicPoints;
    }

}

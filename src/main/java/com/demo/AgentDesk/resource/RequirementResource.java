package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.dto.PropertyDistance;
import com.demo.AgentDesk.dto.PropertyDistanceWrapper;
import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
@RestController
public class RequirementResource {

    @Autowired
    PropertyService propertyService;

    //TODO it should be get but POST makes it quicker for me to write test cases
    @RequestMapping(value = "/requirement", method = RequestMethod.POST)
    public ResponseEntity<List<PropertyDistanceWrapper>> findByRequirementFilter(@RequestBody RequirementDTO requirementDTO){
        return new ResponseEntity<List<PropertyDistanceWrapper>>(propertyService.filterPropertyByRequirement(requirementDTO), HttpStatus.OK);
    }

}

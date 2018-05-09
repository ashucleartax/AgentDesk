package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.dto.PropertyDistance;
import com.demo.AgentDesk.dto.RequirementDTO;
import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.entity.Requirement;
import com.demo.AgentDesk.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by ashutoshpandey on 09/05/18.
 */
@RestController
@RequestMapping("/requirement")
public class RequirementResource {

    @Autowired
    PropertyService propertyService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<PropertyDistance>> findByRequirementFilter(@Valid RequirementDTO requirementDTO){
        return new ResponseEntity<List<PropertyDistance>>(propertyService.filterPropertyByRequirement(requirementDTO), HttpStatus.OK);
    }

}

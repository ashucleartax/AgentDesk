package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.entity.Property;
import com.demo.AgentDesk.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by ashutoshpandey on 08/05/18.
 */
@RestController
@RequestMapping("/property")
public class PropertyResource {

    @Autowired
    private PropertyService propertyService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Iterable<Property>> findAll(){
        return new ResponseEntity<Iterable<Property>>(propertyService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.GET)
    public ResponseEntity<Property> find(@PathParam("propertyId") String propertyId){
        return new ResponseEntity<Property>(propertyService.find(propertyId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Property> create(@RequestBody Property property){
        return new ResponseEntity<Property>(propertyService.create(property), HttpStatus.CREATED);

    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.PUT)
    public ResponseEntity<Property> update(@PathParam("propertyId") String propertyId, @RequestBody Property property){
        return new ResponseEntity<Property>(propertyService.update(propertyId, property), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathParam("propertyId") String propertyId){
        propertyService.delete(propertyId);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }



}

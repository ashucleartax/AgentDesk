package com.demo.AgentDesk.resource;

import com.demo.AgentDesk.entity.Property;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Property>> findAll(){
        return null;
    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.GET)
    public ResponseEntity<Property> find(@PathParam("propertyId") String propertyId){
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Property> create(@RequestBody Property property){
        return null;
    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.PUT)
    public ResponseEntity<Property> update(@PathParam("propertyId") String propertyId, @RequestBody Property property){
        return null;
    }

    @RequestMapping(value = "/{propertyId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathParam("propertyId") String propertyId){
        return null;
    }



}

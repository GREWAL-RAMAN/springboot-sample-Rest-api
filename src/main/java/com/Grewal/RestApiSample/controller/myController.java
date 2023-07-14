package com.Grewal.RestApiSample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Grewal.RestApiSample.entity.payload;
import com.Grewal.RestApiSample.exception.LoadNotFoundException;
import com.Grewal.RestApiSample.services.payloadService;

@RestController
@RequestMapping("/load")
public class myController {
	@Autowired
	payloadService myPayloadService;
	
	 @PostMapping
	    public ResponseEntity<String> addLoad(@RequestBody payload load) {
	        try {
	            String result= myPayloadService.createPayload(load);
	            return ResponseEntity.status(HttpStatus.CREATED).body(result);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add load details");
	        }
	    }

	    @GetMapping
	    public ResponseEntity<List<payload>> getLoadsByShipperId(@RequestParam("shipperId") String shipperId) {
	        try {
	            List<payload> loads =myPayloadService.getPayloads(shipperId);
	            if (loads.size() <= 0) {
	            	return ResponseEntity.notFound().build();
	            } else {
	            	return ResponseEntity.ok(loads);
	            }
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	    }

	    @GetMapping("/{loadId}")
	    public ResponseEntity<payload> getLoadById(@PathVariable("loadId") String loadId) {
	    	  try {
	              payload load = myPayloadService.getPayload(Long.parseLong(loadId));
	              return ResponseEntity.ok(load);
	          } catch (LoadNotFoundException e) {
	              return ResponseEntity.notFound().build();
	          } catch (Exception e) {
	              return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	          }
	    }
	    @PutMapping("/{loadId}")
	    public ResponseEntity<String> updateLoadById(
	            @PathVariable("loadId") String loadId,
	            @RequestBody payload load
	    ) {
	        try {  
	            return myPayloadService.updatePayload(Long.parseLong(loadId),load);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update load details");
	        }
	    }

	    @DeleteMapping("/{loadId}")
	    public ResponseEntity<String> deleteLoadById(@PathVariable("loadId") String loadId) {
	        try {
	            return myPayloadService.deletePayload(Long.parseLong(loadId));
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete load");
	        }
	    }
}

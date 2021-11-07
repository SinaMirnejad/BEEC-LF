package com.lightfeather.BEEC;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import java.net.http.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.apache.tomcat.util.modeler.modules.ModelerSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Exceptions.IncompleteEmployeeInfoException;
import Exceptions.MissingInfo;
import logic.*;



@RestController
@ControllerAdvice
public class TestContrller {
	
	@GetMapping("/test")
	public String testC() {
		return "testing restful";
	}
	
	@GetMapping("/api/supervisors")
	public String supervisorsList() {
		
		

		
		//Read From URL
		ArrayList<Manager> supervisors = new ArrayList<Manager>();
	    String url = "https://o3m5qixdng.execute-api.us-east-1.amazonaws.com/api/managers";

		HttpClient cli = HttpClient.newHttpClient();
		HttpRequest req = HttpRequest.newBuilder()
				.GET().header("accept", "application/json")
				.uri(URI.create(url))
				.build();
		
		HttpResponse<String> responce;
		try {
			responce = cli.send(req, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e1) {
			e1.printStackTrace();
			return "404 Over load, source unavailable";
		} catch (InterruptedException e1) {
			e1.printStackTrace();
			return "404 Over load, source unavailable";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			supervisors = mapper.readValue(responce.body(), new TypeReference<ArrayList<Manager>>() {});
		} catch (JsonMappingException e) {
			e.printStackTrace();
			return "404 Over load, source Unreadable";
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return "404 Over load, source Unreadable";
		}
		
		//removing Managers with numeric jurisdiction
		ArrayList<Manager> supDup = new ArrayList<Manager> (supervisors);
		for(Manager m: supDup) {
			if (m.isJurisdictionNumeric()) {
				supervisors.remove(m);
			}
		}

		//sort the ArrayList
		supervisors.sort(Manager.getComparator("JLF"));

				
	    StringBuffer sb = new StringBuffer();
	    sb = new StringBuffer("");
		for(int i = 0 ; i < supervisors.size(); i++) {
			sb.append("<p>" + supervisors.get(i).print() + "</p>");
			//sb.append(supervisors.get(i).print());
		}
	    
		return sb.toString();
	}
	
	@PostMapping("/api/submit")
	public ResponseEntity submit(@RequestBody Employee employee) throws IncompleteEmployeeInfoException {
		System.out.println(employee);
		
		if(!employee.isValid()) {
	        Object bindingResult;
	        MissingInfo m = new MissingInfo("lastName, firstName, and supervisor are required parameters.");
		    return (ResponseEntity) ResponseEntity.status(HttpStatus.BAD_REQUEST).body(m);
		}
	    return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).build();

	}
	
	

}

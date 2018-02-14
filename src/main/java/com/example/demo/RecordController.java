package com.example.demo;

import org.json.JSONObject;
//import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController 
public class RecordController {

	@RequestMapping("/record/gender")
	public String getGender() {
		DemoApplication.records.sort((o1, o2) -> (o1.gender.compareTo(o2.gender)));
		return DemoApplication.list2JSON().toString();

	}
	
	@RequestMapping("/record/birthdate")
	public String getBirthdate() {
		DemoApplication.records.sort((o1, o2) -> (o1.dataOfBirth.year - o2.dataOfBirth.year) );
		return DemoApplication.list2JSON().toString();
	}
	
	@RequestMapping("/record/name")
	public String getName() {
		DemoApplication.records.sort((o1, o2) -> (o1.lastName.compareTo(o2.lastName)));
		return DemoApplication.list2JSON().toString();
	}
	
	
	//put
	@RequestMapping(method = RequestMethod.PUT,value = "/record")
	public String putRecord() {
		return "put";
	}
	
	//post
	@RequestMapping(method = RequestMethod.POST,value = "/record")
	public String postRecord() {
		return "post";
	}
	
	
	
}

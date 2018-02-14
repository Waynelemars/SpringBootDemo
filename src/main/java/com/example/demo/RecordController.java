package com.example.demo;

import java.awt.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
//import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wayne.record.Record;
import com.wayne.record.Test;



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
	
	
//    @RequestMapping(value = "profile", method = RequestMethod.POST)
//    public String saveProduct(Profile profile){
//        profileService.create(profile);
//        return "redirect:/profile/" + profile.getNumberID();
//    }
	
//	public Record(String lastName, String firstName, String gender, String favoriteColor, int day, int month,
//			int year) 
	
	//post
	@RequestMapping(method = RequestMethod.POST,value = "/record",params = {"fileName"})
	public String postRecord(@RequestBody Record record, @RequestParam(value="fileName") String fileName) {

		DemoApplication.records.add(record);
			
		if (fileName.equals("ingest-files/person_comma_delim.csv")) {
			DemoApplication.recordCom.add(record);
			System.out.println("hello");
			return "add to comma file";
		} else if (fileName.equals("ingest-files/person_space_delim.csv")) {
			DemoApplication.recordSpa.add(record);
			return "add to space file";
		} else if(fileName.equals("ingest-files/person_pipe_delim.csv")){
			DemoApplication.recordPip.add(record);
			return "add to pip file";
		} else {
			return "successfully add to file " + fileName;
			
		}	
	}
	

	
	//put
	@RequestMapping(method = RequestMethod.PUT,value = "/record")
	public String putRecord(@RequestBody Record record) {
		ArrayList<Record> records = DemoApplication.records;
		for (int i = 0 ; i < records.size() ; i++) {
			Record exR = records.get(i);
			if (exR.firstName.equals(record.firstName) && exR.lastName.equals(record.lastName) ) {
				exR.favoriteColor = record.favoriteColor ;
				exR.gender = record.gender;
				exR.day = record.day;
				exR.month = record.month;
				exR.year = record.year;
			}
		}
	
	
		return "put";
	
	}

	
	
	
}

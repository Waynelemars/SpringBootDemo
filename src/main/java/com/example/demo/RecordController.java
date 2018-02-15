package com.example.demo;

import java.awt.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		DemoApplication.records.sort((o1, o2) -> (o1.dataOfBirth.year - o2.dataOfBirth.year));
		return DemoApplication.list2JSON().toString();
	}

	@RequestMapping("/record/name")
	public String getName() {
		DemoApplication.records.sort((o1, o2) -> (o1.lastName.compareTo(o2.lastName)));
		return DemoApplication.list2JSON().toString();
	}

	// @RequestMapping(value = "profile", method = RequestMethod.POST)
	// public String saveProduct(Profile profile){
	// profileService.create(profile);
	// return "redirect:/profile/" + profile.getNumberID();
	// }

	// public Record(String lastName, String firstName, String gender, String
	// favoriteColor, int day, int month,
	// int year)

	// post
	@RequestMapping(method = RequestMethod.POST, value = "/record", params = { "fileName" })
	public String postRecord(@RequestBody Record record, @RequestParam(value = "fileName") String fileName) {
		// files[0] = "ingest-files/person_comma_delim.csv";
		// files[1] = "ingest-files/person_space_delim.csv";
		// files[2] = "ingest-files/person_pipe_delim.csv";
		DemoApplication.records.add(record);
		if (fileName.equals("ingest-files/person_comma_delim.csv")) {
			DemoApplication.recordCom.add(record);
			try {
				String content = "\n" + record.lastName + "," + record.firstName + "," + record.gender + ","
						+ record.favoriteColor + "," + record.day + "/" + record.month + "/" + record.year;
				System.out.println("printing");
				File file = new File("ingest-files/person_comma_delim.csv");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

			} catch (IOException ioe) {
				System.out.println("Exception occurred:");
				ioe.printStackTrace();
			}
			return "add to comma file successfully. You add LastName;" + record.lastName + " to the comma file";
		} else if (fileName.equals("ingest-files/person_space_delim.csv")) {
			DemoApplication.recordSpa.add(record);

			try {
				String content = "\n" + record.lastName + "     " + record.firstName + "    " + record.gender + "    "
						+ record.favoriteColor + "   " + record.day + "/" + record.month + "/" + record.year;
				System.out.println("printing");
				File file = new File("ingest-files/person_space_delim.csv");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

			} catch (IOException ioe) {
				System.out.println("Exception occurred:");
				ioe.printStackTrace();
			}

			return "add to space file successfully. You add LastName: " + record.lastName + " to the space file";
		} else if (fileName.equals("ingest-files/person_pipe_delim.csv")) {
			DemoApplication.recordPip.add(record);
			try {
				String content = "\n" + record.lastName + "|" + record.firstName + "|" + record.gender + "|"
						+ record.favoriteColor + "|" + record.day + "/" + record.month + "/" + record.year;
				System.out.println("printing");
				File file = new File("ingest-files/person_pipe_delim.csv");
				if (!file.exists()) {
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();

			} catch (IOException ioe) {
				System.out.println("Exception occurred:");
				ioe.printStackTrace();
			}
			return "add to Pipe file successfully. You add LastName" + record.lastName + " to the Pipe file";
		} else {
			return "You didn't specify which file to add so it add to all file";
		}

	}

	// put
	@RequestMapping(method = RequestMethod.PUT, value = "/record", params = { "fileName" })
	public String putRecord(@RequestBody Record record, @RequestParam(value = "fileName") String fileName) {
		File file = null;
		ArrayList<Record> records = null;
		String delim = "";
		if (fileName.equals("ingest-files/person_comma_delim.csv")) {
			delim = ",";
			records = DemoApplication.recordCom;
			file = new File("ingest-files/person_comma_delim.csv");
		} else if (fileName.equals("ingest-files/person_space_delim.csv")) {
			delim = " ";
			records = DemoApplication.recordSpa;
			file = new File("ingest-files/person_space_delim.csv");
		} else if (fileName.equals("ingest-files/person_pipe_delim.csv")) {
			delim = "|";
			records = DemoApplication.recordPip;
			file = new File("ingest-files/person_pipe_delim.csv");
		}

		for (int i = 0; i < records.size(); i++) {
				Record exR = records.get(i);
				if (exR.lastName.equals(record.lastName) && exR.firstName.equals(record.firstName)) {
					records.set(i, record);
					System.out.println("I set it");
				}	
		}
	
		//write all file to csv
		try {		
			if (file.exists()) {
				file.delete();
			}
			for (int i = -1; i < records.size(); i++) {
				
				if (i == -1) {
					String content =   "LastName" + delim + "FirstName" + delim + "Gender" + delim + "FavoriteColor"+ delim  + "DateOfBirth";
					if (!file.exists()) {
						file.createNewFile();
					} 
					FileWriter fw = new FileWriter(file, true);
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(content);
					bw.close();
					continue;
				}
				
				System.out.println("printing");
				String content = "\n" + records.get(i).lastName + delim + records.get(i).firstName + delim + records.get(i).gender + delim
						+ records.get(i).favoriteColor + delim + records.get(i).day + "/" + records.get(i).month + "/" + records.get(i).year;	
				if (!file.exists()) {
					file.createNewFile();
				} 
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(content);
				bw.close();
			}
		} catch (IOException ioe) {
			System.out.println("Exception occurred:");
			ioe.printStackTrace();
		}
		
		
		
		
		
		
		
		return "";
	}
}
							
//				Record exR = records.get(i);
//				System.out.println(records.get(i).firstName);
			
//				if (exR.lastName.equals(record.lastName)) {
//					String content = "\n" + record.lastName + "," + record.firstName + ","
//							+ record.gender + "," + record.favoriteColor + "," + record.day
//							+ "/" + records.get(i).month + "/" + records.get(i).year;
//
//					if (!file.exists()) {
//						file.createNewFile();
//					}
//					FileWriter fw = new FileWriter(file, true);
//					BufferedWriter bw = new BufferedWriter(fw);
//					bw.write(content);
//					bw.close();
//					return "Successfully Add it";
//
//				} else {
//					return "This record you are trying to input are not exit, add new record by using post";
//				}
//			} catch (IOException ioe) {
//				System.out.println("Exception occurred:");
//				ioe.printStackTrace();
//			}
//
//		}
//		return "put" + fileName;
//	}

//}

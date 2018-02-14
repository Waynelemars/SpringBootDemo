package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wayne.record.*;

import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
public class DemoApplication {

	static public ArrayList<Record> records;
	
	public static void main(String[] args) {
		
		records = new ArrayList<Record>();
		String[] files = new String[3];
		files[0] = "ingest-files/person_comma_delim.csv";
		files[1] = "ingest-files/person_space_delim.csv";
		files[2] = "ingest-files/person_pipe_delim.csv";
		for(String f : files) {
			readFileFromName(f);
		}
		
		for ( Record r: records) {
			System.out.println(r.lastName + " " + r.firstName + " " + r.gender + " " + r.favoriteColor + " " + r.dataOfBirth);
			
		}
		System.out.println("?????");
//		System.out.println(list2JSON());
		System.out.println("=======");

		
		SpringApplication.run(DemoApplication.class, args);
	}
	
	static public JSONObject list2JSON() {
		JSONObject ret = new JSONObject();
		JSONObject[] element = new JSONObject[records.size()];
		for(int i = 0; i < records.size(); i++) {
			element[i] = new JSONObject();
			try {
				element[i].put("LastName",records.get(i).lastName);
				element[i].put("FirstName", records.get(i).firstName);
				element[i].put("Gender", records.get(i).gender);
				element[i].put("FavoriteColor", records.get(i).favoriteColor);
				element[i].put("DateOfBirth", records.get(i).dataOfBirth.month + "/"+records.get(i).dataOfBirth.day +"/" +records.get(i).dataOfBirth.year);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		try {
			ret.put("result",element);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public static void readFileFromName(String fileName) {
		// This will reference one line at a time
		String line = null;

		ArrayList<String> list = new ArrayList<>();

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				list.add(line);
				// System.out.println(line);
			}

			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
		}

		printTable(fileName, list);
	}

	public static void printTable(String fileName, ArrayList<String> content) {

		if (content.size() == 0) {
			System.out.println("Empty File");
		}
		System.out.println(fileName);
		int width = 15;
		String delim = null;
		String[] del = new String[] { ",", " ", "|" };
		for (String d : del) {
			if (content.get(0).indexOf(d) > 0) {
				if (d.equals(" ")) {
					delim = "\\s+";
				} else {
					delim = "[" + d + "]";
				}
				break;
			}
		}
		for (int i = 1; i < content.size(); i++) {
			String[] line = content.get(i).split(delim);
			// print value
			String lName = line[0];
			String fName = line[1];
			String gender = line[2];
			String color = line[3];
			String[] birth = line[4].split("[/]"); 
			records.add(new Record(lName, fName, gender, color, Integer.parseInt(birth[0]), Integer.parseInt(birth[1]), Integer.parseInt(birth[2]) ));
			

		}
	}
	
	
}

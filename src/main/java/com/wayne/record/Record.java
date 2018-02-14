package com.wayne.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wayne.record.Record.DataOfBirth;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Record {
//	{"DateOfBirth":"4/9/1934","FirstName":"test","LastName":"test","Gender":"test","FavoriteColor":"test"}
	public String lastName;
	public String firstName;
	public String gender;
	public String favoriteColor;
	public  int day;
	public  int month;
	public  int year;
	public DataOfBirth dataOfBirth = new DataOfBirth(day,month,year);


	//Note: must add this default constructor otherwise when you pass json payload to post method. Spring boot cannot help you translate from json to your object
	//Debug this for a long time
	//Reference http://www.baeldung.com/jackson-exception
	public Record() {
		
	}
	
	public Record(String lastName, String firstName, String gender, String favoriteColor, int day, int month,
			int year) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.gender = gender;
		this.favoriteColor = favoriteColor;
		this.dataOfBirth = new DataOfBirth(day, month, year);
	}

	public class DataOfBirth {
		public int year;
		public int month;
		public int day;

		public DataOfBirth(int day, int month, int year) {
			this.year = year;
			this.month = month;
			this.day = day;
		}

	}

}

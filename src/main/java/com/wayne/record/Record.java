package com.wayne.record;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.wayne.record.Record.DataOfBirth;
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class Record {
//	{"DateOfBirth":"4/9/1934","FirstName":"test","LastName":"test","Gender":"test","FavoriteColor":"test"}
	@JsonProperty("LastName")public String lastName;
	@JsonProperty("FirstName")public String firstName;
	@JsonProperty("Gender")public String gender;
	@JsonProperty("FavoriteColor")public String favoriteColor;
	public DataOfBirth dataOfBirth;

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

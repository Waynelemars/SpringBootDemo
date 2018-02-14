package com.wayne.record;

import com.wayne.record.Record.DataOfBirth;

public class Record {

	public String lastName;
	public String firstName;
	public String gender;
	public String favoriteColor;
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

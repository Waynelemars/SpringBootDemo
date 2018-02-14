package com.wayne.record;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class Test{

	public String a = "ted";
	
	//Note: must add this default constructor otherwise when you pass json payload to post method. Spring boot cannot help you translate from json to your object
	//Debug this for a long time
	public Test() {
		
	}
	public Test(String a){
		this.a = a;
	}
	
}

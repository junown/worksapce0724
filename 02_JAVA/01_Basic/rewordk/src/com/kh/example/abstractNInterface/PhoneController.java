package com.kh.example.abstractNInterface;

import java.util.Scanner;

public class PhoneController {
	private String[] result = new String[2];
	
	public String[] method() {
		Phone[] phones = new Phone[2];
		phones[0] = new GalaxyNote9();
		phones[1] = new V40();
		
		String[] result = new String[2];
		int index = 0;
		for(Phone p : phones) {
			if(p instanceof SmartPhone) {
				result[index++] = ((SmartPhone)p).printInformation();
			}
		}
		return result;
	}
}

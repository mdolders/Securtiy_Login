package com.example.security_login;
import java.util.Random;

public class RandGen {
	

	private static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String specialChar = "!@#$%^&*";
	private static final String intChar = "0123456789";
	private static String pass = "";
	private static Random r = new Random();
	

	public String gernarate() {
	
	while (pass.length () != 10){
	int rPick = r.nextInt(4);
	if (rPick == 0){
	int spot = r.nextInt(25);
	pass += lowerCase.charAt(spot);
	}else if (rPick == 1) {
	int spot = r.nextInt (25);
	pass += upperCase.charAt(spot);
	} else if (rPick == 2) {
	int spot = r.nextInt (7);
	pass += specialChar.charAt(spot);
	} else if (rPick == 3){
	int spot = r.nextInt (9);
	pass += intChar.charAt (spot);
	}
		}return pass;
	}

}

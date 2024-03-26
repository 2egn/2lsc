package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

import View.MainPage;

public class user {
	boolean isLogin = false;
	public static int no = 0;
	public static String id = "";
	public static String pw = "";
	public static String name = "";
	public static String birth = "";
	public static int age = 1000;
	
	public static void Init() {
		id = "";
		pw = "";
		name = "";
		birth = "";
		age = 1000;
	}
	public static void setAge() {
		LocalDate nowdate = LocalDate.now();
		LocalDate birthdate = LocalDate.parse(birth);
		int tempage = nowdate.getYear()-birthdate.getYear();
		if(birthdate.isAfter(nowdate.minusYears(tempage))) tempage--;
		age = tempage;
		System.out.println(age);
	}
	
}

package core;

import View.Login;
import View.RegiPage;

public class RegisterController {
	public static String name = "";
	public static String birth = "";
	public static String id = "";
	public static String pw = "";
	public static RegiPage regipage;
	
	public static void SendPassword() {
		regipage.pwbox.setText(pw);
		regipage.setVisible(true);
		
	}
	
	public static void Init() {
		name = "";
		birth = "";
		id = "";
		pw = "";
	}
	
}

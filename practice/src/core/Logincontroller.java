package core;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import View.Login;
import View.MainPage;
import model.user;
public class Logincontroller {
	public static String pw = "";
	public static String id = "";
	public static MainPage mainpage;
	public static Login login;
	public static void SendPassword() {
		login.pwbox.setText(pw);
		login.setVisible(true);
		
	}
	
	public static void Init() {
		pw = "";
		id = "";
	}
	public static void TryLogin() throws SQLException{
		SQLExecutor ex = new SQLExecutor();
		if(id.isBlank()||pw.isBlank()) {
			JOptionPane.showMessageDialog(null, "��ĭ�� �ֽ��ϴ�","���",JOptionPane.ERROR_MESSAGE);
			return;
		}
		ex.Connect();
		ResultSet rs = ex.ExecuteReadQuery("SELECT * FROM user WHERE id='"+id+"' AND pw='"+pw+"'");
		if(!rs.next()) {//login failed
			JOptionPane.showMessageDialog(null, "�α��� ������ ��ġ���� �ʽ��ϴ�","���",JOptionPane.ERROR_MESSAGE);
			return;
		}
		mainpage.isLogin = true;
		mainpage.LoginInitiated();	
		rs.close();
		rs = ex.ExecuteReadQuery("SELECT id,pw,u_name,birth,u_no FROM user WHERE id='"+id+"'");
		while(rs.next()) {
			user.id=rs.getString(1);
			user.pw=rs.getString(2);
			user.name=rs.getString(3);
			user.birth=rs.getString(4);
			user.no=rs.getInt(5);
		}
		user.setAge();
		login.dispose();
		mainpage.createMusicArea();
		mainpage.setVisible(true);
	}
}

package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Sql5 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://10.125.121.222:3306/sqltest";
		String user = "scott";
		String passwd = "tiger";
		String str;
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, passwd);
		Statement st = con.createStatement();
		
//		for(int i = 1; i <= 100; i++) {
//			//str = "insert into user values ( 'user' "+ i +", 'passwd' "+ i +", 'user', true, now())";
//		str = "insert into user values ( 'user"+i+"', 'passwd"+i+"', 'user', true, now())";
//		System.out.println(i + st.executeUpdate(str));
//	}	
			str = "update user set enable = false where username like \"user3%\"";
			System.out.println(st.executeUpdate(str));
		}
	
}


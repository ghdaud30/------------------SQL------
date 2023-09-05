package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Sql7 {

	public static void main(String[] args) {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://10.125.121.222:3306/sqltest";
		String user = "scott";
		String passwd = "tiger";
		
		String str = "update user set enable = ? where username like ?";
		
		Scanner sc = new Scanner(System.in);
	    System.out.print("Please write your SQL Query1: ");
	    boolean query1 = sc.nextBoolean(); 
	    System.out.print("Please write your SQL Query2: ");
	    String query2 = sc.next();   //nextBoolean 과 nextLine 은 궁합이 안좋네
	    System.out.println("");
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, passwd);
			PreparedStatement pst = con.prepareStatement(str);
			pst.setBoolean(1, query1);
			pst.setString(2, query2);
			int effect = pst.executeUpdate();
			System.out.println(effect);

			if (effect > 0) {
	            System.out.println("업데이트가 성공적으로 수행되었습니다. 영향 받은 행 수: " + effect);
	        } else {
	            System.out.println("업데이트 작업이 실패하거나 영향을 받은 행이 없습니다.");
	        }
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}

}

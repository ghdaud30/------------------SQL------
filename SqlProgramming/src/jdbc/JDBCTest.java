package jdbc;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class JDBCTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try {
			
			Scanner sc = new Scanner(System.in);
			
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String user = "scott";
			String pwd = "tiger";
			
			String driver2= "org.h2.Driver";
			String url2 = "jdbc:h2:tcp://localhost/~/.h2/jdbcstudy";
			String user2 = "sa";
			String pwd2 = "abcd";
			
			String str = "select * from city limit 10";
			String str2 = "select * from city where Population between ? and ? limit 10";
			String str3 = "select * from testtable";
			String str4 = "select * from testtable where TELEPHONE = ? limit ?";
			
			
			String str5 = "INSERT INTO testtable VALUES (977, 'gfd', '938', 'dfgfdgdf');";
			
			MYJDBC mj = new MYJDBC(driver, url, user, pwd);
			MYJDBC mj2 = new MYJDBC(driver2, url2, user2, pwd2);
		
			
			
//			List<List<String>> list = mj.queryWithStatement(str3);
//			if(list != null) {
//				mj.printList(list);
//			}
//			
//			System.out.print("Please write your SQL Query: ");
//			String arg = sc.nextLine();
//			System.out.print("Please write your SQL Query: ");
//			String arg2 = sc.nextLine();
//
//			
//			List<List<String>> list2 = mj2.queryWithPreparedStatement(str4,arg,arg2);
//			if(list != null) {
//				mj2.printList(list2);
//			}
			
			int rs = mj2.query_update_WithStatement(str5);
			
			if(rs > 0) {
				System.out.println(rs + ": rs is success");
			}else {
				System.out.println(rs + ": rs is failed");
			}
			System.out.println("");
			
			List<List<String>> list = mj2.queryWithStatement(str3);
			if(list != null) {
				mj2.printList(list);
			}
			
		}catch(Exception e) {
			e.getMessage();
		}
	}
}

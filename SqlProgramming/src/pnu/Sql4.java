package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Sql4 {
	
	public static Connection dbConnect(String driver, String url, String user, String passwd){
		try {
			
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, passwd);
			
			System.out.println("1. Connection success(con_return)");
			System.out.println("");
			return con;
			
		}catch(Exception e){
			System.out.println("1. Connection failure(con_return)");
			System.out.println("");
			System.out.println(e.getMessage());
		}
		
		return null;
	}
	
	public static Statement dbQuery_st(Connection con) {
		try {
			Statement st = con.createStatement();
			System.out.println("2. Query1 Success(st_return)");
			System.out.println("");
			return st;
			
		}catch(Exception e) {
			System.out.println("2. Query1 failure(st_return)");
			System.out.println("");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static ResultSet dbQuery_rs(Statement st, String str) {
		try {
			ResultSet rs = st.executeQuery(str);
			System.out.println("3. Query2 success(rs_return)");
			
			return rs;
			
		}catch(Exception e) {
			System.out.println("3. Query2 failure(rs_return)");
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public static int dbQuery_rs_update(Statement st, String str) {
		try {
			int rs = st.executeUpdate(str);
			System.out.println("3. Query2 success(rs_return)");
			
			return rs;
			
		}catch(Exception e) {
			System.out.println("3. Query2 failure(rs_return)");
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public static void dbShowData(ResultSet rs){
		try {
			System.out.println("");
			System.out.println("-".repeat(100));
			
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			
			int arr[] = new int[count];

			
			
			//getColumnDisplaySize(i) sql 각 열 데이터형크기  char(3) 	3
			//getColumnName(i) 각 열의 이름 크기 CountryCode  	11
			
			for(int i =1; i <= count; i++) {
				arr[i-1] = rsmd.getColumnDisplaySize(i) > rsmd.getColumnName(i).length()
						? rsmd.getColumnDisplaySize(i): rsmd.getColumnName(i).length() + 1;
				
				System.out.print(String.format("%-" + arr[i-1] + "s",  rsmd.getColumnName(i)));
			}
			System.out.println("");
			System.out.println("");
			
			while(rs.next()) {
				for(int i =1; i <= count; i++) {
					System.out.print(String.format("%-" + arr[i-1] + "s", rs.getString(i)));
				}
				System.out.println("");
			}
			
			
			
		}catch(Exception e) {
			System.out.println("Show failure");
			System.out.println(e.getMessage());
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://10.125.121.222:3306/world";
		String user = "scott";
		String passwd = "tiger";
		
		Scanner sc = new Scanner(System.in);
	    System.out.print("Please write your SQL Query: ");
	    String query = sc.nextLine(); 
	    System.out.println("");
	     
		String str = "select * from city limit 10";
		
		String str1 = "SELECT Name\r\n"
				+ "FROM world.city\r\n"
				+ "WHERE countrycode = 'kor'\r\n"
				+ "AND Population >= 1000000;";
		
		String str2 = "SELECT Name , Population\r\n"
				+ "FROM world.city\r\n"
				+ "ORDER BY Population DESC";

		String str3 = "SELECT Name, Population\r\n"
				+ "FROM world.city\r\n"
				+ "WHERE Population BETWEEN 1000000 AND 5000000\r\n"
				+ "ORDER BY Population DESC";
		
		String str4 = "select c.Name, Language, IsOfficial, Percentage\r\n"
				+ "from country c join countrylanguage cl\r\n"
				+ "on cl.countrycode = c.Code and c.Code ='kor';";
		
		String str5 = "insert into user values (?, ?, 'user','True', now() + INTERVAL 1 MINUTE)";
		
		Connection con =  dbConnect(driver,url,user,passwd);
		Statement st = dbQuery_st(con);
		ResultSet rs = dbQuery_rs(st,str5);
		dbShowData(rs);
		
		
		rs.close();
		st.close();
		con.close();
	}

}

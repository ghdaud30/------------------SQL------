package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MYJDBC {
	
	private String driver;
	private String url;
	private String user;
	private String pwd;
	
	
	public MYJDBC(String driver, String url, String user, String pwd) throws ClassNotFoundException {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.pwd = pwd;	
	}
	
	private Connection sqlConnection(String url ,String user, String pwd) throws SQLException {
		return DriverManager.getConnection(url, user, pwd);
	}
	
public List<List<String>> queryWithPreparedStatement(String query, String...args) throws SQLException{
		
		try {
			List<List<String>> result = new ArrayList<>();
			Connection con =  sqlConnection(url,user,pwd);
			PreparedStatement pst = con.prepareStatement(query);
			
			String[] argument = args;  
			
			for(int i = 1; i <= args.length; i++) {
				pst.setString(i, argument[i-1]);
				System.out.println("The " + i + " arg is set.");
			}
			System.out.println("");
			
			ResultSet rs = pst.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// 질의 결과 컬럼 정보를 저장
			int count = rsmd.getColumnCount();
			List<String> brr = new ArrayList<>(count);
			int[] title = new int[count];
			
			for(int i = 1; i <= count; i ++) {
				brr.add(rsmd.getColumnName(i));
			}
			result.add(brr);
			
			while(rs.next()) {
				List<String> arr = new ArrayList<>(count);
				
				for(int i = 1; i <= count; i++) {
					arr.add(rs.getString(i));
				}
				result.add(arr);
		
			}
			rs.close();				
			pst.close();
			con.close();
			
			return result;
			
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return null;	
	}
	
public int query_update_WithStatement(String query) throws SQLException{
	
	try {
		List<List<String>> result = new ArrayList<>();
		Connection con =  sqlConnection(url,user,pwd);
		Statement st = con.createStatement();
		int rs = st.executeUpdate(query);
		

		st.close();
		con.close();	
		
		return rs;
		
	}catch(Exception e) {
		e.getStackTrace();
	}
	return -1;
}

	public List<List<String>> queryWithStatement(String query) throws SQLException{
		
		try {
			List<List<String>> result = new ArrayList<>();
			Connection con =  sqlConnection(url,user,pwd);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			ResultSetMetaData rsmd = rs.getMetaData();
			int count = rsmd.getColumnCount();
			List<String> brr = new ArrayList<>(count);
//			int[] title = new int[count];
//			
//			for(int i = 1; i <= count; i ++) {
//				title[i-1] = rsmd.getColumnDisplaySize(i) > rsmd.getColumnName(i).length()
//						? rsmd.getColumnDisplaySize(i): rsmd.getColumnName(i).length() + 1;
//				
//				System.out.print(String.format("%-" + title[i-1] + "s",  rsmd.getColumnName(i)));
//			}
			
			// 질의 결과 컬럼 정보를 저장
			for(int i = 1; i <= count; i ++) {
				brr.add(rsmd.getColumnName(i));
			}
			result.add(brr);
					
			
			while(rs.next()) {
				List<String> arr = new ArrayList<>(count);
				
				for(int i = 1; i <= count; i++) {
					arr.add(rs.getString(i));
				}
				result.add(arr);
			}
			
			rs.close();
			st.close();
			con.close();	
			
			return result;
		}catch(Exception e) {
			e.getStackTrace();
		}
		
		return null;	
	}
	
	public void printList(List<List<String>> list) {
		for(List<String> arr : list) {
			for(String i : arr) {
				System.out.print(i + " ");
			}
			
//			for(int i = 1; i < arr.size(); i++) {
//				System.out.print(arr.get(i) + " ");
//			}
			
			System.out.println();
		System.out.println();
		}
		System.out.println("_".repeat(50));
		System.out.println();
	}
}

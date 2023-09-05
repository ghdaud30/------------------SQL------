package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class QueryByStatment {

	public static void main(String[] args) {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/world";
			String username = "scott";
			String password = "tiger";
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection success");
			
			String str = "select id,name,countrycode,district,population from city";
			String del = "\t";
			
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(str); 
			
			
			while(rs.next()) {
//				System.out.print(rs.getString("id")+ del);
//				System.out.print(rs.getString("name")+ del);
//				System.out.print(rs.getString("countrycode")+ del);
//				System.out.print(rs.getString("district")+ del);
//				System.out.print(rs.getString("population")+"\n");
				System.out.print(String.format("%10d", rs.getInt("id")));
				System.out.print(String.format("%20s", rs.getString("name")));
				System.out.print(String.format("%10s", rs.getString("countrycode")));
				System.out.print(String.format("%20s", rs.getString("district")));
				System.out.print(String.format("%10d", rs.getInt("population")) + "\n");
			}
			rs.close();
			st.close();
			con.close();
			
		}catch (Exception e) {
			System.out.println("Connection failure : " + e.getMessage());
		}

	}

}

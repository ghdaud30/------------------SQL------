package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.CallableStatement;
import java.util.Scanner;

public class StoredProcedureQuery2 {

	static Connection getConnection() throws ClassNotFoundException, SQLException {
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/world";
		String user = "scott";
		String passwd = "tiger";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, user, passwd);

		return con;
	}

	public static void executeProcedure(Connection con, String country) throws SQLException {

		String str = "call nationLanguage02(?)";

		CallableStatement cs = con.prepareCall(str);
		cs.setString(1, country);

		ResultSet rs = cs.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		if (rsmd.getColumnCount() > 0) {
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println("");
				System.out.println("");
			}
		}
		System.out.println("_".repeat(30));
		rs.close();
		cs.close();
	}
	
	public static void executeProcedure2(Connection con, String country) throws SQLException {

		String str = "call nationLanguage03(?,?)";

		CallableStatement cs = con.prepareCall(str);
		cs.setString(1, country);
		cs.registerOutParameter(2,Types.DECIMAL);
		
		ResultSet rs = cs.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		int count = rsmd.getColumnCount();
		
		if (rsmd.getColumnCount() > 0) {
			while (rs.next()) {
				for (int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println("");
				System.out.println("");
			}
		}
		System.out.println(cs.getFloat(2));
		System.out.println("_".repeat(30));
		rs.close();
		cs.close();
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		Scanner sc = new Scanner(System.in);
		System.out.print("write the country u want: ");
		String country = sc.nextLine();
		System.out.println("");
		
		Connection con = getConnection();
		
		executeProcedure(con, country);
		executeProcedure2(con, country);
		
		con.close();
	}

}

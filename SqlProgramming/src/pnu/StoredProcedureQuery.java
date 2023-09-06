package pnu;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.ResultSetMetaData;
import java.sql.CallableStatement;

public class StoredProcedureQuery {
	
	public static Connection sqlConnection(String driver, String url, String user, String passwd) throws SQLException, ClassNotFoundException {
		
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,passwd);
			return con;
			
		}catch ( Exception e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void executeProcedure1(Connection con) throws SQLException {
		CallableStatement cs = con.prepareCall("CALL MyFirst(?)");
		
		cs.setString(1, "user3%");
		
		ResultSet rs = cs.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
		
		while(rs.next()) {
			for(int i = 1; i <= count; i++) {
				System.out.print(rs.getString(i) + " ");
			}
			System.out.println("");
			System.out.println("");
		}
		System.out.println("_".repeat(60));
		System.out.println("");
		rs.close();
		cs.close();
	}
	
	public static void executeProcedure2(Connection con) throws SQLException {
		
		String sql = "CALL myStoredProc2(?,?)";
		
		CallableStatement cs = con.prepareCall(sql);
			
		cs.setString(1, "user3%");
		cs.registerOutParameter(2, Types.INTEGER);
		
		ResultSet rs = cs.executeQuery();

		ResultSetMetaData rsmd = rs.getMetaData();
        int count = rsmd.getColumnCount();
		if(rsmd.getColumnCount() > 0) {
			while(rs.next()) {
				for(int i = 1; i <= count; i++) {
					System.out.print(rs.getString(i) + " ");
				}
				System.out.println("");
				System.out.println("");
			}
		}
		System.out.println(cs.getInt(2));
		rs.close();
		cs.close();
	}
	
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		String driver = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/sampledb";
		String user = "scott";
		String passwd = "tiger";
		Connection con;
		
		con = sqlConnection(driver, url, user, passwd);
		executeProcedure1(con);
		
		url = "jdbc:mysql://localhost:3306/sqltest";
		con = sqlConnection(driver, url, user, passwd);
		executeProcedure2(con);
		
		con.close();
		
	}
	
}

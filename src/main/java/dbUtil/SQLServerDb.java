package dbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

import commoncomponents.PropertiesUtility;
import dataconstants.Constants;

public class SQLServerDb implements DataBase {

	private String dbServerIP;
	private String dbPort;
	private String username;
	private String password;
	private String dbName;

	public SQLServerDb() {
		dbServerIP = PropertiesUtility.getProperty(Constants.SERVER);
		dbPort = PropertiesUtility.getProperty(Constants.PORT);
		username = PropertiesUtility.getProperty(Constants.USERNAME);
		password = PropertiesUtility.getProperty(Constants.PASSWORD);
		dbName = PropertiesUtility.getProperty(Constants.DBNAME);
	}

	@Override
	public synchronized ArrayList<ArrayList<String>> getSqlResultInMap(String sql) {
		ArrayList<ArrayList<String>> data_map = new ArrayList<>();

		try {

			Connection con = DriverManager.getConnection(getFormatttedURL());

			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData md = rs.getMetaData();

			while (rs.next()) {
				ArrayList<String> list = new ArrayList<>();
				for (int i = 1; i <= md.getColumnCount(); i++) {
					list.add(rs.getString(i));
				}
				data_map.add(list);
			}

			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return data_map;
	}

	public String getFormatttedURL() {
		String dburl = String.format("jdbc:sqlserver://%s:%s;user=%s;password=%s;databaseName=%s", dbServerIP, dbPort, dbName,username,password);
		System.out.println(dburl);
		return dburl;

	}


}

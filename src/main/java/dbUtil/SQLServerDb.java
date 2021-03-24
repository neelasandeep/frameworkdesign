package dbUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import com.zaxxer.hikari.HikariDataSource;

import commoncomponents.PropertiesUtility;
import dModel.DatabaseModel;
import dataconstants.Constants;
import dataconstants.SqlConstants;

public class SQLServerDb {

	private static String dbServerIP;
	private static String dbPort;
	private static String username;
	private static String password;
	private static String dbName;

	ResultSet rs;
	private static HikariDataSource dataSource;

	static {
		dbServerIP = PropertiesUtility.getProperty(Constants.SERVER);
		dbPort = PropertiesUtility.getProperty(Constants.PORT);
		username = PropertiesUtility.getProperty(Constants.USERNAME);
		password = PropertiesUtility.getProperty(Constants.PASSWORD);
		dbName = PropertiesUtility.getProperty(Constants.DBNAME);
		try {

			dataSource = new HikariDataSource();
			dataSource.setJdbcUrl(getFormatttedURL());
			dataSource.setUsername(username);
			dataSource.setPassword(password);

			dataSource.setMinimumIdle(1);
			dataSource.setMaximumPoolSize(2);// The maximum number of connections, idle or busy, that can be present in
												// the pool.
			// dataSource.setAutoCommit(false);
			dataSource.setLoginTimeout(3);
			dataSource.setIdleTimeout(60000);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static DataSource fromConnectionPool() {
		return dataSource;
	}

	public List<DatabaseModel> executeQuery(String sql) {
		List<DatabaseModel> resultList = new ArrayList<>();
		try (Connection connection = fromConnectionPool().getConnection();) {

			System.out.println(connection.getClass());
			Statement stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			resultList.addAll(buildDataModelFromResult(rs));

		} catch (Exception e) {
			System.out.println(e);
		}
		return resultList;

	}

	private List<DatabaseModel> buildDataModelFromResult(ResultSet result) {
		List<DatabaseModel> resultlist = new ArrayList<>();
		try {
			while (result.next()) {
				resultlist.add(buildDatabaseModel(result));
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return resultlist;
	}

	private DatabaseModel buildDatabaseModel(ResultSet result) {

		DatabaseModel dbModel = new DatabaseModel();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyy HH:mm:ss");
		try {
			ResultSetMetaData mData = result.getMetaData();
			for (int i = 1; i <= mData.getColumnCount(); i++) {
				String name = mData.getColumnLabel(i);
				String type = mData.getColumnTypeName(i);
				if (SqlConstants.VARCHAR.equals(type)) {
					dbModel.addModelProperty(name, result.getString(name));
				} else if (SqlConstants.TIMESTAMP.equals(type)) {
					Date value = result.getTimestamp(name);
					dbModel.addModelProperty(name, (value == null) ? "" : dateFormat.format(value));
				} else if (SqlConstants.INT.equals(type)) {
					int value = result.getInt(name);
					dbModel.addModelProperty(name, String.valueOf(value));
				} else {
					dbModel.addModelProperty(name, result.getObject(name));
				}
			}

		} catch (SQLException e) {

		}

		return dbModel;

	}

	public int executeUpdate(String sql) {
		int result = 0;
		try (Connection connection = fromConnectionPool().getConnection();) {

			Statement stmt = connection.createStatement();
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println(e);
		}
		return result;

	}

	public static String getFormatttedURL() {
		String dburl = String.format("jdbc:sqlserver://%s:%s;user=%s;password=%s;databaseName=%s", dbServerIP, dbPort,
				dbName, username, password);
		System.out.println(dburl);
		return dburl;

	}

}

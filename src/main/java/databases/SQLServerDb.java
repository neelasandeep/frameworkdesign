package databases;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariDataSource;

import constants.ApplicationConstants;
import constants.SqlConstants;
import models.dbmodel.DatabaseModel;
import utilities.PropertiesUtility;

public class SQLServerDb {
	private static Logger logger = LogManager.getLogger();

	private static String dbServerIP;
	private static String dbPort;
	private static String username;
	private static String pwdd;
	private static String dbName;

	ResultSet rs;
	private static HikariDataSource dataSource;

	static {
		dbServerIP = PropertiesUtility.getProperty(ApplicationConstants.SERVER);
		dbPort = PropertiesUtility.getProperty(ApplicationConstants.PORT);
		username = PropertiesUtility.getProperty(ApplicationConstants.USERNAME);
		pwdd = PropertiesUtility.getProperty(ApplicationConstants.PWDD);
		dbName = PropertiesUtility.getProperty(ApplicationConstants.DBNAME);
		try {

			dataSource = new HikariDataSource();
			dataSource.setJdbcUrl(getFormatttedURL());
			dataSource.setUsername(username);
			dataSource.setPassword(pwdd);

			dataSource.setMinimumIdle(1);
			dataSource.setMaximumPoolSize(2);// The maximum number of connections, idle or busy, that can be present in
												// the pool.
			
			dataSource.setLoginTimeout(3);
			dataSource.setIdleTimeout(60000);

		} catch (SQLException e) {
			logger.info(e);
		}
	}

	public static DataSource fromConnectionPool() {
		return dataSource;
	}

	public List<DatabaseModel> executeQuery(String sql) {
		List<DatabaseModel> resultList = new ArrayList<>();
		try (Connection connection = fromConnectionPool().getConnection();
				Statement stmt = connection.createStatement();) {

			logger.info(connection.getClass());
			
			rs = stmt.executeQuery(sql);
			resultList.addAll(buildDataModelFromResult(rs));

		} catch (Exception e) {
			logger.info(e);
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

			logger.info(e);
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
			logger.info(e);
		}

		return dbModel;

	}

	public int executeUpdate(String sql) {
		int result = 0;
		try (Connection connection = fromConnectionPool().getConnection();
				Statement stmt = connection.createStatement();) {

			
			result = stmt.executeUpdate(sql);

		} catch (Exception e) {
			logger.info(e);
		}
		return result;

	}

	public static String getFormatttedURL() {
		String dburl = String.format("jdbc:sqlserver://%s:%s;user=%s;password=%s;databaseName=%s", dbServerIP, dbPort,
				dbName, username, pwdd);
		logger.info(dburl);
		return dburl;

	}

}

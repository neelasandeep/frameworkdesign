package dataBases;

import javax.sql.DataSource;

public interface DataBase {
	public DataSource fromConnectionPool();

}

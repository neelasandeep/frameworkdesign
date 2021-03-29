package dataBases;

import java.util.ArrayList;
import java.util.List;

import models.dbModel.DatabaseModel;

public class CommonDBOperation {
	MySqlDb con;

	public CommonDBOperation() {
		con = new MySqlDb();
	}

	public ArrayList<String> getEmployeeDetailsfromDB(String sql) {
		ArrayList<String> result = new ArrayList<>();
		List<DatabaseModel> listofEmployees = con.executeQuery(sql);
		for (DatabaseModel databaseModel : listofEmployees) {
			result.add(databaseModel.getValueForProperty("username") == null ? ""
					: databaseModel.getValueForProperty("username").toString() + "-"
							+ databaseModel.getValueForProperty("password").toString());
		}
		return result;
	}

}

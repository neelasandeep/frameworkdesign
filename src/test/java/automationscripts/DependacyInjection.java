package automationscripts;

public class DependacyInjection {
private String sum;

public String getSum() {
	return sum;
}

public void setSum(String sum) {
	this.sum = sum;
}

public ExcelTest getExcel() {
	return new ExcelTest();
}


}

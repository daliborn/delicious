package domain;

import java.sql.Timestamp;

public class CheckStatus {
	private Integer id;
	private Integer statusCode;
	private Timestamp dateRun;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public Timestamp getDateRun() {
		return dateRun;
	}
	public void setDateRun(Timestamp dateRun) {
		this.dateRun = dateRun;
	}
}

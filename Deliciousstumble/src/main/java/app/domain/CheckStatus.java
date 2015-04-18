package app.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CheckStatus implements Serializable {
	public CheckStatus(Integer id, Integer statusCode, Timestamp dateRun) {
		super();
		this.id = id;
		this.statusCode = statusCode;
		this.dateRun = dateRun;
	}
	
	protected CheckStatus(){		
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private Integer statusCode;
	@Column
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

	@Override
	public String toString() {
		return String.format("CheckStatus [id=%s, statusCode=%s, dateRun=%s]",
				id, statusCode, dateRun);
	}
}

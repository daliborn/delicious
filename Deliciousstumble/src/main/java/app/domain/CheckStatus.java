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
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4661126982786046555L;

	public CheckStatus(Post post, Integer statusCode, Timestamp dateRun) {
		super();
		this.statusCode = statusCode;
		this.dateRun = dateRun;
		this.post = post;
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
	@Column
	private Post post;
	
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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Override
	public String toString() {
		return String.format("CheckStatus [id=%s, statusCode=%s, dateRun=%s]",
				id, statusCode, dateRun);
	}
}

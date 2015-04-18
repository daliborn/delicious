package app.domain;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class CheckStatus implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -4661126982786046555L;

	public CheckStatus(Post post, Integer statusCode, Timestamp dateRun, String errorCode) {
		super();
		this.statusCode = statusCode;
		this.dateRun = dateRun;
		this.post = post;
		this.errorCode = errorCode;
	}
	
	protected CheckStatus(){		
	}
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column
	private String errorCode;
	
	@Column
	@JsonView(View.Summary.class)
	private Integer statusCode;
	@Column
	private Timestamp dateRun;
	
	@ManyToOne(fetch=FetchType.LAZY)
    public Post post;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
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
		return String
				.format("CheckStatus [id=%s, errorCode=%s, statusCode=%s, dateRun=%s, post=%s]",
						id, errorCode, statusCode, dateRun, post);
	}
}

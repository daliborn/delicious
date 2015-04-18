package app.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.fasterxml.jackson.annotation.JsonView;

import app.dto.DateAdapter;


@Entity
public class Post implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4375101784885522125L;

	public Post(String description, String extended, String hash,
			String href, String privatea, String shared, String tag,
			Timestamp time, String meta, Integer others) {
		super();
		this.description = description;
		this.extended = extended;
		this.hash = hash;
		this.href = href;
		this.privatea = privatea;
		this.shared = shared;
		this.tag = tag;
		this.time = time;
		this.meta = meta;
		this.others = others;
	}
	
	protected Post(){
		
	}

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column
	private String description;
	@Column ( length = 10000 )
	private String extended;
	@Column
	private String hash;
	@Column( length = 10000 )
	@JsonView(View.Summary.class)
	private String href;
	@Column
	private String privatea;
	@Column
	private String shared;
	@Column
	private String tag;
	@Column
	private Timestamp time;
	@Column
	private String meta;
	@Column
	private Integer others;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="post")
    private List<CheckStatus> checkStatus;

	public String getDescription() {
		return description;
	}
	

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtended() {
		return extended;
	}
	

	public void setExtended(String extended) {
		this.extended = extended;
	}

	public String getHash() {
		return hash;
	}


	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHref() {
		return href;
	}
	

	public void setHref(String href) {
		this.href = href;
	}

	public String getPrivatea() {
		return privatea;
	}
	

	public void setPrivatea(String privatea) {
		this.privatea = privatea;
	}

	public String getShared() {
		return shared;
	}
	

	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getTag() {
		return tag;
	}
	

	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getTime() {
		return time;
	}
	
	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getMeta() {
		return meta;
	}
	
	public void setMeta(String meta) {
		this.meta = meta;
	}

	public Integer getOthers() {
		return others;
	}


	public void setOthers(Integer others) {
		this.others = others;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<CheckStatus> getCheckStatus() {
		return checkStatus;
	}
	
	public void setCheckStatus(List<CheckStatus> checkStatus) {
		this.checkStatus = checkStatus;
	}

	@Override
	public String toString() {
		return String
				.format("Post [id=%s, description=%s, extended=%s, hash=%s, href=%s, privatea=%s, shared=%s, tag=%s, time=%s, meta=%s, others=%s, checkStatus=%s]",
						id, description, extended, hash, href, privatea,
						shared, tag, time, meta, others, checkStatus);
	}


}

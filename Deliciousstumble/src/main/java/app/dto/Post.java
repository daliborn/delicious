package app.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


@XmlRootElement(name = "post")
public class Post implements Serializable{
	
	public Post() {

	}
	
	private String description;

	private String extended;

	private String hash;

	private String href;

	private String privatea;

	private String shared;

	private String tag;

	private Timestamp time;

	private String meta;

	private Integer others;

	public String getDescription() {
		return description;
	}
	
	@XmlAttribute( name = "description")
	public void setDescription(String description) {
		this.description = description;
	}

	public String getExtended() {
		return extended;
	}
	
	@XmlAttribute( name = "extended")
	public void setExtended(String extended) {
		this.extended = extended;
	}

	public String getHash() {
		return hash;
	}

	@XmlAttribute( name = "hash")
	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getHref() {
		return href;
	}
	
	@XmlAttribute( name = "href")
	public void setHref(String href) {
		this.href = href;
	}

	public String getPrivatea() {
		return privatea;
	}
	
	@XmlAttribute( name = "private")
	public void setPrivatea(String privatea) {
		this.privatea = privatea;
	}

	public String getShared() {
		return shared;
	}
	
	@XmlAttribute( name = "shared")
	public void setShared(String shared) {
		this.shared = shared;
	}

	public String getTag() {
		return tag;
	}
	
	@XmlAttribute( name = "tag")
	public void setTag(String tag) {
		this.tag = tag;
	}

	public Timestamp getTime() {
		return time;
	}
	
	@XmlAttribute( name = "time")
	@XmlJavaTypeAdapter(DateAdapter.class)
	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getMeta() {
		return meta;
	}
	
	@XmlAttribute( name = "meta")
	public void setMeta(String meta) {
		this.meta = meta;
	}

	public Integer getOthers() {
		return others;
	}

	@XmlAttribute( name = "others")
	public void setOthers(Integer others) {
		this.others = others;
	}


	@Override
	public String toString() {
		return String
				.format("Post [description=%s, extended=%s, hash=%s, href=%s, privatea=%s, shared=%s, tag=%s, time=%s, meta=%s, others=%s]",
						description, extended, hash, href, privatea,
						shared, tag, time, meta, others);
	}

}

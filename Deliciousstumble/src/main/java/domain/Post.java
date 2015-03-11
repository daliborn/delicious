package domain;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
public class Post {

	private String description;

	private String extended;

	private String hash;

	private String href;

	private String privatea;

	private String shared;

	private String tag;

	private String time;
	
	private String meta;
	
	private Integer others;

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
	
	@XmlAttribute( name = "href")
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
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

}

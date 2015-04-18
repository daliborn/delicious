package app.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import app.dto.Post;

@XmlRootElement(name = "posts")
public class Posts {

	private List<Post> post;

	private String tag;

	private String user;

	private Integer total;


	public List<Post> getPost() {
	     return this.post;
	}

	@XmlElement( name = "post" )
	public void setPost(List<Post> post) {
		this.post = post;
	}

	public String getTag() {
		return tag;
	}

	@XmlAttribute( name = "tag")
	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUser() {
		return user;
	}

	@XmlAttribute( name = "user")
	public void setUser(String user) {
		this.user = user;
	}

	public Integer getTotal() {
		return total;
	}

	@XmlAttribute( name = "total")
	public void setTotal(Integer total) {
		this.total = total;
	}

}

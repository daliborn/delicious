package app.service;

import java.util.List;

import domain.Post;

public interface DaoService {
	public void createPosts(List<Post> postsList);
}

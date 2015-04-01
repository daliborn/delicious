package app.service;

import java.util.List;

import domain.Post;

public interface PostsService {
	public void createPosts(List<Post> postsList);
	public List<Post> getAllPosts();
	public void updatePost(Post post);
	public Post getPostById(Integer postId);
}

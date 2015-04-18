package app.service;

import java.util.List;

import app.domain.Post;

/**
 * @author daliborn
 *	class used for interaction with Post objects stored locally
 */
public interface PostsService {
	public void createPosts(List<Post> postsList);
	public List<Post> getAllPosts();
	public void updatePost(Post post);
	public Post getPostById(Long postId);
}

package app.service;

import java.util.List;

import app.domain.Post;
import app.service.util.AppHttpStatus;

/**
 * @author daliborn
 *	class used for interaction with Post objects stored locally
 */
public interface PostsService {
	public void createPosts(List<Post> postsList);
	public List<Post> fetchPosts(AppHttpStatus httpStatus);
	public void updatePost(Post post);
	public Post getPostById(Long postId);
}

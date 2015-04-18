package app.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.PostsRepository;
import app.domain.Post;
import app.service.PostsService;

@Service
@Qualifier("postsService")
public class PostsServiceImpl implements PostsService {
	private static final Logger logger = Logger
			.getLogger(PostsServiceImpl.class.getName());

	@Autowired
	PostsRepository repository;

	@Override
	public void createPosts(List<Post> postsList) {
		repository.save(postsList);
	}

	@Override
	public List<Post> getAllPosts() {
		return (List<Post>) repository.findAll();

	}

	@Override
	public void updatePost(Post post) {

	}

	@Override
	public Post getPostById(Long postId) {
		return repository.findOne(postId);
	}

}

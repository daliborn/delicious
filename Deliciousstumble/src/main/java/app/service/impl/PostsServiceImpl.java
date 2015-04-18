package app.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.database.CheckStatusRepository;
import app.database.PostsRepository;
import app.domain.CheckStatus;
import app.domain.Post;
import app.service.PostsService;
import app.service.util.AppHttpStatus;

@Service
@Qualifier("postsService")
public class PostsServiceImpl implements PostsService {
	private static final Logger logger = Logger
			.getLogger(PostsServiceImpl.class.getName());

	@Autowired
	private PostsRepository postsRepository;
	
	@Autowired
	private CheckStatusRepository checkStatusRepository;

	@Override
	public void createPosts(List<Post> postsList) {
		postsRepository.save(postsList);
	}

	@Override
	public List<Post> fetchPosts(AppHttpStatus httpStatus) {
		switch (httpStatus) {
			case ALL:{
				List<Post> posts =  (List<Post>) postsRepository.findAll();
				posts.removeAll(Collections.singleton(null));
				return posts;
			}
			
			case NOT_FOUND:{
				List<CheckStatus> statuses =  checkStatusRepository.findByStatusCode(AppHttpStatus.NOT_FOUND.getStatusCode());
				List<Post> posts = new ArrayList<Post>();
				for(CheckStatus status : statuses){		
					posts.add(status.getPost());
				}
				return posts;
			}
		}
		return null;


	}

	@Override
	public void updatePost(Post post) {

	}

	@Override
	public Post getPostById(Long postId) {
		return postsRepository.findOne(postId);
	}

}

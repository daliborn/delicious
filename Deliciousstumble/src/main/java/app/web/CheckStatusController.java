package app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.domain.CheckStatus;
import app.domain.Post;
import app.service.CheckStatusService;
import app.service.PostsService;
import app.service.WebService;


@RestController
public class CheckStatusController {
	@Autowired
	private CheckStatusService checkStatusService;
	
	@Autowired
	private PostsService postsService;
	
	@Autowired 
	private WebService webService;
	
	
	@RequestMapping(value = "post/{postId}/createCheckStatus", method = RequestMethod.POST)
	public void createCheckStatus (@PathVariable Integer postId) {
		Post post = postsService.getPostById(postId);		
		CheckStatus status = webService.checkUrl(post);
		checkStatusService.createCheckStatus(status);
	}
	
	@RequestMapping(value = "post/all/createCheckStatus", method = RequestMethod.POST)
	public void checkStatuses () {
		List<Post> posts = postsService.getAllPosts();
		
	}

}

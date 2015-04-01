package app.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import app.service.CheckStatusService;
import app.service.PostsService;
import app.service.WebService;
import domain.CheckStatus;
import domain.Post;


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
		checkStatusService.createCheckStatus(post, status);
	}

}

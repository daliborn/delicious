package app.web;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import app.domain.Post;
import app.domain.View;
import app.service.PostsService;
import app.service.util.AppHttpStatus;

@RestController
public class PostsController {
	@Autowired
	private PostsService postsService;	
	
	@JsonView(View.Summary.class)
	@RequestMapping(value = "post/all/dead", method = RequestMethod.GET)
	public List<Post> showAllDead() {		
		return  postsService.fetchPosts(AppHttpStatus.NOT_FOUND);

	}
	
	
}

package app.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.CheckStatus;
import domain.Post;
import app.service.CheckStatusService;


@RestController
public class CheckStatusController {
	@Autowired
	private CheckStatusService checkStatusService;
	
	
	@RequestMapping("createCheckStatus")
	public void createCheckStatus () {
		Post post = null;
		CheckStatus status = null;
		checkStatusService.createCheckStatus(post, status);
	}

}

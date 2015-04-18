package app.service.util;

import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import app.database.CheckStatusRepository;
import app.domain.CheckStatus;
import app.domain.Post;
import app.service.WebService;

@Component
@Scope("prototype")
public class UrlCheckerConsumer implements Runnable {
	public UrlCheckerConsumer() {

	}

	private static final Logger logger = Logger
			.getLogger(UrlCheckerConsumer.class.getName());

	@Autowired
	private WebService webService;
	
	 @Autowired
	 private CheckStatusRepository repository;

	private ArrayBlockingQueue<Post> queue;


	@Override
	public void run() {

		while (!queue.isEmpty()) {
			Post post = queue.poll();
			CheckStatus status = webService.checkUrl(post);
			repository.save(status);

		}
		logger.info("check link Thread finshied");

	}

	public WebService getWebService() {
		return webService;
	}

	public void setWebService(WebService webService) {
		this.webService = webService;
	}

	public ArrayBlockingQueue<Post> getQueue() {
		return queue;
	}

	public void setQueue(ArrayBlockingQueue<Post> queue) {
		this.queue = queue;
	}

}

package app.service.impl;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import app.database.CheckStatusRepository;
import app.database.PostsRepository;
import app.domain.CheckStatus;
import app.domain.Post;
import app.service.CheckStatusService;
import app.service.WebService;
import app.service.util.UrlCheckerConsumer;

@Service
@Qualifier("checkStatusService")
public class CheckStatusServiceImpl implements CheckStatusService {
	private static final Logger logger = Logger.getLogger(CheckStatusServiceImpl.class.getName());

	private static final int URLCHECK_THREAD_NUM = 10;
	
	 @Autowired
	 private CheckStatusRepository repository;
	 
	 @Autowired
	 private PostsRepository postsRepository;
	 
	 @Autowired
	 private WebService webService;
	 
	 @Autowired
	 private TaskExecutor taskExecutor;
	 
	 @Autowired
	 private UrlCheckerConsumer urlCheckerConsumer;

	@Override
	public CheckStatus checkUrl(Post post) {
		CheckStatus status = webService.checkUrl(post);
		return repository.save(status);
	}

	@Override
	public void createCheckUrlBatch(List<Post> posts) {
		ArrayBlockingQueue<Post> postsQueue = new ArrayBlockingQueue<Post>(posts.size(), true, posts);
		
		for(int i = 0; i<URLCHECK_THREAD_NUM;i++){
			urlCheckerConsumer.setQueue(postsQueue);
			taskExecutor.execute(urlCheckerConsumer);		
		}
		
	}

}

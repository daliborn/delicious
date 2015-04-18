package app.service.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import app.domain.CheckStatus;
import app.domain.Post;
import app.service.WebService;

public class UrlCheckerConsumer implements Runnable {
	private static final Logger logger = Logger.getLogger(UrlCheckerConsumer.class.getName());
	
	 @Autowired
	 private WebService webService;
	
	private ArrayBlockingQueue<Post> queue;
	private ArrayBlockingQueue<CheckStatus> checkStatusesQueue;

	public UrlCheckerConsumer(ArrayBlockingQueue<Post> queue, ArrayBlockingQueue<CheckStatus> checkStatusesQueue) {
		this.checkStatusesQueue = checkStatusesQueue; 
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			while( ! queue.isEmpty()){				
				Post post =  queue.poll();				
				CheckStatus status = webService.checkUrl(post);
	
				checkStatusesQueue.add(status);				
			}
			logger.info("Thread finshied");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

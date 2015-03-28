package app.service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;

import domain.Post;

public class UrlChecker implements Runnable {
	private ArrayBlockingQueue<Post> post;

	public UrlChecker(ArrayBlockingQueue<Post> queue) {
		super();
		this.post = queue;
	}

	@Override
	public void run() {
		URL url;
		try {
			String path = post.poll().getHref();
			url = new URL(path);
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			
			int code = connection.getResponseCode();
			
			
			//Status table?, update existing, response code, date

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

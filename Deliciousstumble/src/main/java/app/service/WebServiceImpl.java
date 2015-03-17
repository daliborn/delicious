package app.service;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import domain.Post;

@Service
@Qualifier("webService")
public class WebServiceImpl implements WebService {
	
	private int webWorkers = 10;

	@Override
	public void openUrlinBrowser(String url) {
        if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(url));
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }
        }else{
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

	}

	@Override
	public void checkUrl(List<Post> posts) {
		ArrayBlockingQueue<Post> queue = new ArrayBlockingQueue<Post>(100, false, posts);
		
		for (int i = 0; i < webWorkers; i++) {
			UrlChecker urlCheck = new UrlChecker(queue);
			urlCheck.run();
					
		}	
	}

}

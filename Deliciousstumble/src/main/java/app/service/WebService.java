package app.service;

import java.io.IOException;

import app.domain.CheckStatus;
import app.domain.Post;


/**
 * @author daliborn
 * class used for interaction with web
 */
public interface WebService {
	public void openUrlinBrowser(String url);
	public CheckStatus checkUrl(Post post) throws IOException;	
}

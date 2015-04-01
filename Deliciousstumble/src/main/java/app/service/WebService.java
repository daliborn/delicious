package app.service;

import java.util.List;

import domain.CheckStatus;
import domain.Post;

public interface WebService {
	public void openUrlinBrowser(String url);
	public void checkUrlBatch(List<Post> posts);
	public CheckStatus checkUrl(Post post);
}

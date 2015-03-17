package app.service;

import java.util.List;

import domain.Post;

public interface WebService {
	public void openUrlinBrowser(String url);
	public void checkUrl(List<Post> posts);
}

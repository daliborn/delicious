package app.service;

import java.util.List;

import app.domain.CheckStatus;
import app.domain.Post;

public interface CheckStatusService {
	public CheckStatus checkUrl(Post post);
	public void createCheckUrlBatch(List<Post> posts);

}

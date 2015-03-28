package app.service;

import java.util.List;

import domain.CheckStatus;
import domain.Post;

public interface CheckStatusService {
	public void createCheckStatus(Post post, CheckStatus status);
	public List<CheckStatus> getStatusesforPost(Post post);

}

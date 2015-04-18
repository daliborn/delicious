package app.service;

import java.util.List;

import app.domain.CheckStatus;
import app.domain.Post;

public interface CheckStatusService {
	public void createCheckStatus(CheckStatus status);
	public List<CheckStatus> getStatusesforPost(Post post);

}

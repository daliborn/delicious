package app.service;

import java.util.List;

import app.domain.Post;

public interface SolrService {
	public void indexDocuments(List<Post> posts);
}

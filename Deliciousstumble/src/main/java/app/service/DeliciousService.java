package app.service;

import java.util.List;

import app.domain.Post;

/**
 * @author daliborn
 * 
 *  class used for interaction with delicious service
 */
public interface DeliciousService {

	List<Post> createList(String body);

}

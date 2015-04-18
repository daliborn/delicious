package app.service.impl;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.domain.Post;
import app.dto.Posts;
import app.service.DeliciousService;

@Service
@Qualifier("deliciousService")
public class DeliciousServiceIml implements DeliciousService {
	private static final Logger logger = Logger.getLogger(DeliciousServiceIml.class.getName());

	@Override
	public List<Post> createList(String body) {
		logger.info("start of writing!");
		try {
			JAXBContext context = JAXBContext.newInstance(Posts.class);
			Unmarshaller um = context.createUnmarshaller();
			Posts posts = (Posts) um.unmarshal(new StringReader(body));
			
			List<app.domain.Post> domainPosts = new ArrayList<Post>();
			
			for (app.dto.Post xmlPost : posts.getPost()){
				app.domain.Post domainPost = new Post(xmlPost.getDescription(), 
						xmlPost.getExtended(), xmlPost.getHash(), 
						xmlPost.getHref(), xmlPost.getPrivatea(), 
						xmlPost.getShared(), xmlPost.getTag(), 
						xmlPost.getTime(), xmlPost.getMeta(), xmlPost.getOthers());
				domainPosts.add(domainPost);
			}
			logger.info("finish of writing!");
			return domainPosts;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;

	}

}

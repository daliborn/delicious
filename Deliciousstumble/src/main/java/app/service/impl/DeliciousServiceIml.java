package app.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.domain.Post;
import app.service.DeliciousService;
import domain.Posts;

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
			return posts.getPost();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;

	}

}

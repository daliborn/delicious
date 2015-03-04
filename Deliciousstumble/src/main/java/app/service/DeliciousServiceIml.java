package app.service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import domain.Post;
import domain.Posts;

@Service
@Qualifier("deliciousService")
public class DeliciousServiceIml implements DeliciousService {

	@Override
	public List<Post> createList(String body) {
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream("delicious.xml"), "utf-8"));
			writer.write(body);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception ex) {
			}
		}

		try {
			JAXBContext context = JAXBContext.newInstance(Posts.class);
			Unmarshaller um = context.createUnmarshaller();
			Posts posts = (Posts) um.unmarshal(new FileReader("delicious.xml"));
			return posts.getPost();
		} catch (JAXBException | FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;

	}

}

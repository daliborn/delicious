package app.service.impl;

import java.awt.Desktop;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import app.domain.CheckStatus;
import app.domain.Post;
import app.service.WebService;

@Service
@Qualifier("webService")
public class WebServiceImpl implements WebService {

	@Override
	public void openUrlinBrowser(String url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			try {
				desktop.browse(new URI(url));
			} catch (IOException | URISyntaxException e) {
				e.printStackTrace();
			}
		} else {
			Runtime runtime = Runtime.getRuntime();
			try {
				runtime.exec("xdg-open " + url);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public CheckStatus checkUrl(Post post) throws IOException {

		String path = post.getHref();

		URL url = new URL(path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();

		int code = connection.getResponseCode();
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());

		CheckStatus status = new CheckStatus(post, code, date);
		return status;
	}

}

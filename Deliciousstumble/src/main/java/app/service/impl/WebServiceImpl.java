package app.service.impl;

import java.awt.Desktop;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
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
	public CheckStatus checkUrl(Post post){
		Date systemDate = new Date();
		Timestamp date = new Timestamp(systemDate.getTime());
		String errorCode = null;

		String path = post.getHref();
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));

		URL url = null;
		try {
			url = new URL(path);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			errorCode = e.getLocalizedMessage();
		}
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) url.openConnection();
		} catch (IOException e) {
			e.printStackTrace();
			errorCode = e.getLocalizedMessage();
		}
		try {
			connection.setRequestMethod("GET");
		} catch (ProtocolException e) {
			e.printStackTrace();
			errorCode = e.getLocalizedMessage();
		}
		try {
			connection.connect();
		} catch (IOException e) {
			e.printStackTrace();
			errorCode = e.getLocalizedMessage();
		}

		int code = 0;
		try {
			code = connection.getResponseCode();
		} catch (IOException e) {
			e.printStackTrace();
			errorCode = e.getLocalizedMessage();
		}


		CheckStatus status = new CheckStatus(post, code, date, errorCode);
		return status;
	}

}

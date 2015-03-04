package app.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.oltu.oauth2.client.OAuthClient;
import org.apache.oltu.oauth2.client.URLConnectionClient;
import org.apache.oltu.oauth2.client.request.OAuthBearerClientRequest;
import org.apache.oltu.oauth2.client.request.OAuthClientRequest;
import org.apache.oltu.oauth2.client.response.OAuthAuthzResponse;
import org.apache.oltu.oauth2.client.response.OAuthResourceResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import domain.DeliciousResponse;
import domain.Post;

@RestController
public class MainController {

	private static final String API_LOCATION = "https://api.delicious.com/v1/posts/all";
	private static final String TOKEN_LOCATION = "https://avosapi.delicious.com/api/v1/oauth/token";
	private static final String AUTHORIZATHION_LOCATION = "https://delicious.com/auth/authorize";
	private static final String CLIENT_SECRET = "4b9c3d1edc298a1600a031b786019744";
	private static final String CLIENT_ID = "5706addbcf9d8726072f4672e875c46e";
	@Autowired
	private app.service.DeliciousService deliciousServis;

	@RequestMapping("/")
	public void index(HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		try {
			OAuthClientRequest request = OAuthClientRequest
					.authorizationLocation(AUTHORIZATHION_LOCATION)
					.setClientId(CLIENT_ID)
					.setRedirectURI("http://localhost:8080/requesttoken")
					.buildQueryMessage();

			servletResponse.sendRedirect(request.getLocationUri());
		} catch (OAuthSystemException | IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/requesttoken")
	public String requesttoken(HttpServletRequest request,
			HttpServletResponse servletResponse) {
		OAuthAuthzResponse oar;
		try {
			oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
			String code = oar.getCode();

			OAuthClientRequest tokenrequest = OAuthClientRequest
					.tokenLocation(TOKEN_LOCATION).setClientId(CLIENT_ID)
					.setClientSecret(CLIENT_SECRET)
					.setRedirectURI("http://localhost:8080/delicious")
					.setCode(code).setParameter("grant_type", "code")
					.buildQueryMessage();

			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			DeliciousResponse response = oAuthClient.accessToken(tokenrequest,
					DeliciousResponse.class);

			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
					API_LOCATION).buildQueryMessage();
			bearerClientRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE,
					"multipart/form-data");
			bearerClientRequest.setHeader("Authorization",
					"Bearer " + response.getAccessToken());

			OAuthResourceResponse resourceResponse = oAuthClient.resource(
					bearerClientRequest, OAuth.HttpMethod.GET,
					OAuthResourceResponse.class);
			
			List<Post> postsList = deliciousServis.createList(resourceResponse.getBody());
			

			return "/delicious";
		} catch (OAuthProblemException | OAuthSystemException e) {
			e.printStackTrace();
		}
		return "greska";

	}

	@RequestMapping("/delicious")
	public String delicious(HttpServletRequest request,
			HttpServletResponse servletResponse) {
		return "bravo";
	}
}

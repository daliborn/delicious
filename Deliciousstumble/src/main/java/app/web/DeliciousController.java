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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.domain.Post;
import app.dto.DeliciousResponse;
import app.service.DeliciousService;
import app.service.PostsService;
import app.service.WebService;

@RestController
public class DeliciousController {
	@Value("${connection.localhost}")
	private String localhost;
	
	@Value("${connection.request_token_url}")
	private String request_token_url;

	@Value("${connection.api_location}")
	private String api_location;
	
	@Value("${connection.token_location}")
	private String token_location;
	
	@Value("${connection.authorizathion_location}")
	private String authorizathion_location;
	
	@Value("${connection.client_secret}")
	private String client_secret;
	
	@Value("${connection.client_id}")
	private String client_id;
	
	@Autowired
	private DeliciousService deliciousService;
	
	@Autowired
	private PostsService postsService;
	
	@Autowired
	private WebService webService;


	
	@RequestMapping("/synchronize")
	public void synchronize(HttpServletRequest servletRequest,
			HttpServletResponse servletResponse) {
		try {
			OAuthClientRequest request = OAuthClientRequest
					.authorizationLocation(authorizathion_location)
					.setClientId(client_id)
					.setRedirectURI(request_token_url)
					.buildQueryMessage();

			servletResponse.sendRedirect(request.getLocationUri());
		} catch (OAuthSystemException | IOException e) {
			e.printStackTrace();
		}

	}

	@RequestMapping("/requesttoken")
	public void requesttoken(HttpServletRequest request) {
		OAuthAuthzResponse oar;
		try {
			oar = OAuthAuthzResponse.oauthCodeAuthzResponse(request);
			String code = oar.getCode();

			OAuthClientRequest tokenrequest = OAuthClientRequest
					.tokenLocation(token_location).setClientId(client_id)
					.setClientSecret(client_secret)
					.setRedirectURI(localhost)
					.setCode(code).setParameter("grant_type", "code")
					.buildQueryMessage();

			OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());

			DeliciousResponse response = oAuthClient.accessToken(tokenrequest,
					DeliciousResponse.class);

			OAuthClientRequest bearerClientRequest = new OAuthBearerClientRequest(
					api_location).buildQueryMessage();
			bearerClientRequest.setHeader(OAuth.HeaderType.CONTENT_TYPE,
					"multipart/form-data");
			bearerClientRequest.setHeader("Authorization",
					"Bearer " + response.getAccessToken());

			OAuthResourceResponse resourceResponse = oAuthClient.resource(
					bearerClientRequest, OAuth.HttpMethod.GET,
					OAuthResourceResponse.class);
			
			List<Post> postsList = deliciousService.createList(resourceResponse.getBody());
			postsService.createPosts(postsList);
		} catch (OAuthProblemException | OAuthSystemException e) {
			e.printStackTrace();
		}
	}
}

package domain;

import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.oltu.oauth2.client.response.OAuthAccessTokenResponse;
import org.apache.oltu.oauth2.common.OAuth;
import org.apache.oltu.oauth2.common.token.BasicOAuthToken;
import org.apache.oltu.oauth2.common.token.OAuthToken;
import org.apache.oltu.oauth2.common.utils.OAuthUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DeliciousResponse extends OAuthAccessTokenResponse {
	private static final String ENCODING = "UTF-8";

	 public String getAccessToken() {
	        return getParam(OAuth.OAUTH_ACCESS_TOKEN);
	    }

	    public Long getExpiresIn() {
	        String value = getParam(OAuth.OAUTH_EXPIRES_IN);
	        return value == null? null: Long.valueOf(value);
	    }

	    public String getRefreshToken() {
	        return getParam(OAuth.OAUTH_EXPIRES_IN);
	    }

	    public String getScope() {
	        return getParam(OAuth.OAUTH_SCOPE);
	    }

	    public OAuthToken getOAuthToken() {
	        return new BasicOAuthToken(getAccessToken(), getExpiresIn(), getRefreshToken(), getScope());
	    }

	    protected void setBody(String body) {
	        this.body = body;
	        
	        
	        if (!OAuthUtils.isEmpty(body)) {
	        	Gson gson = new Gson();
		        DaoJson resp = gson.fromJson(body, DaoJson.class);
		        
		        parameters.put(OAuth.OAUTH_ACCESS_TOKEN, resp.getAccess_token());
		        parameters.put("session", resp.getSession());
		        parameters.put("version", resp.getVersion());
		        parameters.put("api_mgmt_ms", resp.getApi_mgmt_ms());
		        parameters.put("status", resp.getStatus());
		        parameters.put("delta_ms", resp.getDelta_ms());
		        parameters.put("server", resp.getServer());        
		        
	        }
	    }
	    

	    protected void setContentType(String contentType) {
	        this.contentType = contentType;
	    }

	    protected void setResponseCode(int code) {
	        this.responseCode = code;
	    }

}

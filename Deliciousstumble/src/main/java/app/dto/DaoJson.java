package app.dto;

public class DaoJson {
	private String status;
	private Integer delta_ms;
    private String server;
	private String session;
	private Integer api_mgmt_ms;
	
    public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DaoJson [status=" + status + ", delta_ms=" + delta_ms
				+ ", server=" + server + ", session=" + session
				+ ", api_mgmt_ms=" + api_mgmt_ms + ", version=" + version
				+ ", access_token=" + access_token + "]";
	}
	public Integer getDelta_ms() {
		return delta_ms;
	}
	public void setDelta_ms(Integer delta_ms) {
		this.delta_ms = delta_ms;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
	}
	public Integer getApi_mgmt_ms() {
		return api_mgmt_ms;
	}
	public void setApi_mgmt_ms(Integer api_mgmt_ms) {
		this.api_mgmt_ms = api_mgmt_ms;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	private String version;
    private String access_token;

}

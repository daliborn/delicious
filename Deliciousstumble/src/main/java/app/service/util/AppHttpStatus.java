package app.service.util;

public enum AppHttpStatus {
	NOT_FOUND(404), ALL(0);
	private int statusCode;

    private AppHttpStatus(int statusCode) {
            this.statusCode = statusCode;
    }

	public int getStatusCode() {
		return statusCode;
	}

}

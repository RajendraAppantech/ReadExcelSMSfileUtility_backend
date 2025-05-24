package com.sms.client;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;

public class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

	private ClientHttpResponse response;
	private byte[] body;

	public BufferingClientHttpResponseWrapper(ClientHttpResponse response) {
		this.response = response;
	}

	@Override
	public InputStream getBody() throws IOException {
		if (body == null) {
			body = StreamUtils.copyToByteArray(response.getBody());
		}
		return new ByteArrayInputStream(body);
	}

	@Override
	public HttpStatusCode getStatusCode() throws IOException {
		return this.response.getStatusCode();
	}

	@Override
	public int getRawStatusCode() throws IOException {
		return this.response.getRawStatusCode();
	}

	@Override
	public String getStatusText() throws IOException {
		return this.response.getStatusText();
	}

	@Override
	public HttpHeaders getHeaders() {
		return this.response.getHeaders();
	}

	@Override
	public void close() {
		this.response.close();
	}
}
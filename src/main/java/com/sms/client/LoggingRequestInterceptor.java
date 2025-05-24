package com.sms.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class LoggingRequestInterceptor implements ClientHttpRequestInterceptor {
	
	private static final Logger Logger = LoggerFactory.getLogger(LoggingRequestInterceptor.class);

	@Override
	public ClientHttpResponse intercept(final HttpRequest request, final byte[] body,
			final ClientHttpRequestExecution execution) throws IOException {
		traceRequest(request, body);
		ClientHttpResponse response = execution.execute(request, body);
		response = traceResponse(response);
		return response;
	}

	private void traceRequest(HttpRequest request, byte[] body) throws IOException {
		Logger.info("=========================== Client Request Begin ===========================");
		Logger.info("URI          : " + request.getURI());
		Logger.info("Method       : " + request.getMethod());
		Logger.info("Headers      : " + request.getHeaders());
		Logger.info("Body : " + new String(body, "utf-8"));
		Logger.info("============================ Client Request End ============================");
	}

	private ClientHttpResponse traceResponse(ClientHttpResponse response) throws IOException {
		ClientHttpResponse newCopiedResponse = new BufferingClientHttpResponseWrapper(response);
		StringBuilder inputStringBuilder = new StringBuilder();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(newCopiedResponse.getBody(), "UTF-8"));
		String line = bufferedReader.readLine();
		while (line != null) {
			inputStringBuilder.append(line);
			line = bufferedReader.readLine();
		}
		Logger.info("=========================== Client Response Begin ===========================");
		Logger.info("Status code   : " + response.getStatusCode());
		Logger.info("Status text   : " + response.getStatusText());
		Logger.info("Headers       : " + response.getHeaders());
		Logger.info("Response Body : " + inputStringBuilder.toString());
		Logger.info("============================ Client Response End ============================");
		return newCopiedResponse;
	}

}
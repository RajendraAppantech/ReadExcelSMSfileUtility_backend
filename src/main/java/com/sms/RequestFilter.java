package com.sms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class RequestFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);

    @Override
    public void init(FilterConfig config) throws ServletException {
        logger.info("******** Filter Initialized ********");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        // Read and store request body
        byte[] requestBytes = httpRequest.getInputStream().readAllBytes();
        HttpServletRequest wrappedRequest = ServletUtils.getWrappedHttpServletRequest(httpRequest, requestBytes);

        // Log request details
        logger.info("========================== New Request Found =========================");
        logger.info("URI          : {}", httpRequest.getRequestURL());
        logger.info("Method       : {}", httpRequest.getMethod());

        StringBuilder headerBuilder = new StringBuilder();
        Collections.list(httpRequest.getHeaderNames()).forEach(headerName ->
                headerBuilder.append(headerName)
                        .append(": ")
                        .append(httpRequest.getHeader(headerName))
                        //.append("\n")
        );
        
        String requestParams = Collections.list(httpRequest.getParameterNames()).stream()
                .map(paramName -> paramName + "=" + httpRequest.getParameter(paramName))
                .collect(Collectors.joining(", "));
        
        String requestBody = new String(requestBytes, StandardCharsets.UTF_8).replaceAll("\\s+", " ");
        
        logger.info("Headers      : {}", headerBuilder);
        logger.info("Request Parameters : {}", requestParams);
        logger.info("Request Body : {}", requestBody);
        logger.info("============================ Request End ============================");

        // Wrap and capture response
        ByteArrayOutputStream responseOutputStream = new ByteArrayOutputStream();
        HttpServletResponse wrappedResponse = ServletUtils.getWrappedHttpServletResponse(httpResponse, responseOutputStream);

        // Continue filter chain
        chain.doFilter(wrappedRequest, wrappedResponse);

        // Read and log response
        byte[] responseBytes = responseOutputStream.toByteArray();
        logger.info("=========================== Response Begin ==========================");
        logger.info("Status Code   : {}", wrappedResponse.getStatus());
        logger.info("Response Body : {}", new String(responseBytes, StandardCharsets.UTF_8));
        logger.info("============================ Response End ===========================\n");
        // Write response back
        servletResponse.getOutputStream().write(responseBytes);
    }

    @Override
    public void destroy() {
        // No specific cleanup required
    }
}
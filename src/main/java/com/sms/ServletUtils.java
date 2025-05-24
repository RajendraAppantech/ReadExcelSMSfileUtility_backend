package com.sms;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import jakarta.servlet.ReadListener;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.WriteListener;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

public class ServletUtils {
	
	public static final HttpServletRequestWrapper getWrappedHttpServletRequest(ServletRequest servletRequest, byte[] requestBytes) {
		return new HttpServletRequestWrapper((HttpServletRequest) servletRequest) {
			
			@Override
			public ServletInputStream getInputStream() throws IOException {
				return getServletInputStreamFrom(requestBytes);
			}

			@Override
			public BufferedReader getReader() throws IOException {
				return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(requestBytes)));
			}
		};
	}
	
	public static final ServletInputStream getServletInputStreamFrom(final byte[] bytes) {
		return new ServletInputStream() {
			private ByteArrayInputStream baos = new ByteArrayInputStream(bytes);
			
			@Override
			public int read() throws IOException {
				return baos.read();
			}
			
			@Override
			public void setReadListener(ReadListener listener) {
			}
			
			@Override
			public boolean isReady() {
				return false;
			}
			
			@Override
			public boolean isFinished() {
				return false;
			}
		};
	}
	
	public static final HttpServletResponseWrapper getWrappedHttpServletResponse(ServletResponse servletResponse, ByteArrayOutputStream baos) {
		return new HttpServletResponseWrapper((HttpServletResponse) servletResponse) {
			
			@Override
			public PrintWriter getWriter() {
				return new PrintWriter(baos);
			}

			@Override
			public ServletOutputStream getOutputStream() throws IOException {
				return getServletOutputStream(baos);
			}

		};
	}
	
	private static final ServletOutputStream getServletOutputStream(ByteArrayOutputStream baos) {
		return new ServletOutputStream() {
			
			@Override
			public void write(int b) throws IOException {
				baos.write(b);
			}
			
			@Override
			public void setWriteListener(WriteListener listener) {
			}
			
			@Override
			public boolean isReady() {
				return true;
			}
		};
	}
	
	
	
}

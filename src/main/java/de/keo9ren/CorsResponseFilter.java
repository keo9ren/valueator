package de.keo9ren;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

@Provider
public class CorsResponseFilter implements ContainerResponseFilter {

	public static final String ALLOWED_METHODS = "GET, POST, PUT, DELETE, OPTIONS";
	public final static int MAX_AGE = 42 * 60 * 60;
	public final static String DEFAULT_ALLOWED_HEADERS = "origin,accept,content-type";
	public final static String DEFAULT_EXPOSED_HEADERS = "location,info";

	@Override
	public void filter(ContainerRequestContext reqC, ContainerResponseContext respC) throws IOException {

		MultivaluedMap<String, Object> headers = respC.getHeaders();

		headers.add("Access-Control-Allow-Origin", "*");
		headers.add("Access-Control-Allow-Credentials", "true");
		headers.add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
		headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
	}

}

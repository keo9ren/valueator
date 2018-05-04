package de.okay9;

import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("value")
public class ValueatorEndpoint {

	@Inject
	private Logger log;

	@GET
	public String value() {
		log.info("value");
		return "value";
	}

}

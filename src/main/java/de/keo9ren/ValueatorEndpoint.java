package de.keo9ren;

import java.math.BigDecimal;
import java.net.URI;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("value")
public class ValueatorEndpoint {

	@Context
	UriInfo uriInfo;

	@Inject
	CustomerService cs;

	@Inject
	Logger log;

	@GET
	@Path("sum")
	@Produces(MediaType.APPLICATION_JSON)
	public Response sum() {
		BigDecimal income = cs.getIncome();
		JsonObjectBuilder sum = Json.createObjectBuilder().add("sum", income);
		return Response.ok(sum.build()).build();
	}

	@GET
	@Path("val")
	public Response value() {
		JsonObjectBuilder user = Json.createObjectBuilder().add("name", "oliver kehret");
		user.add("accounts", 0);

		URI absPath = uriInfo.getAbsolutePath();
		Link l = Link.fromUri(absPath).rel("self").type("GET").build();
		user.add(//
				"_link",
				Json.createObjectBuilder().add("self", absPath.toString()).add("shares", absPath.toString() + "/shares")//
		);

		return Response.ok(user.build().toString()).links(l).build();
	}

	@GET
	@Path("shares")
	public Response sec() {
		JsonObjectBuilder shares = Json.createObjectBuilder();

		shares.add("book", "Lord of the Rings");

		Link l = Link.fromUri(uriInfo.getBaseUri()).rel("self").type("GET").build();

		shares.add("link", l.toString());

		return Response.ok(shares.build().toString()).links(l).build();
	}

	@POST
	@Path("{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response receive(@PathParam("id") String no) {
		log.info("Received " + no.toString());
		JsonObjectBuilder user = Json.createObjectBuilder().add("no", no);
		return Response.status(200).entity(user.build()).build();
	}

}

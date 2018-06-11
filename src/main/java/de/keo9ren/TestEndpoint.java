package de.keo9ren;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("test")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TestEndpoint {

	@Context
	UriInfo uriInfo;

	@Inject
	Logger log;

	@Inject
	TestService ts;

	@POST
	@Path("balance/income/{income}")
	public Response setIncome(@PathParam("income") BigDecimal income) {
		Balance b = ts.setIncome(income);
		return Response.status(201).entity(b).build();
	}

	@POST
	@Path("balance/expenditure/{expenditure}")
	public Response setExpenditure(@PathParam("expenditure") BigDecimal expenditure) {
		Balance b = ts.setBalance();
		return Response.status(201).entity(b).build();
	}

	@GET
	@Path("balance")
	public Response getBalance() {
		List<Balance> b = ts.getBalance();
		return Response.status(200).entity(b).build();
	}

	@GET
	@Path("get")
	public Response get() {
		LocalTime t = LocalTime.now();
		JsonObject j = Json.createObjectBuilder().add("time", t.toString()).build();
		return Response.ok(j).build();
	}

	@GET
	@Path("id/{id}")
	public Response getId(@PathParam("id") BigDecimal id) {
		JsonObject j = Json.createObjectBuilder().add("id", id.toString()).build();
		return Response.ok(j).build();
	}

	@POST
	@Path("post/{id}")
	public Response post(@PathParam("id") String id) {
		URI idUri = uriInfo.getBaseUriBuilder()//
				.path(TestEndpoint.class)//
				.path(TestEndpoint.class, "getId").build(id);
		JsonObjectBuilder j = Json.createObjectBuilder()//
				.add("_links", Json.createObjectBuilder()//
						.add("self", uriInfo.getAbsolutePath().toString())//
						.add("content-location", idUri.toString()).build());
		return Response.status(201).entity(j.build()).contentLocation(idUri).build();
		// return Response.created(idUri).build();
	}
}

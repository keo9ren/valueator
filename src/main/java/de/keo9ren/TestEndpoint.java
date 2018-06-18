package de.keo9ren;

import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.stream.JsonParser;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.sebastian_daschner.siren4javaee.EntityBuilder;
import com.sebastian_daschner.siren4javaee.Siren;

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

	@GET
	@Path("balance/income")
	public Response getIncomeList() {
		List<Balance> bal = ts.getIncome();

		EntityBuilder eb = Siren.createEntityBuilder()//
				.addClass("incomelist")//
				.addProperty("itemcount", 1);

		bal.stream().map(b -> buildIncomeLinks(b)).forEach(eb::addEntity);

		return Response.ok().entity(eb.build()).build();
	}

	private JsonObject buildIncomeLinks(Balance b) {
		return Siren.createEntityBuilder()//
				.addSubEntityRel("income")// , UriInfo u
				.addProperty("amount", b.getIncome())//
				// .addProperty("date", b.getDate().toString())//
				// .addLink()
				.build();
	}

	// @GET
	// @Path("balance/income")
	// public Response getIncomeList() {
	// // TODO: Return list of all incomes
	// List<Balance> i = ts.getIncome();
	// // TODO: Add actions to update the income
	// JsonArrayBuilder jar = Json.createArrayBuilder();
	// i.forEach((item) -> {
	// JsonObjectBuilder job = Json.createObjectBuilder().add("class", "income");
	// job.add("properties", Json.createObjectBuilder().add("income",
	// item.getIncome()).build());
	// jar.add(job.build());
	// });
	// // TODO: Add action to delete single income
	// return Response.ok().entity(jar.build()).build();
	// }
	//
	@POST
	@Path("balance/income")
	public Response setIncome(InputStream in) {
		JsonParser p = Json.createParser(in);
		BigDecimal income = null;
		BigDecimal timestamp = null;
		while (p.hasNext()) {
			switch (p.next()) {
			case KEY_NAME:
				String key = p.getString();
				p.next();
				switch (key) {
				case "amount":
					income = p.getBigDecimal();
					break;
				case "date":
					timestamp = p.getBigDecimal();
				default:
					break;
				}
			default:
				break;
			}
		}

		Balance b = ts.setIncome(income, timestamp);

		JsonObjectBuilder o = Json.createObjectBuilder();
		o.add("class", "income");
		o.add("properties", Json.createObjectBuilder().add("id", b.getId()).add("income", b.getIncome())
				.add("date", b.getDate().toString()).build());
		URI putHref = uriInfo.getBaseUriBuilder().path(TestEndpoint.class).path(TestEndpoint.class, "updateIncome")
				.build(b.getId());
		URI getHref = uriInfo.getBaseUriBuilder().path(TestEndpoint.class).path(TestEndpoint.class, "getIncome")
				.build(b.getId());
		URI delHref = uriInfo.getBaseUriBuilder().path(TestEndpoint.class).path(TestEndpoint.class, "delIncome")
				.build(b.getId());
		URI getAllHref = uriInfo.getBaseUriBuilder().path(TestEndpoint.class).path(TestEndpoint.class, "getIncomeList")
				.build();
		JsonArray actions = Json.createArrayBuilder().add(Json.createObjectBuilder()//
				.add("method", "GET")//
				.add("href", putHref.toString())//
				.add("type", "application/json")//
		).add(Json.createObjectBuilder().add("method", "PUT")//
				.add("name", "update-income").add("href", getHref.toString())//
				.add("type", "application/json").add("fields", //
						Json.createArrayBuilder().add(//
								Json.createObjectBuilder().add("name", "amount").add("type", "number").build())
								.add(Json.createObjectBuilder().add("name", "date").add("type", "timestamp").build()))//
		).add(Json.createObjectBuilder().add("method", "DELETE")//
				.add("href", delHref.toString())//
				.add("type", "application/json")//
		).add(Json.createObjectBuilder().add("method", "GET")//
				.add("name", "getall-income").add("href", getAllHref.toString())//
				.add("type", "application/json")//
		).build();
		o.add("actions", actions);
		o.add("links", uriInfo.getBaseUriBuilder().path(TestEndpoint.class).path(TestEndpoint.class, "setIncome")
				.build().toString());

		return Response.status(201).entity(o.build()).build();
	}

	@PUT
	@Path("balance/income/{id}")
	public Response updateIncome(@PathParam("id") String id) {

		// TODO: What code to return for put
		return Response.status(200).build();
	}

	@GET
	@Path("balance/income/{id}")
	public Response getIncome(@PathParam("id") String id) {

		// TODO: What code to return for put
		return Response.status(200).build();
	}

	@DELETE
	@Path("balance/income/{id}")
	public Response delIncome(@PathParam("id") String id) {

		// TODO: What code to return for put
		return Response.status(200).build();
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

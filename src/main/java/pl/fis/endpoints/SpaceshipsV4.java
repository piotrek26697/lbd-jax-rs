package pl.fis.endpoints;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.fis.data.Spaceship;
import pl.fis.logic.SpaceFleetHandler;
import pl.fis.logic.errors.ResourceNotFound;
import pl.fis.logic.pagination.Page;
import pl.fis.logic.pagination.SpaceshipsPaginated;

@Api(value = "Space-fleet endpoint. Provides funcionality to operate space fleet")
@Path("/v4/space-fleet")
public class SpaceshipsV4
{
	@Inject
	private SpaceFleetHandler fleetHandler;

	@Inject
	private SpaceshipsPaginated spaceshipsPaginated;

	@ApiOperation(value = "Retrive available ships", notes = "Returns Json format")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpaceFleet()
	{
		CacheControl cc = new CacheControl();
		cc.setMaxAge(60); // 1 minute
		cc.setPrivate(true);

		return Response.ok(fleetHandler.getSpaceFleet()).cacheControl(cc).build();
	}

	@ApiOperation(value = "Add ship to the fleet", notes = "Accepts Json format")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully added ship"),
			@ApiResponse(code = 400, message = "Spaceship invalid", response = ConstraintViolationException.class) })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSpaceship(@Valid Spaceship spaceship, @Context UriInfo uriInfo)
	{
		fleetHandler.addSpaceship(spaceship);
		return Response
				.created(
						UriBuilder.fromUri(uriInfo.getBaseUri()).path("/v4/space-fleet/" + spaceship.getName()).build())
				.build();
	}

	@ApiOperation(value = "Retrive information about specific ship", notes = "Returns Json format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrived information about specific spaceship", response = Spaceship.class),
			@ApiResponse(code = 404, message = "Spaceship not found", response = ResourceNotFound.class) })
	@Path("/{spaceshipName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSpaceshipDetails(@ApiParam(value = "spaceship name") @PathParam("spaceshipName") String name)
	{
		Spaceship ship = fleetHandler.getSpaceship(name);
		if (ship == null)
			throw new ResourceNotFound(name);
		else
		{
			CacheControl cc = new CacheControl();
			cc.setMaxAge(60); // 1 minute
			cc.setPrivate(true);
			return Response.ok(ship).cacheControl(cc).build();
		}
	}

	@ApiOperation(value = "Retrive information about ships in sorted manner", notes = "Returns Json format")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrived ship list", response = Spaceship.class) })
	@Path("/ships")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderedSpaceships(@QueryParam("order") @DefaultValue("ASC") String order,
			@QueryParam("by") @DefaultValue("name") String element, @QueryParam("page") @DefaultValue("1") String page,
			@QueryParam("size") @DefaultValue("20") String size, @Context UriInfo uriInfo)
	{
		int pageSize;
		int pageNumber;
		try
		{
			pageSize = Integer.parseInt(size);
			pageNumber = Integer.parseInt(page);
		} catch (NumberFormatException ex)
		{
			pageSize = 20;
			pageNumber = 1;
		}

		spaceshipsPaginated.setupSize(pageSize, order, element);
		spaceshipsPaginated.setIndex(pageNumber - 1);
		Page<Spaceship> shipPage = spaceshipsPaginated.getCurrentPage();

		for (Spaceship ship : shipPage.getObjects())
		{
			UriBuilder uriBuilder = UriBuilder.fromUri(uriInfo.getBaseUri()).path(SpaceshipsV4.class);
			uriBuilder.path(ship.getName());
			Link detailsLink = Link.fromUriBuilder(uriBuilder).rel("self").type("GET").build();
			ship.setSelfLink(detailsLink);
		}
		Link firstPageLink, lastPageLink;
		ResponseBuilder response = Response.ok(shipPage);
		if (pageNumber > 1)
		{
			firstPageLink = Link
					.fromUriBuilder(UriBuilder.fromUri(uriInfo.getBaseUri()).path(SpaceshipsV4.class)
							.path(SpaceshipsV4.class, "getOrderedSpaceships").queryParam("order", order)
							.queryParam("by", element).queryParam("page", 1).queryParam("size", size))
					.rel("first").build();
			response.links(firstPageLink);
		}
		if (spaceshipsPaginated.getPageCount() > pageNumber)
		{
			lastPageLink = Link.fromUriBuilder(UriBuilder.fromUri(uriInfo.getBaseUri()).path(SpaceshipsV4.class)
					.path(SpaceshipsV4.class, "getOrderedSpaceships").queryParam("order", order)
					.queryParam("by", element).queryParam("page", spaceshipsPaginated.getPageCount())
					.queryParam("size", size)).rel("last").build();
			response.links(lastPageLink);
		}
		return response.build();
	}
}

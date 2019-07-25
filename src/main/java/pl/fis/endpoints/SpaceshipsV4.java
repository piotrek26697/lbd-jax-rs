package pl.fis.endpoints;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.CacheControl;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import pl.fis.data.ResourceNotFound;
import pl.fis.data.Spaceship;
import pl.fis.logic.SpaceFleetHandler;

@Api(value = "Space-fleet endpoint. Provides funcionality to operate space fleet")
@Path("/v4/space-fleet")
public class SpaceshipsV4
{
	@Inject
	private SpaceFleetHandler fleetHandler;

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
	@ApiResponses(value = { @ApiResponse(code = 204, message = "Successfully added ship"),
			@ApiResponse(code = 400, message = "Spaceship invalid", response = ConstraintViolationException.class) })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSpaceship(@Valid Spaceship spaceship)
	{
		fleetHandler.addSpaceship(spaceship);
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

//	@Path("/ships")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public List<Spaceship> getOrderedSpaceships(@QueryParam("order") String order, @QueryParam("by") String element)
//	{
//		List<Spaceship> shipList = fleetHandler.getSpaceFleet().getSpaceFleetShips();
///////////////////////////////////////
//		switch (order.toUpperCase())
//		{
//		case "DESC":
//			if ("speed".equalsIgnoreCase(element))
//			{
//				Collections.sort(shipList, (s1, s2) -> {
//					return Double.compare(s1.getSpeed(), s2.getSpeed());
//				});
//				Collections.reverse(shipList);
//			} else
//			{
//				Collections.sort(shipList, (s1, s2) -> {
//					return s1.getName().compareTo(s2.getName());
//				});
//				Collections.reverse(shipList);
//			}
//		default:
//			if ("speed".equalsIgnoreCase(element))
//			{
//				Collections.sort(shipList, (s1, s2) -> {
//					return Double.compare(s1.getSpeed(), s2.getSpeed());
//				});
//			} else
//			{
//				Collections.sort(shipList, (s1, s2) -> {
//					return s1.getName().compareTo(s2.getName());
//				});
//			}
//		}
//////////////////////////////////
//		return null;
//	}
}

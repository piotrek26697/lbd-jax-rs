package pl.fis.endpoints;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.fis.data.SpaceFleet;
import pl.fis.data.Spaceship;
import pl.fis.logic.SpaceFleetHandler;

@Path("/v3/space-fleet")
public class SpaceshipsV3
{
	@Inject
	private SpaceFleetHandler fleetHandler;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SpaceFleet getSpaceFleet()
	{
		return fleetHandler.getSpaceFleet();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void addSpaceship(Spaceship spaceship)
	{
		fleetHandler.addSpaceship(spaceship);
	}

	@Path("/{spaceshipName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Spaceship getSpaceshipDetails(@PathParam("spaceshipName") String name)
	{
		return fleetHandler.getSpaceship(name);
	}
}

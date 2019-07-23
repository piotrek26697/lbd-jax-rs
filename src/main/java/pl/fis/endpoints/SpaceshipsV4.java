package pl.fis.endpoints;

import java.time.LocalDate;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.fis.data.ResourceNotFound;
import pl.fis.data.SpaceFleet;
import pl.fis.data.Spaceship;
import pl.fis.logic.SpaceFleetHandler;

@Path("/v4/space-fleet")
public class SpaceshipsV4
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
	public void addSpaceship(@Valid Spaceship spaceship)
	{
		fleetHandler.addSpaceship(spaceship);
	}

	@Path("/{spaceshipName}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Spaceship getSpaceshipDetails(@PathParam("spaceshipName") String name)
	{
		Spaceship ship = fleetHandler.getSpaceship(name);
		if (ship == null)
			throw new ResourceNotFound(name);
		else
			return ship;
	}
}

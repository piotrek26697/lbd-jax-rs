package pl.fis.endpoints;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.fis.data.DataBase;
import pl.fis.data.Spaceship;

@Path("/v1/spaceships")
public class SpaceshipsV1
{
	@Inject
	private DataBase db;
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Spaceship> getSpaceships()
	{
		return db.getShipList();
	}
	
	@Consumes(MediaType.TEXT_PLAIN)
	@POST
	public void addSpaceship(String spaceship)
	{
		db.getShipList().add(new Spaceship(spaceship));
	}
}

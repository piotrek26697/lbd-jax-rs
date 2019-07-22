package pl.fis.endpoints;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.fis.data.DataBase;
import pl.fis.data.Spaceship;

@Path("/v2/spaceships")
public class SpaceshipsV2
{
	@Inject
	private DataBase db;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Spaceship> getSpaceshipsJSON()
	{	
		return db.getShipList();
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public void addSpaceShip(Spaceship spaceship)
	{
		db.getShipList().add(spaceship);
	}
	
	@DELETE
	@Path("/{spaceshipName}")
	public void deleteSpaceship(@PathParam("spaceshipName") String spaceshipName)
	{
		db.deleteShip(spaceshipName);
	}
}

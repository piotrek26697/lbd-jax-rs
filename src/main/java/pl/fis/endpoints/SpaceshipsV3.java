package pl.fis.endpoints;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import pl.fis.data.DataBase;
import pl.fis.data.SpaceFleet;

@Path("/v3/space-fleet")
public class SpaceshipsV3
{
	@Inject
	private DataBase db;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public SpaceFleet getSpaceFleet()
	{
		SpaceFleet fleet = new SpaceFleet();
		fleet.setName("FIS fleet");
		fleet.setSpacefleet(db.getShipList());
		return fleet;
	}
}

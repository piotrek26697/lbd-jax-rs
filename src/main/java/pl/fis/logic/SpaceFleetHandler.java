package pl.fis.logic;

import java.util.Iterator;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.fis.data.DataBase;
import pl.fis.data.SpaceFleet;
import pl.fis.data.Spaceship;

@Stateless
public class SpaceFleetHandler
{
	@Inject
	private DataBase db;

	public SpaceFleetHandler()
	{
	}

	public SpaceFleet getSpaceFleet()
	{
		return db.getFleet();
	}

	public void addSpaceship(Spaceship spaceship)
	{
		db.getFleet().getSpaceFleetShips().add(spaceship);
	}

	public Spaceship getSpaceship(String name)
	{
		for (Iterator<Spaceship> iter = db.getFleet().getSpaceFleetShips().iterator(); iter.hasNext();)
		{
			Spaceship ship = iter.next();
			if (ship.getName().equalsIgnoreCase(name))
				return ship;
		}
		return null;
	}
}

package pl.fis.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DataBase
{
	private List<Spaceship> shipList;
	
	private SpaceFleet fleet;

	public DataBase()
	{
		shipList.add(new Spaceship("Elysium", 5, "1999-01-05"));
		shipList.add(new Spaceship("Battlestar Galactica", 10, "2008-06-24"));
		shipList.add(new Spaceship("Reapers", 2, "1993-11-03"));
		shipList.add(new Spaceship("USS Enterprise", 1, "1993-11-07"));
		shipList.add(new Spaceship("Spaceball One", 2, "1994-09-03"));
		shipList.add(new Spaceship("Millenium Falcon", 15, "2019-05-04"));
		shipList.add(new Spaceship("Death Star", 1, "1954-09-19"));
		shipList.add(new Spaceship("Starkiller Base", 1, "1984-06-03"));
		shipList.add(new Spaceship("Tie Fighter", 6, "2001-08-29"));
		shipList.add(new Spaceship("USCSS Prometheus", 9, "2014-12-20"));
		
		fleet = new SpaceFleet();
		fleet.setName("FIS fleet");
		fleet.setSpaceFleetShips(shipList);
	}

	public SpaceFleet getFleet()
	{
		return fleet;
	}

	public void setFleet(SpaceFleet fleet)
	{
		this.fleet = fleet;
	}

	public List<Spaceship> getShipList()
	{
		return shipList;
	}

	public void setShipList(List<Spaceship> shipList)
	{
		this.shipList = shipList;
	}

	public void deleteShip(String name)
	{
		for (Iterator<Spaceship> iterator = shipList.iterator(); iterator.hasNext();)
		{
			Spaceship ship = iterator.next();
			if (ship.getName().equalsIgnoreCase(name))
				iterator.remove();
		}
	}

}

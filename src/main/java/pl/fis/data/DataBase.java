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
		shipList = new ArrayList<>();
		shipList.add(new Spaceship("Elysium"));
		shipList.add(new Spaceship("Battlestar Galactica"));
		shipList.add(new Spaceship("Reapers"));
		
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

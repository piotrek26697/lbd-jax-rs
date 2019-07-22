package pl.fis.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataBase
{
	private List<Spaceship> shipList;

	public DataBase()
	{
		shipList = new ArrayList<>();
		shipList.add(new Spaceship("Elysium"));
		shipList.add(new Spaceship("Battlestar Galactica"));
		shipList.add(new Spaceship("Reapers"));
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

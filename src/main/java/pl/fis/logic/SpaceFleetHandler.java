package pl.fis.logic;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import pl.fis.data.DataBase;
import pl.fis.data.SpaceFleet;
import pl.fis.data.Spaceship;
import pl.fis.logic.pagination.Page;

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

	public List<Page<Spaceship>> getSpaceshipPages(int size, String sortOrder, String sortBy)
	{
		List<Spaceship> shipList = db.getFleet().getSpaceFleetShips();
		ListSorter.sort(sortOrder, sortBy, shipList);
		List<Page<Spaceship>> resultList = new ArrayList<>();
		int i = 0;
		while (true)
		{
			Page<Spaceship> page = new Page<>();
			if (i * size >= shipList.size())
				return resultList;
			else if (i * size + size > shipList.size())
			{
				page.setObjects(shipList.subList(i * size, shipList.size()));
				resultList.add(page);
			} else
			{
				page.setObjects(shipList.subList(i * size, i * size + size));
				resultList.add(page);
			}
			i++;
		}
	}
}

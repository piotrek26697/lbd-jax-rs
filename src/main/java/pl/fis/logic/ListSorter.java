package pl.fis.logic;

import java.util.Collections;
import java.util.List;

import pl.fis.data.Spaceship;

public class ListSorter
{
	static public List<Spaceship> sort(String order, String element, List<Spaceship> shipList)
	{

		if ("speed".equalsIgnoreCase(element))
		{
			Collections.sort(shipList, (s1, s2) -> {
				return Double.compare(s1.getSpeed(), s2.getSpeed());
			});
		} else
		{
			Collections.sort(shipList, (s1, s2) -> {
				return s1.getName().compareTo(s2.getName());
			});
		}
		if("DESC".equalsIgnoreCase(order))
			Collections.reverse(shipList);
		
		return shipList;
	}
}
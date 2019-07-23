package pl.fis.data;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;

public class SpaceFleet
{
	private String name;

	@JsonbProperty("ships")
	private List<Spaceship> spaceFleetShips;

	public SpaceFleet()
	{
	};

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Spaceship> getSpaceFleetShips()
	{
		return spaceFleetShips;
	}

	public void setSpaceFleetShips(List<Spaceship> spacefleet)
	{
		this.spaceFleetShips = spacefleet;
	}
}

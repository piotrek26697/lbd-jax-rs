package pl.fis.data;

import java.util.List;

import javax.json.bind.annotation.JsonbProperty;


public class SpaceFleet
{
	private String name;
	
	@JsonbProperty("ships")
	private List<Spaceship> spacefleet;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Spaceship> getSpacefleet()
	{
		return spacefleet;
	}

	public void setSpacefleet(List<Spaceship> spacefleet)
	{
		this.spacefleet = spacefleet;
	}
}

package pl.fis.logic.pagination;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Link;

public class Page<T>
{
	private List<T> objects;

	private List<Link> links;

	public Page()
	{
		links = new ArrayList<>();
	}

	public List<Link> getLinks()
	{
		return links;
	}

	public void setLinks(List<Link> links)
	{
		this.links = links;
	}

	public List<T> getObjects()
	{
		return objects;
	}

	public void setObjects(List<T> objects)
	{
		this.objects = objects;
	}

}

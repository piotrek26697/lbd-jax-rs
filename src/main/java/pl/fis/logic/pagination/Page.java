package pl.fis.logic.pagination;

import java.util.List;

public class Page<T>
{
	private List<T> objects;

	public List<T> getObjects()
	{
		return objects;
	}

	public void setObjects(List<T> objects)
	{
		this.objects = objects;
	}
	
}

package pl.fis.logic.pagination;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import pl.fis.data.Spaceship;
import pl.fis.logic.SpaceFleetHandler;

@RequestScoped
public class SpaceshipsPaginated implements Paginated<Page<Spaceship>>
{
	@Inject
	private SpaceFleetHandler spaceFleetHandler;
	private List<Page<Spaceship>> spaceshipPages;
	private int pageIndex = 0;

	@Override
	public Page<Spaceship> getCurrentPage()
	{
		if (pageIndex < spaceshipPages.size() && pageIndex >= 0)
			return spaceshipPages.get(pageIndex);
		else
			return null;
	}

	@Override
	public int getCurrentPageIndex()
	{
		return pageIndex;
	}

	@Override
	public int getPageCount()
	{
		return spaceshipPages.size();
	}

	public void setIndex(int index)
	{
		this.pageIndex = index;
	}

	public void setupSize(int size, String sortOrder, String sortBy)
	{
		this.spaceshipPages = spaceFleetHandler.getSpaceshipPages(size, sortOrder, sortBy);
	}
}

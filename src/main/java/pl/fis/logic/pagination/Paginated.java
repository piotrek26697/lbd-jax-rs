package pl.fis.logic.pagination;

public interface Paginated<T>
{
	T getCurrentPage();
	int getCurrentPageIndex();
	int getPageCount();
}

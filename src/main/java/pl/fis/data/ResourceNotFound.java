package pl.fis.data;

public class ResourceNotFound extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFound()
	{
		super("Resource not found");
	}
	public ResourceNotFound(String message)
	{
		super(message);
	}
}

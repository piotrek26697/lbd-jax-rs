package pl.fis.logic.errors.handlers;

import javax.json.Json;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import pl.fis.logic.errors.ResourceNotFound;

@Provider
public class EntityNotFoundMapper implements ExceptionMapper<ResourceNotFound>
{

	@Override
	public Response toResponse(ResourceNotFound exception)
	{
		String message = Json.createObjectBuilder()
				.add("status", Response.Status.NOT_FOUND.toString())
				.add("message", "resource '" + exception.getMessage()+"' not found")
				.build()
				.toString();
		
		
		return Response.status(Response.Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(message)
				.build();
	}

}

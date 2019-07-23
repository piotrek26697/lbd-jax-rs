package pl.fis.logic;

import java.util.Set;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException>
{
	@Override
	public Response toResponse(ConstraintViolationException exception)
	{
		JsonArrayBuilder errorsList = Json.createArrayBuilder();

		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations)
		{
			String fieldName = violation.getPropertyPath().toString()
					.substring(violation.getPropertyPath().toString().lastIndexOf('.') + 1);
			
			JsonObject error = Json.createObjectBuilder()
					.add(fieldName, violation.getMessage())
					.build();
			
			errorsList.add(error);
		}
		String message = Json.createObjectBuilder()
				.add("status", Response.Status.BAD_REQUEST.toString())
				.add("field-errors", errorsList.build())
				.build()
				.toString();

		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
}

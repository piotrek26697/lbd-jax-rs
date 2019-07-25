package pl.fis.logic;

import java.util.ResourceBundle;
import java.util.Set;

import javax.inject.Inject;
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
	@Inject
	private UserLanguage userLanguage;
	
	@Override
	public Response toResponse(ConstraintViolationException exception)
	{		
		ResourceBundle resourceBundle = ResourceBundle.getBundle("LanguageBundle", userLanguage.getLanguage());
		
		JsonArrayBuilder errorsList = Json.createArrayBuilder();

		Set<ConstraintViolation<?>> violations = exception.getConstraintViolations();
		for (ConstraintViolation<?> violation : violations)
		{
			String fieldName = violation.getPropertyPath().toString()
					.substring(violation.getPropertyPath().toString().lastIndexOf('.') + 1);
			
			JsonObject error = Json.createObjectBuilder()
					.add(resourceBundle.getString(fieldName), resourceBundle.getString(fieldName+"Message"))
					.build();
			
			errorsList.add(error);
		}
		String message = Json.createObjectBuilder()
				.add(resourceBundle.getString("status"), Response.Status.BAD_REQUEST.toString())
				.add(resourceBundle.getString("errors"), errorsList.build())
				.build()
				.toString();

		return Response.status(Response.Status.BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
}

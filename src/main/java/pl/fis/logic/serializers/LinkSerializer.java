package pl.fis.logic.serializers;

import java.io.IOException;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LinkSerializer extends JsonSerializer<Link>
{

	@Override
	public void serialize(Link value, JsonGenerator gen, SerializerProvider serializers) throws IOException
	{
		gen.writeString(value.toString());
	}

}

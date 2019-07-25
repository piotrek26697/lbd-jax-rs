package pl.fis.filters;

import java.io.IOException;
import java.util.Locale;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import pl.fis.logic.UserLanguage;

@Provider
public class LanguageFilter implements ContainerRequestFilter
{
	@Inject
	private UserLanguage userLanguage;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException
	{
		if (requestContext.getAcceptableLanguages() != null && requestContext.getAcceptableLanguages().size() > 0)
			userLanguage.setLanguage(requestContext.getAcceptableLanguages().get(0));
		else
			userLanguage.setLanguage(new Locale("en"));
	}

}

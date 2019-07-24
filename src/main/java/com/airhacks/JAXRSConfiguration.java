package com.airhacks;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures a JAX-RS endpoint. Delete this class, if you are not exposing
 * JAX-RS resources in your application.
 *
 * @author airhacks.com
 */
@ApplicationPath("/api")
public class JAXRSConfiguration extends Application
{
	@Override
	public Set<Class<?>> getClasses()
	{
		Set<Class<?>> classes = new HashSet<>();
		classes.add(pl.fis.endpoints.SpaceshipsV4.class);
		classes.add(pl.fis.logic.ConstraintViolationMapper.class);
		classes.add(pl.fis.logic.SpaceFleetHandler.class);
		classes.add(pl.fis.logic.EntityNotFoundMapper.class);
		classes.add(pl.fis.serializers.LocalDateSerializer.class);
		classes.add(pl.fis.serializers.LocalDateDeserializer.class);
		classes.add(pl.fis.data.DataBase.class);
		classes.add(pl.fis.data.ResourceNotFound.class);
		classes.add(pl.fis.data.SpaceFleet.class);
		classes.add(pl.fis.data.Spaceship.class);
		classes.add(com.github.phillipkruger.apiee.ApieeService.class);
		return classes;
	}
}

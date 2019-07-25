package pl.fis.logic;

import java.util.Locale;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class UserLanguage
{
	private Locale language;

	public Locale getLanguage()
	{
		return language;
	}

	public void setLanguage(Locale language)
	{
		this.language = language;
	}

}

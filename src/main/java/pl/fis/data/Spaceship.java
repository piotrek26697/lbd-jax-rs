package pl.fis.data;

import java.time.LocalDate;

import javax.json.bind.annotation.JsonbProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import io.swagger.annotations.ApiModel;
import pl.fis.logic.serializers.LinkSerializer;
import pl.fis.logic.serializers.LocalDateDeserializer;
import pl.fis.logic.serializers.LocalDateSerializer;

@ApiModel
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Spaceship
{
	@XmlElement
	@NotBlank
	private String name;

	@XmlElement
	@Positive
	private double speed;

	@XmlElement(name = "year-of-manufacturing")
	//@XmlJavaTypeAdapter(LocalDateAdapter.class)
	@JsonbProperty("year-of-manufacturing")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@PastOrPresent
	private LocalDate dayOfManufacture;
	
	@JsonSerialize(using=LinkSerializer.class)
	private Link selfLink;

	public Spaceship()
	{
	};

	public Link getselfLink()
	{
		return selfLink;
	}

	public void setSelfLink(Link detailsLink)
	{
		this.selfLink = detailsLink;
	}

	public Spaceship(String name)
	{
		this.name = name;
	}

	public Spaceship(String name, double speed, String date)
	{
		this.name = name;
		this.speed = speed;
		this.dayOfManufacture = LocalDate.parse(date);
	}

	public LocalDate getDayOfManufacture()
	{
		return dayOfManufacture;
	}

	public void setDayOfManufacture(LocalDate dayOfManufacture)
	{
		this.dayOfManufacture = dayOfManufacture;
	}

	public double getSpeed()
	{
		return speed;
	}

	public void setSpeed(double speed)
	{
		this.speed = speed;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}

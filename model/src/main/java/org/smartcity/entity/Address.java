package org.smartcity.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.Serializable;

/**
 * todo javadoc
 */
@Embeddable
public class Address
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( Address.class );

	public static final String STATE_COLUMN_NAME           = "STATE";
	public static final String CITY_COLUMN_NAME            = "CITY";
	public static final String STREET_COLUMN_NAME          = "STREET";
	public static final String BUILDING_NUMBER_COLUMN_NAME = "BUILDING_NUMBER";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String STATE_FILED            = "state";
	public static final String CITY_FIELD             = "city";
	public static final String STREET_FIELD           = "street";
	public static final String BUILDING_NUMBER_FIELD  = "buildingNumber";
	public static final String TELEPHONE_NUMBER_FIELD = "telephoneNumber";

	private static final short MIN_BUILDING_NUMBER = 1;
	private static final short MAX_BUILDING_NUMBER = 999;

	@Column(
			name = Address.STATE_COLUMN_NAME,
			nullable = false
	)
	private String          state;
	@Column(
			name = Address.CITY_COLUMN_NAME,
			nullable = false
	)
	private String          city;
	@Column(
			name = Address.STREET_COLUMN_NAME,
			nullable = false
	)
	private String          street;
	@Column(
			name = Address.BUILDING_NUMBER_COLUMN_NAME,
			nullable = false
	)
	private Short           buildingNumber;
	@Embedded
	private TelephoneNumber telephoneNumber;

	public Address() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public Address(
			String state,
			String city,
			String street,
			Short buildingNumber,
			TelephoneNumber telephoneNumber ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setTelephoneNumber( telephoneNumber )
				.setState( state )
				.setCity( city )
				.setStreet( street )
				.setBuildingNumber( buildingNumber );
	}

	public String getState() {
		return state;
	}

	public Address setState( String state ) {
		LOG.debug( "State is " + state );
		this.state = state;
		return this;
	}

	public String getCity() {
		return city;
	}

	public Address setCity( String city ) {
		LOG.debug( "City is " + city );
		this.city = city;
		return this;
	}

	public String getStreet() {
		return street;
	}

	public Address setStreet( String street ) {
		LOG.debug( "Street is " + street );
		this.street = street;
		return this;
	}

	public Short getBuildingNumber() {
		return buildingNumber;
	}

	public Address setBuildingNumber( Short buildingNumber ) {
		LOG.debug( "Building number is " + buildingNumber );
		if( buildingNumber < MIN_BUILDING_NUMBER || buildingNumber > MAX_BUILDING_NUMBER ) {
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append(
					"Building number is not in allow bound. Building number should be between " )
					.append( MIN_BUILDING_NUMBER )
					.append( " and " )
					.append( MAX_BUILDING_NUMBER )
					.append( ". But acctual result is " )
					.append( buildingNumber )
					.append( '.' );
			String message = messageBuilder.toString();
			LOG.debug( message );
			throw new IllegalArgumentException( message );
		}
		this.buildingNumber = buildingNumber;
		return this;
	}

	public TelephoneNumber getTelephoneNumber() {
		return telephoneNumber;
	}

	public Address setTelephoneNumber( TelephoneNumber telephoneNumber ) {
		LOG.debug( "Telephone number is " + telephoneNumber );
		this.telephoneNumber = telephoneNumber;
		return this;
	}

	@Override
	public int hashCode() {
		return getState().hashCode()
			   + getCity().hashCode()
			   + getStreet().hashCode()
			   + getBuildingNumber()
			   + getTelephoneNumber().hashCode();
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			Address address = (Address) object;
			return address.getState().equals( getState() )
				   && address.getCity().equals( getCity() )
				   && address.getStreet().equals( getStreet() )
				   && address.getBuildingNumber().equals( getBuildingNumber() )
				   && address.getTelephoneNumber().equals( getTelephoneNumber() );
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Address with State = " )
				.append( getState() )
				.append( " City = " )
				.append( getCity() )
				.append( " Street = " )
				.append( getStreet() )
				.append( " Building Number = " )
				.append( getBuildingNumber() );
		return sb.toString();
	}

}

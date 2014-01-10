package org.smartcity.entity.jpa;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Address;

@Embeddable
public class AddressEmbedded
		implements Address<AddressEmbedded, TelephoneNumberEmbedded> {

	private static final Log LOG = LogFactory.getLog( AddressEmbedded.class );

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
	private String                  state;
	@Column(
			name = Address.CITY_COLUMN_NAME,
			nullable = false
	)
	private String                  city;
	@Column(
			name = Address.STREET_COLUMN_NAME,
			nullable = false
	)
	private String                  street;
	@Column(
			name = Address.BUILDING_NUMBER_COLUMN_NAME,
			nullable = false
	)
	private short                   buildingNumber;
	@Embedded
	private TelephoneNumberEmbedded telephoneNumber;

	public AddressEmbedded() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public AddressEmbedded(
			String state,
			String city,
			String street,
			short buildingNumber,
			TelephoneNumberEmbedded telephoneNumber ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setTelephoneNumber( telephoneNumber )
				.setState( state )
				.setCity( city )
				.setStreet( street )
				.setBuildingNumber( buildingNumber );
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public AddressEmbedded setState( String state ) {
		LOG.debug( "State is " + state );
		this.state = state;
		return this;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public AddressEmbedded setCity( String city ) {
		LOG.debug( "City is " + city );
		this.city = city;
		return this;
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public AddressEmbedded setStreet( String street ) {
		LOG.debug( "Street is " + street );
		this.street = street;
		return this;
	}

	@Override
	public short getBuildingNumber() {
		return buildingNumber;
	}

	@Override
	public AddressEmbedded setBuildingNumber( short buildingNumber ) {
		LOG.debug( "Building number is " + buildingNumber );
		if ( buildingNumber < MIN_BUILDING_NUMBER
				|| buildingNumber > MAX_BUILDING_NUMBER ) {
			StringBuilder messageBuilder = new StringBuilder();
			messageBuilder.append( "Building number is not in allow bound. Building number should be between " )
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

	@Override
	public TelephoneNumberEmbedded getTelephoneNumber() {
		return telephoneNumber;
	}

	@Override
	public AddressEmbedded setTelephoneNumber( TelephoneNumberEmbedded telephoneNumber ) {
		LOG.debug( "Telephone number is " + telephoneNumber );
		this.telephoneNumber = telephoneNumber;
		return this;
	}

	@Override
	public int hashCode() {
		return getState().hashCode() + getCity().hashCode() + getStreet().hashCode() + getBuildingNumber() +
			   getTelephoneNumber().hashCode();
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
				&& object.getClass().equals( getClass() ) ) {
			AddressEmbedded address = (AddressEmbedded)object;
			return address.getState().equals( getState() )
					&& address.getCity().equals( getCity() )
					&& address.getStreet().equals( getStreet() )
					&& address.getBuildingNumber() == getBuildingNumber()
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

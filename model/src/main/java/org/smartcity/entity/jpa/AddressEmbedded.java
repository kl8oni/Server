package org.smartcity.entity.jpa;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Address;

@Embeddable
public class AddressEmbedded
		implements Address<AddressEmbedded, TelephoneNumberEmbedded> {

	private static final Log LOG = LogFactory.getLog( AddressEmbedded.class );

	private static final short MIN_BUILDING_NUMBER = 1;
	private static final short MAX_BUILDING_NUMBER = 999;

	private String                  state;
	private String                  city;
	private String                  street;
	private short                   buildingNumber;
	@Embedded
	private TelephoneNumberEmbedded telephoneNumber;

	public AddressEmbedded(
			String state, String city, String street, short buildingNumber,
			TelephoneNumberEmbedded telephoneNumber ) {
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
		this.state = state;
		return this;
	}

	@Override
	public String getCity() {
		return city;
	}

	@Override
	public AddressEmbedded setCity( String city ) {
		this.city = city;
		return this;
	}

	@Override
	public String getStreet() {
		return street;
	}

	@Override
	public AddressEmbedded setStreet( String street ) {
		this.street = street;
		return this;
	}

	@Override
	public short getBuildingNumber() {
		return buildingNumber;
	}

	@Override
	public AddressEmbedded setBuildingNumber( short buildingNumber ) {
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
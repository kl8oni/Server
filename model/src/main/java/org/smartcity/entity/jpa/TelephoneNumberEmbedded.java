package org.smartcity.entity.jpa;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import java.util.Formatter;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.TelephoneNumber;

@Embeddable
public class TelephoneNumberEmbedded
		implements TelephoneNumber<TelephoneNumberEmbedded> {

	private static final Log           LOG             = LogFactory.getLog( TelephoneNumberEmbedded.class );
	private static final Formatter     FORMATTER       = new Formatter();
	private static final StringBuilder MESSAGE_BUILDER = new StringBuilder();

	/*
	 * Constant for fields embeddable class
	 */
	private static final String TELEPHONE_NUMBER_VALUE = "telephoneNumberValue";

	private static final String DEFAULT_TELEPHONE_NUMBER_PATTERN = "(%d3)-%d3-%d7";
	private static final short  MIN_STATE_CODE                   = 1;
	private static final short  MAX_STATE_CODE                   = 999;
	private static final short  MIN_CITY_CODE                    = 1;
	private static final short  MAX_CITY_CODE                    = 999;
	private static final int    MIN_NUMBER                       = 1;
	private static final int    MAX_NUMBER                       = 9999999;

	@Transient
	private short         stateCode;
	@Transient
	private short         cityCode;
	@Transient
	private int           number;
	@Transient
	private String        pattern;
	@Transient
	private boolean       regenerateTelephoneNumber;
	private String        telephoneNumberValue;

	public TelephoneNumberEmbedded() {
	}

	public TelephoneNumberEmbedded(
			short stateCode,
			short cityCode,
			int number,
			String pattern ) {
		this.regenerateTelephoneNumber = false;
		setTelephonePattern( pattern )
				.setStateCode( stateCode )
				.setCityCode( cityCode )
				.setNumber( number );
	}

	private void generateTelephoneNumberValue() {
		FORMATTER.format( Locale.getDefault(), pattern, stateCode, cityCode, number );
		this.telephoneNumberValue = FORMATTER.out().toString();
	}

	@Override
	public TelephoneNumberEmbedded setTelephoneNumberValue( String telephoneNumberValue ) {
		//TODO parse new telephone number with pattern
		this.telephoneNumberValue = telephoneNumberValue;
		return this;
	}

	@Override
	public String getTelephoneNumberValue() {
		return telephoneNumberValue;
	}

	@Override
	public TelephoneNumberEmbedded setStateCode( short stateCode ) {
		clearMessageBuilder();
		if ( stateCode < MIN_STATE_CODE
				|| stateCode > MAX_STATE_CODE ) {
			MESSAGE_BUILDER.append( "State code is not in allowable bound. State code should be between " )
					.append( MIN_STATE_CODE )
					.append( " and " )
					.append( MAX_STATE_CODE )
					.append( ". But actual result is " )
					.append( stateCode )
					.append( '.' );
			String message = MESSAGE_BUILDER.toString();
			LOG.debug( message );
			throw new IllegalArgumentException( message );
		}
		this.stateCode = stateCode;
		if ( regenerateTelephoneNumber ) {
			generateTelephoneNumberValue();
		}
		return this;
	}

	@Override
	public TelephoneNumberEmbedded setCityCode( short cityCode ) {
		clearMessageBuilder();
		if ( cityCode < MIN_CITY_CODE
				|| cityCode > MAX_CITY_CODE ) {
			MESSAGE_BUILDER.append( "City code is not in allowable bound. State code should be between " )
					.append( MIN_CITY_CODE )
					.append( " and " )
					.append( MAX_CITY_CODE )
					.append( ". But actual result is " )
					.append( cityCode )
					.append( '.' );
			String message = MESSAGE_BUILDER.toString();
			LOG.debug( message );
			throw new IllegalArgumentException( message );
		}
		if ( regenerateTelephoneNumber ) {
			generateTelephoneNumberValue();
		}
		this.cityCode = cityCode;
		return this;
	}

	@Override
	public TelephoneNumberEmbedded setNumber( int number ) {
		clearMessageBuilder();
		if ( number < MIN_NUMBER
			 || number > MAX_NUMBER ) {
			MESSAGE_BUILDER.append( "Number is not in allowable bound. State code should be between " )
					.append( MIN_NUMBER )
					.append( " and " )
					.append( MAX_NUMBER )
					.append( ". But actual result is " )
					.append( number )
					.append( '.' );
			String message = MESSAGE_BUILDER.toString();
			LOG.debug( message );
			throw new IllegalArgumentException( message );
		}
		if ( regenerateTelephoneNumber ) {
			generateTelephoneNumberValue();
		}
		this.number = number;
		return this;
	}

	@Override
	public TelephoneNumberEmbedded setTelephonePattern( String pattern ) {
		regenerateTelephoneNumber = true;
		if ( pattern == null ) {
			LOG.debug( "Set telephone pattern to default" );
			this.pattern = DEFAULT_TELEPHONE_NUMBER_PATTERN;
		}
		else {
			this.pattern = pattern;
		}
		if( regenerateTelephoneNumber ) {
			generateTelephoneNumberValue();
		}
		return this;
	}

	@Override
	public String getTelephonePattern() {
		return pattern;
	}

	private void clearMessageBuilder() {
		MESSAGE_BUILDER.delete( 0, MESSAGE_BUILDER.length() );
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
				&& object.getClass().equals( getClass() ) ) {
			TelephoneNumberEmbedded telephoneNumber = (TelephoneNumberEmbedded)object;
			return telephoneNumber.getTelephoneNumberValue().equals( getTelephoneNumberValue() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getTelephoneNumberValue().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Telephone Number = " )
				.append( getTelephoneNumberValue() )
				.append( " ]" );
		return sb.toString();
	}

}

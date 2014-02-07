package org.smartcity.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

import java.io.Serializable;
import java.util.Formatter;
import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 */
@Embeddable
public class TelephoneNumber
		implements Serializable {

	private static final Log           LOG             = LogFactory.getLog( TelephoneNumber.class );
	private static final Formatter     FORMATTER       = new Formatter();
	private static final StringBuilder MESSAGE_BUILDER = new StringBuilder();

	public static final String TELEPHONE_NUMBER_COLUMN_NAME = "TELEPHONE_NUMBER";

	/*
	 * Constant for fields embeddable class
	 */
	private static final String TELEPHONE_NUMBER_VALUE_FIELD = "telephoneNumberValue";

	private static final String DEFAULT_TELEPHONE_NUMBER_PATTERN = "(%03d)-%03d-%07d";
	private static final short  MIN_STATE_CODE                   = 1;
	private static final short  MAX_STATE_CODE                   = 999;
	private static final short  MIN_CITY_CODE                    = 1;
	private static final short  MAX_CITY_CODE                    = 999;
	private static final int    MIN_NUMBER                       = 1;
	private static final int    MAX_NUMBER                       = 9999999;

	@Transient
	private Short   stateCode;
	@Transient
	private Short   cityCode;
	@Transient
	private Integer number;
	@Transient
	private String  pattern;
	@Transient
	private Boolean regenerateTelephoneNumber;
	@Column(
			name = TelephoneNumber.TELEPHONE_NUMBER_COLUMN_NAME,
			nullable = false
	)
	private String  telephoneNumberValue;

	public TelephoneNumber() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public TelephoneNumber(
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setTelephonePattern( pattern )
				.setStateCode( stateCode )
				.setCityCode( cityCode )
				.setNumber( number );
	}

	private void generateTelephoneNumberValue() {
		if( regenerateTelephoneNumber ) {
			StringBuilder sb = (StringBuilder) FORMATTER.out();
			sb.delete( 0, sb.length() );
			FORMATTER.format( Locale.getDefault(), pattern, stateCode, cityCode, number );
			this.telephoneNumberValue = FORMATTER.out().toString();
		}
	}

	public TelephoneNumber setTelephoneNumberValue( String telephoneNumberValue ) {
		//TODO parse new telephone number with pattern
		this.telephoneNumberValue = telephoneNumberValue;
		return this;
	}

	public String getTelephoneNumberValue() {
		generateTelephoneNumberValue();
		return telephoneNumberValue;
	}

	public TelephoneNumber setStateCode( Short stateCode ) {
		LOG.debug( "State code is " + stateCode );
		if( stateCode < MIN_STATE_CODE
			|| stateCode > MAX_STATE_CODE ) {
			clearMessageBuilder();
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
		return this;
	}

	public TelephoneNumber setCityCode( Short cityCode ) {
		LOG.debug( "City code is " + cityCode );
		if( cityCode < MIN_CITY_CODE
			|| cityCode > MAX_CITY_CODE ) {
			clearMessageBuilder();
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
		this.cityCode = cityCode;
		return this;
	}

	public TelephoneNumber setNumber( Integer number ) {
		LOG.debug( "Number is " + number );
		if( number < MIN_NUMBER
			|| number > MAX_NUMBER ) {
			clearMessageBuilder();
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
		this.number = number;
		return this;
	}

	public TelephoneNumber setTelephonePattern( String pattern ) {
		LOG.debug( "Telephone pattern is " + pattern );
		regenerateTelephoneNumber = true;
		if( pattern == null ) {
			LOG.debug( "Set telephone pattern to default" );
			this.pattern = DEFAULT_TELEPHONE_NUMBER_PATTERN;
		}
		else {
			this.pattern = pattern;
		}
		generateTelephoneNumberValue();
		return this;
	}

	public String getTelephonePattern() {
		return pattern;
	}

	private void clearMessageBuilder() {
		MESSAGE_BUILDER.delete( 0, MESSAGE_BUILDER.length() );
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			TelephoneNumber telephoneNumber = (TelephoneNumber) object;
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

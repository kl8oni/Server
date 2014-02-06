package org.smartcity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(
		name = Email.TABLE_NAME
)
public class Email {

	private static final Log LOG = LogFactory.getLog( Email.class );

	public static final String TABLE_NAME                = "USER_EMAILS";
	public static final String GENERATOR_NAME            = "USER_EMAIL_ID_GENERATOR";
	public static final String SEQUENCE_NAME             = "USER_EMAIL_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME            = "USER_EMAIL_ID";
	public static final String EMAIL_ADDRESS_COLUMN_NAME = "EMAIL_ADDRESS";
	public static final String USER_ID_COLUMN_NAME       = "USER_ID";
	public static final String MAIN_EMAIL_COLUMN         = "MAIN_EMAIL";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD            = "ID";
	public static final String EMAIL_ADDRESS_FIELD = "emailAddress";
	public static final String MAIN_EMAIL_FIELD    = "mainField";
	public static final String OWNER_FIELD         = "owner";

	@Id
	@GeneratedValue(
			generator = Email.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = Email.GENERATOR_NAME,
			sequenceName = Email.SEQUENCE_NAME,
			initialValue = 1
	)
	@Column(
			name = Email.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger ID;
	@Column(
			name = Email.EMAIL_ADDRESS_COLUMN_NAME,
			nullable = false
	)
	private String     emailAddress;
	@Column(
			name = Email.MAIN_EMAIL_COLUMN,
			nullable = false
	)
	private boolean    mainEmail;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Email.USER_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = User.ID_COLUMN_NAME
	)
	private User       owner;

	public Email() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public Email(
			String emailAddress,
			Boolean mainEmail,
			User owner ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setEmailAddress( emailAddress )
				.setMainEmail( mainEmail )
				.setOwner( owner );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public Email setEmailAddress( String emailAddress ) {
		LOG.debug( "Email address is " + emailAddress );
		this.emailAddress = emailAddress;
		return this;
	}

	public User getOwner() {
		return owner;
	}

	public Email setOwner( User owner ) {
		LOG.debug( "Owner is " + owner );
		this.owner = owner;
		return this;
	}

	public Boolean isMainEmail() {
		return mainEmail;
	}

	public Email setMainEmail( boolean mainEmail ) {
		LOG.debug( "Main email is " + mainEmail );
		this.mainEmail = mainEmail;
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			Email email = (Email) object;
			return email.getEmailAddress().equals( getEmailAddress() )
					&& email.isMainEmail().equals( isMainEmail() )
					&& email.getOwner().equals( getOwner() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getEmailAddress().hashCode() + isMainEmail().hashCode() + getOwner().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Email with Address = " )
				.append( getEmailAddress() )
				.append( " Is Main Email = " )
				.append( isMainEmail() )
				.append( " ]" );
		return sb.toString();
	}

}

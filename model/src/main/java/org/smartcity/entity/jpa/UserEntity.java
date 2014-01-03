package org.smartcity.entity.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Document;
import org.smartcity.entity.Email;
import org.smartcity.entity.User;
import org.smartcity.entity.jpa.converters.LongToBigIntegerConverter;

@Entity
@Table(
		name = User.TABLE_NAME
)
public class UserEntity
		implements	User<UserEntity, EmailEntity, DocumentEntity> {

	private static final Log LOG = LogFactory.getLog( UserEntity.class );

	@Id
	@GeneratedValue(
			generator = User.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = User.GENERATOR_NAME,
			sequenceName = User.SEQUENCE_NAME,
			initialValue = 1
	)
	@Column(
			name = User.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	@Convert(
			converter = LongToBigIntegerConverter.class
	)
	private BigInteger       ID;
	@Column(
			name = User.LAST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String           lastName;
	@Column(
			name = User.FIRST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String           firstName;
	@Column(
			name = User.MIDDLE_NAME_COLUMN_NAME,
			nullable = false
	)
	private String           middleName;
	@Column(
			name = User.NICK_NAME_COLUMN_NAME,
			nullable = false
	)
	private String           nickName;
	@Column(
			name = User.PASSWORD_COLUMN_NAME,
			nullable = false
	)
	private String           password;
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = User.IDENTIFY_DOCUMENT_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Document.ID_COLUMN_NAME
	)
	private DocumentEntity   identifyDocument;
	@OneToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumns(
			value = {
					@JoinColumn(
							name = User.ID_COLUMN_NAME,
							nullable = false,
							referencedColumnName = Email.USER_ID_COLUMN_NAME
					),
					@JoinColumn(
							name = Email.IS_MAIN_EMAIL_COLUMN,
							columnDefinition = Email.IS_MAIN_EMAIL_COLUMN + " = true"
					)
			}
	)
	private EmailEntity      mainEmail;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = User.ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Email.USER_ID_COLUMN_NAME
	)
	private Set<EmailEntity> userEmails;


	public UserEntity() {
	}

	public UserEntity(
			String lastName, String firstName, String middleName, String nickName, String password,
			DocumentEntity identifyDocument, EmailEntity mainEmail ) {
		setLastName( lastName )
				.setFirstName( firstName )
				.setMiddleName( middleName )
				.setNickName( nickName )
				.setPassword( password )
				.setIdentifyDocument( identifyDocument )
				.setMainEmail( mainEmail )
				.setUserEmails( new HashSet<EmailEntity>() );
	}

	@Override
	public BigInteger getID() {
		return ID;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public UserEntity setLastName( String lastName ) {
		this.lastName = lastName;
		return this;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public UserEntity setFirstName( String firstName ) {
		this.firstName = firstName;
		return this;
	}

	@Override
	public String getMiddleName() {
		return middleName;
	}

	@Override
	public UserEntity setMiddleName( String middleName ) {
		this.middleName = middleName;
		return this;
	}

	@Override
	public String getNickName() {
		return nickName;
	}

	@Override
	public UserEntity setNickName( String nickName ) {
		this.nickName = nickName;
		return this;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public UserEntity setPassword( String password ) {
		this.password = password;
		return this;
	}

	@Override
	public EmailEntity getMainEmail() {
		return mainEmail;
	}

	@Override
	public UserEntity setMainEmail( EmailEntity mainEmail ) {
		this.mainEmail = mainEmail;
		return this;
	}

	@Override
	public Set<EmailEntity> getUserEmails() {
		return userEmails;
	}

	@Override
	public UserEntity setUserEmails( Set<EmailEntity> userEmails ) {
		this.userEmails = userEmails;
		return this;
	}

	@Override
	public UserEntity addUserEmail( EmailEntity email ) {
		userEmails.add( email );
		return this;
	}

	@Override
	public UserEntity addUserEmails( Collection<EmailEntity> emails ) {
		this.userEmails.addAll( emails );
		return this;
	}

	@Override
	public DocumentEntity getIdentifyDocument() {
		return identifyDocument;
	}

	@Override
	public UserEntity setIdentifyDocument( DocumentEntity identifyDocument ) {
		this.identifyDocument = identifyDocument;
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			UserEntity user = (UserEntity) object;
			return user.getNickName().equals( getNickName() )
				   && user.getFirstName().equals( getFirstName() )
				   && user.getMiddleName().equals( getMiddleName() )
				   && user.getLastName().equals( getLastName() )
				   && user.getIdentifyDocument().equals( getIdentifyDocument() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getNickName().hashCode()
			   + getFirstName().hashCode()
			   + getMiddleName().hashCode()
			   + getLastName().hashCode()
			   + getIdentifyDocument().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ User with ID = " )
				.append( getID() )
				.append( " NickName = " )
				.append( getNickName() )
				.append( " ]" );
		return sb.toString();
	}

}
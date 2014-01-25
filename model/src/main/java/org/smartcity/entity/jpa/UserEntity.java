package org.smartcity.entity.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Document;
import org.smartcity.entity.User;

@Entity
@Table(
		name = User.TABLE_NAME
)
public class UserEntity
		implements User<
		UserEntity,
		EmailEntity,
		DocumentEntity> {

	private static final Log LOG = LogFactory.getLog( UserEntity.class );

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD                = "ID";
	public static final String LAST_NAME_FIELD         = "lastName";
	public static final String FIRST_NAME_FIELD        = "firstName";
	public static final String MIDDLE_NAME_FIELD       = "middleName";
	public static final String NICK_NAME_FIELD         = "nickName";
	public static final String PASSWORD_FIELD          = "password";
	public static final String IDENTIFY_DOCUMENT_FIELD = "identifyDocument";
	public static final String USER_EMAILS_FIELD       = "userEmails";
	public static final String DOCUMENTS_FILED         = "documents";

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
	private BigInteger          ID;
	@Column(
			name = User.LAST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String              lastName;
	@Column(
			name = User.FIRST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String              firstName;
	@Column(
			name = User.MIDDLE_NAME_COLUMN_NAME,
			nullable = false
	)
	private String              middleName;
	@Column(
			name = User.NICK_NAME_COLUMN_NAME,
			nullable = false
	)
	private String              nickName;
	@Column(
			name = User.PASSWORD_COLUMN_NAME,
			nullable = false
	)
	private String              password;
	@Transient
	private DocumentEntity      identifyDocument;
	@Transient
	private EmailEntity         mainEmail;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = EmailEntity.OWNER_FIELD
	)
	private Set<EmailEntity>    userEmails;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = DocumentEntity.OWNER_FIELD
	)
	private Set<DocumentEntity> documents;


	public UserEntity() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public UserEntity(
			String lastName,
			String firstName,
			String middleName,
			String nickName,
			String password ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setLastName( lastName )
				.setFirstName( firstName )
				.setMiddleName( middleName )
				.setNickName( nickName )
				.setPassword( password )
				.setUserEmails( new HashSet<EmailEntity>() )
				.setDocuments( new HashSet<DocumentEntity>() )
				.setMainEmail( null );
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
		LOG.debug( "Last name is " + lastName );
		this.lastName = lastName;
		return this;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public UserEntity setFirstName( String firstName ) {
		LOG.debug( "First name is " + firstName );
		this.firstName = firstName;
		return this;
	}

	@Override
	public String getMiddleName() {
		return middleName;
	}

	@Override
	public UserEntity setMiddleName( String middleName ) {
		LOG.debug( "Middle name is " + middleName );
		this.middleName = middleName;
		return this;
	}

	@Override
	public String getNickName() {
		return nickName;
	}

	@Override
	public UserEntity setNickName( String nickName ) {
		LOG.debug( "Nick name is " + nickName );
		this.nickName = nickName;
		return this;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public UserEntity setPassword( String password ) {
		LOG.debug( "Password is " + password );
		this.password = password;
		return this;
	}

	@Override
	public EmailEntity getMainEmail() {
		return mainEmail;
	}

	@Override
	public UserEntity setMainEmail( EmailEntity mainEmail ) {
		LOG.debug( "Main email is " + mainEmail );
		if( mainEmail != null ) {
			this.mainEmail = mainEmail;
		}
		else {
			for( EmailEntity email : userEmails ) {
				if( email.isMainEmail() ) {
					this.mainEmail = email;
					break;
				}
			}
		}
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
		LOG.debug( "Identify document is " + identifyDocument );
		this.identifyDocument = identifyDocument;
		return this;
	}

	@Override
	public Set<DocumentEntity> getDocuments() {
		return documents;
	}

	@Override
	public UserEntity setDocuments( Set<DocumentEntity> documents ) {
		this.documents = documents;
		for( DocumentEntity document : documents ) {
			if( document.isIdentifyDocument() ) {
				identifyDocument = document;
				break;
			}
		}
		return this;
	}

	@Override
	public UserEntity addDocument( DocumentEntity document ) {
		documents.add( document );
		return this;
	}

	@Override
	public UserEntity addDocuments( Collection<DocumentEntity> documents ) {
		this.documents.addAll( documents );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
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

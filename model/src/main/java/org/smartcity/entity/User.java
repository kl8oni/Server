package org.smartcity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@Entity
@Table(
		name = User.TABLE_NAME
)
public class User {

	private static final Log LOG = LogFactory.getLog( User.class );

	public static final String TABLE_NAME                       = "USERS";
	public static final String GENERATOR_NAME                   = "USER_ID_GENERATOR";
	public static final String SEQUENCE_NAME                    = "USER_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME                   = "USER_ID";
	public static final String LAST_NAME_COLUMN_NAME            = "LAST_NAME";
	public static final String FIRST_NAME_COLUMN_NAME           = "FIRST_NAME";
	public static final String MIDDLE_NAME_COLUMN_NAME          = "MIDDLE_NAME";
	public static final String NICK_NAME_COLUMN_NAME            = "NICK_NAME";
	public static final String PASSWORD_COLUMN_NAME             = "PASSWORD";
	public static final String IDENTIFY_DOCUMENT_ID_COLUMN_NAME = "IDENTIFY_DOCUMENT_ID";

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
	private BigInteger    ID;
	@Column(
			name = User.LAST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String        lastName;
	@Column(
			name = User.FIRST_NAME_COLUMN_NAME,
			nullable = false
	)
	private String        firstName;
	@Column(
			name = User.MIDDLE_NAME_COLUMN_NAME,
			nullable = false
	)
	private String        middleName;
	@Column(
			name = User.NICK_NAME_COLUMN_NAME,
			nullable = false
	)
	private String        nickName;
	@Column(
			name = User.PASSWORD_COLUMN_NAME,
			nullable = false
	)
	private String        password;
	@Transient
	private Document      identifyDocument;
	@Transient
	private Email         mainEmail;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = Email.OWNER_FIELD
	)
	private Set<Email>    userEmails;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = Document.OWNER_FIELD
	)
	private Set<Document> documents;


	public User() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public User(
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
				.setUserEmails( new HashSet<Email>() )
				.setDocuments( new HashSet<Document>() );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getLastName() {
		return lastName;
	}

	public User setLastName( String lastName ) {
		LOG.debug( "Last name is " + lastName );
		this.lastName = lastName;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public User setFirstName( String firstName ) {
		LOG.debug( "First name is " + firstName );
		this.firstName = firstName;
		return this;
	}

	public String getMiddleName() {
		return middleName;
	}

	public User setMiddleName( String middleName ) {
		LOG.debug( "Middle name is " + middleName );
		this.middleName = middleName;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public User setNickName( String nickName ) {
		LOG.debug( "Nick name is " + nickName );
		this.nickName = nickName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public User setPassword( String password ) {
		LOG.debug( "Password is " + password );
		this.password = password;
		return this;
	}

	public Email getMainEmail() {
		return mainEmail;
	}

	public Set<Email> getUserEmails() {
		return userEmails;
	}

	public User setUserEmails( Set<Email> userEmails ) {
		this.userEmails = userEmails;
		return this;
	}

	public User addUserEmail( Email email ) {
		if( email.isMainEmail() ) {
			mainEmail = email;
		}
		userEmails.add( email );
		return this;
	}

	public User addUserEmails( Collection<Email> emails ) {
		this.userEmails.addAll( emails );
		return this;
	}

	public Document getIdentifyDocument() {
		return identifyDocument;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public User setDocuments( Set<Document> documents ) {
		this.documents = documents;
		for( Document document : documents ) {
			if( document.isIdentifyDocument() ) {
				identifyDocument = document;
				break;
			}
		}
		return this;
	}

	public User addDocument( Document document ) {
		if( document.isIdentifyDocument() ) {
			identifyDocument = document;
		}
		documents.add( document );
		return this;
	}

	public User addDocuments( Collection<Document> documents ) {
		this.documents.addAll( documents );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			User user = (User) object;
			return user.getNickName().equals( getNickName() )
				   && user.getFirstName().equals( getFirstName() )
				   && user.getMiddleName().equals( getMiddleName() )
				   && user.getLastName().equals( getLastName() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getNickName().hashCode()
			   + getFirstName().hashCode()
			   + getMiddleName().hashCode()
			   + getLastName().hashCode();
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

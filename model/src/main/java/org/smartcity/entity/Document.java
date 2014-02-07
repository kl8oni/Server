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

import java.io.Serializable;
import java.math.BigInteger;

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 * todo i don't like implementation need to do better look
 */
@Entity
@Table(
		name = Document.TABLE_NAME
)
public class Document
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( Document.class );

	public static final String TABLE_NAME                       = "DOCUMENTS";
	public static final String GENERATOR_NAME                   = "DOCUMENT_ID_GENERATOR";
	public static final String SEQUENCE_NAME                    = "DOCUMENT_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME                   = "DOCUMENT_ID";
	public static final String NAME_COLUMN_NAME                 = "NAME";
	public static final String SERIES_COLUMN_NAME               = "SERIES";
	public static final String NUMBER_COLUMN_NAME               = "NUMBER";
	public static final String DELIVERY_DATE_COLUMN_NAME        = "DELIVERY_DATE";
	public static final String USER_ID_COLUMN_NAME              = "USER_ID";
	public static final String GOVERNMENT_OFFICE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_ID";
	public static final String DOCUMENT_TEMPLATE_ID_COLUMN_NAME = "DOCUMENT_TEMPLATE_ID";
	public static final String IDENTIFY_DOCUMENT_COLUMN_NAME    = "IDENTIFY_DOCUMENT";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD            = "ID";
	public static final String NAME_FIELD          = "name";
	public static final String SERIES_FIELD        = "series";
	public static final String NUMBER_FIELD        = "number";
	public static final String DELIVERY_DATE_FIELD = "deliveryDate";
	public static final String OWNER_FIELD         = "owner";
	public static final String OFFICE_FIELD        = "office";
	public static final String TEMPLATE_FIELD      = "template";

	@Id
	@GeneratedValue(
			generator = Document.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = Document.GENERATOR_NAME,
			sequenceName = Document.SEQUENCE_NAME,
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = Document.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger       ID;
	@Column(
			name = Document.NAME_COLUMN_NAME,
			nullable = false
	)
	private String           name;
	@Column(
			name = Document.SERIES_COLUMN_NAME,
			nullable = false
	)
	private String           series;
	@Column(
			name = Document.NUMBER_COLUMN_NAME,
			nullable = false
	)
	private Long             number;
	@Column(
			name = Document.DELIVERY_DATE_COLUMN_NAME,
			nullable = false
	)
	private Date             deliveryDate;
	@Column(
			name = Document.IDENTIFY_DOCUMENT_COLUMN_NAME,
			nullable = false
	)
	private Boolean          identifyDocument;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.USER_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = User.ID_COLUMN_NAME
	)
	private User             owner;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.GOVERNMENT_OFFICE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOffice.ID_COLUMN_NAME
	)
	private GovernmentOffice office;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.DOCUMENT_TEMPLATE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = DocumentTemplate.ID_COLUMN_NAME
	)
	private DocumentTemplate template;

	public Document() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public Document(
			String name,
			String series,
			Long number,
			Date deliveryDate,
			User owner,
			GovernmentOffice office,
			DocumentTemplate template,
			Boolean identifyDocument ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setSeries( series )
				.setNumber( number )
				.setDeliveryDate( deliveryDate )
				.setOwner( owner )
				.setOffice( office )
				.setTemplate( template )
				.setIdentifyDocument( identifyDocument );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public Document setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public String getSeries() {
		return series;
	}

	public Document setSeries( String series ) {
		LOG.debug( "Series is " + series );
		this.series = series;
		return this;
	}

	public Long getNumber() {
		return number;
	}

	public Document setNumber( Long number ) {
		LOG.debug( "Number is " + number );
		this.number = number;
		return this;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public Document setDeliveryDate( Date deliveryDate ) {
		LOG.debug( "Delivery date is " + deliveryDate );
		this.deliveryDate = deliveryDate;
		return this;
	}

	public User getOwner() {
		return owner;
	}

	public Document setOwner( User owner ) {
		LOG.debug( "Owner is " + owner );
		this.owner = owner;
		return this;
	}

	public DocumentTemplate getTemplate() {
		return template;
	}

	public Document setTemplate( DocumentTemplate template ) {
		LOG.debug( "Template is " + template );
		this.template = template;
		return this;
	}

	public GovernmentOffice getOffice() {
		return office;
	}

	public Document setOffice( GovernmentOffice office ) {
		LOG.debug( "Office is " + office );
		this.office = office;
		return this;
	}

	public Boolean isIdentifyDocument() {
		return identifyDocument;
	}

	public Document setIdentifyDocument( Boolean identifyDocument ) {
		LOG.debug( "Identify document " + identifyDocument );
		this.identifyDocument = identifyDocument;
		return this;
	}

	public String formatted() {
		//TODO need to implement
		// see DocumentTemplate#getSeriesPattern and DocumentTemplate#getNumberPattern
		return "formattedDocumentString";
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			Document document = (Document) object;
			return document.getName().equals( getName() )
				   && document.getNumber().equals( getNumber() )
				   && document.getSeries().equals( getSeries() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getNumber().hashCode() + getSeries().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Document with ID = " )
				.append( getID() )
				.append( " Name = " )
				.append( getName() )
				.append( " Series = " )
				.append( getSeries() )
				.append( " Number = " )
				.append( getNumber() );
		return sb.toString();
	}

}

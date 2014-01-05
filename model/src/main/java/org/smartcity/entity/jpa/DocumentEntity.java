package org.smartcity.entity.jpa;

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

import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.User;

@Entity
@Table(
		name = Document.TABLE_NAME
)
public class DocumentEntity
		implements	Document<DocumentEntity, UserEntity, DocumentTemplateEntity, GovernmentOfficeEntity> {

	private static final Log LOG = LogFactory.getLog( DocumentEntity.class );

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
			initialValue = 1
	)
	@Column(
			name = Document.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger             ID;
	@Column(
			name = Document.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                 name;
	@Column(
			name = Document.SERIES_COLUMN_NAME,
			nullable = false
	)
	private String                 series;
	@Column(
			name = Document.NUMBER_COLUMN_NAME,
			nullable = false
	)
	private long                   number;
	@Column(
			name = Document.DELIVERY_DATE_COLUMN_NAME,
			nullable = false
	)
	private Date                   deliveryDate;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.USER_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = User.ID_COLUMN_NAME
	)
	private UserEntity             owner;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.GOVERNMENT_OFFICE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOffice.ID_COLUMN_NAME
	)
	private GovernmentOfficeEntity office;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = Document.DOCUMENT_TEMPLATE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = DocumentTemplate.ID_COLUMN_NAME
	)
	private DocumentTemplateEntity template;

	public DocumentEntity() {
	}

	public DocumentEntity(
			String name,
			String series,
			long number,
			Date deliveryDate,
			UserEntity owner,
			GovernmentOfficeEntity office,
			DocumentTemplateEntity template ) {
		setName( name )
				.setSeries( series )
				.setNumber( number )
				.setDeliveryDate( deliveryDate )
				.setOwner( owner )
				.setOffice( office )
				.setTemplate( template );
	}

	@Override
	public BigInteger getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public DocumentEntity setName( String name ) {
		this.name = name;
		return this;
	}

	@Override
	public String getSeries() {
		return series;
	}

	@Override
	public DocumentEntity setSeries( String series ) {
		this.series = series;
		return this;
	}

	@Override
	public long getNumber() {
		return number;
	}

	@Override
	public DocumentEntity setNumber( long number ) {
		this.number = number;
		return this;
	}

	@Override
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	@Override
	public DocumentEntity setDeliveryDate( Date deliveryDate ) {
		this.deliveryDate = deliveryDate;
		return this;
	}

	@Override
	public UserEntity getOwner() {
		return owner;
	}

	@Override
	public DocumentEntity setOwner( UserEntity owner ) {
		this.owner = owner;
		return this;
	}

	@Override
	public DocumentTemplateEntity getTemplate() {
		return template;
	}

	@Override
	public DocumentEntity setTemplate( DocumentTemplateEntity template ) {
		this.template = template;
		return this;
	}

	@Override
	public GovernmentOfficeEntity getOffice() {
		return office;
	}

	@Override
	public DocumentEntity setOffice( GovernmentOfficeEntity office ) {
		this.office = office;
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			DocumentEntity document = (DocumentEntity)object;
			return document.getName().equals( getName() )
				   && document.getNumber() == getNumber()
				   && document.getSeries().equals( getSeries() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + (int)getNumber() + getSeries().hashCode();
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

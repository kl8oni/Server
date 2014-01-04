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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.GovernmentOfficeType;

@Entity
@Table(
		name = DocumentTemplate.TABLE_NAME
)
public class DocumentTemplateEntity
		implements DocumentTemplate<DocumentTemplateEntity, DocumentEntity, GovernmentOfficeTypeEntity> {

	private static final Log LOG = LogFactory.getLog( DocumentTemplateEntity.class );

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD                     = "ID";
	public static final String NAME_FIELD                   = "name";
	public static final String SERIES_PATTERN_FIELD         = "seriesPattern";
	public static final String NUMBER_PATTERN_FILED         = "numberPattern";
	public static final String GOVERNMENT_OFFICE_TYPE_FIELD = "governmentOfficeType";
	public static final String DOCUMENT_FIELD               = "document";

	@Id
	@GeneratedValue(
			generator = DocumentTemplate.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = DocumentTemplate.GENERATOR_NAME,
			sequenceName = DocumentTemplate.SEQUENCE_NAME,
			initialValue = 1
	)
	@Column(
			name = DocumentTemplate.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger                 ID;
	@Column(
			name = DocumentTemplate.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                     name;
	@Column(
			name = DocumentTemplate.SERIES_PATTERN_COLUMN_NAME,
			nullable = false
	)
	private String                     seriesPattern;
	@Column(
			name = DocumentTemplate.NUMBER_PATTERN_COLUMN_NAME,
			nullable = false
	)
	private String                     numberPattern;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = DocumentTemplate.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeTypeEntity governmentOfficeType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = DocumentEntity.TEMPLATE_FIELD
	)
	private Set<DocumentEntity>        documents;

	public DocumentTemplateEntity() {
	}

	public DocumentTemplateEntity(
			String name,
			String seriesPattern,
			String numberPattern,
			GovernmentOfficeTypeEntity governmentOfficeType ) {
		setName( name )
				.setSeriesPattern( seriesPattern )
				.setNumberPattern( numberPattern )
				.setGovernmentOfficeType( governmentOfficeType )
				.setDocuments( new HashSet<DocumentEntity>() );
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
	public DocumentTemplateEntity setName( String name ) {
		this.name = name;
		return this;
	}

	@Override
	public String getSeriesPattern() {
		return seriesPattern;
	}

	@Override
	public DocumentTemplateEntity setSeriesPattern( String seriesPattern ) {
		this.seriesPattern = seriesPattern;
		return this;
	}

	@Override
	public String getNumberPattern() {
		return numberPattern;
	}

	@Override
	public DocumentTemplateEntity setNumberPattern( String numberPattern ) {
		this.numberPattern = numberPattern;
		return this;
	}

	@Override
	public GovernmentOfficeTypeEntity getGovernmentOfficeType() {
		return governmentOfficeType;
	}

	@Override
	public DocumentTemplateEntity setGovernmentOfficeType( GovernmentOfficeTypeEntity governmentOfficeType ) {
		this.governmentOfficeType = governmentOfficeType;
		return this;
	}

	@Override
	public Set<DocumentEntity> getDocuments() {
		return documents;
	}

	@Override
	public DocumentTemplateEntity setDocuments( Set<DocumentEntity> documents ) {
		this.documents = documents;
		return this;
	}

	@Override
	public DocumentTemplateEntity addDocument( DocumentEntity document ) {
		documents.add( document );
		return this;
	}

	@Override
	public DocumentTemplateEntity addDocuments( Collection<DocumentEntity> documents ) {
		this.documents.addAll( documents );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			DocumentTemplateEntity documentTemplate = (DocumentTemplateEntity)object;
			return documentTemplate.getName().equals( getName() )
				   && documentTemplate.getNumberPattern().equals( getNumberPattern() )
				   && documentTemplate.getSeriesPattern().equals( getSeriesPattern() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getNumberPattern().hashCode() + getSeriesPattern().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Document Template with ID = " )
				.append( getID() )
				.append( " Name = " )
				.append( getName() )
				.append( " Series Pattern = " )
				.append( getSeriesPattern() )
				.append( " Number Pattern = " )
				.append( getNumberPattern() )
				.append( " ]" );
		return sb.toString();
	}

}
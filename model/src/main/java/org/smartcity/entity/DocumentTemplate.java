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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 */
@Entity
@Table(
		name = DocumentTemplate.TABLE_NAME
)
public class DocumentTemplate
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( DocumentTemplate.class );

	public static final String TABLE_NAME                            = "DOCUMENT_TEMPLATES";
	public static final String GENERATOR_NAME                        = "DOCUMENT_TEMPLATE_ID_GENERATOR";
	public static final String SEQUENCE_NAME                         = "DOCUMENT_TEMPLATE_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME                        = "DOCUMENT_TEMPLATE_ID";
	public static final String NAME_COLUMN_NAME                      = "NAME";
	public static final String SERIES_PATTERN_COLUMN_NAME            = "SERIES_PATTERN";
	public static final String NUMBER_PATTERN_COLUMN_NAME            = "NUMBER_PATTERN";
	public static final String GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_TYPE_ID";

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
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = DocumentTemplate.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger           ID;
	@Column(
			name = DocumentTemplate.NAME_COLUMN_NAME,
			nullable = false
	)
	private String               name;
	@Column(
			name = DocumentTemplate.SERIES_PATTERN_COLUMN_NAME,
			nullable = false
	)
	private String               seriesPattern;
	@Column(
			name = DocumentTemplate.NUMBER_PATTERN_COLUMN_NAME,
			nullable = false
	)
	private String               numberPattern;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = DocumentTemplate.GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = GovernmentOfficeType.ID_COLUMN_NAME
	)
	private GovernmentOfficeType governmentOfficeType;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = Document.TEMPLATE_FIELD
	)
	private Set<Document>        documents;

	public DocumentTemplate() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public DocumentTemplate(
			String name,
			String seriesPattern,
			String numberPattern,
			GovernmentOfficeType governmentOfficeType ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setSeriesPattern( seriesPattern )
				.setNumberPattern( numberPattern )
				.setGovernmentOfficeType( governmentOfficeType )
				.setDocuments( new HashSet<Document>() );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public DocumentTemplate setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public String getSeriesPattern() {
		return seriesPattern;
	}

	public DocumentTemplate setSeriesPattern( String seriesPattern ) {
		LOG.debug( "Series pattern is " + seriesPattern );
		this.seriesPattern = seriesPattern;
		return this;
	}

	/**
	 * This method in para with setter should use in generation of
	 * {@link org.smartcity.entity.Document} number property
	 *
	 * @return number formatted pattern for document number
	 */
	public String getNumberPattern() {
		return numberPattern;
	}

	/**
	 * This method in para with getter should use in generation of
	 * {@link org.smartcity.entity.Document} number property
	 *
	 * @return number formatted pattern for document number
	 */
	public DocumentTemplate setNumberPattern( String numberPattern ) {
		LOG.debug( "Number pattern is " + numberPattern );
		this.numberPattern = numberPattern;
		return this;
	}

	public GovernmentOfficeType getGovernmentOfficeType() {
		return governmentOfficeType;
	}

	public DocumentTemplate setGovernmentOfficeType( GovernmentOfficeType governmentOfficeType ) {
		LOG.debug( "Government office type is " + governmentOfficeType );
		this.governmentOfficeType = governmentOfficeType;
		return this;
	}

	public Set<Document> getDocuments() {
		return documents;
	}

	public DocumentTemplate setDocuments( Set<Document> documents ) {
		this.documents = documents;
		return this;
	}

	public DocumentTemplate addDocument( Document document ) {
		documents.add( document );
		return this;
	}

	public DocumentTemplate addDocuments( Collection<Document> documents ) {
		this.documents.addAll( documents );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			DocumentTemplate documentTemplate = (DocumentTemplate) object;
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

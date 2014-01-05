package org.smartcity.entity;

import java.io.Serializable;

import java.math.BigInteger;
import java.util.Collection;
import java.util.Set;

public interface DocumentTemplate<DT extends DocumentTemplate,
								  D extends Document,
								  OT extends GovernmentOfficeType>
		extends		Serializable {

	String TABLE_NAME                            = "DOCUMENT_TEMPLATES";
	String GENERATOR_NAME                        = "DOCUMENT_TEMPLATE_ID_GENERATOR";
	String SEQUENCE_NAME                         = "DOCUMENT_TEMPLATE_ID_SEQUENCE";
	String ID_COLUMN_NAME                        = "DOCUMENT_TEMPLATE_ID";
	String NAME_COLUMN_NAME                      = "NAME";
	String SERIES_PATTERN_COLUMN_NAME            = "SERIES_PATTERN";
	String NUMBER_PATTERN_COLUMN_NAME            = "NUMBER_PATTERN";
	String GOVERNMENT_OFFICE_TYPE_ID_COLUMN_NAME = "GOVERNMENT_OFFICE_TYPE_ID";

	public BigInteger getID();

	public String getName();

	public DT setName( String name );

	public String getSeriesPattern();

	public DT setSeriesPattern( String seriesPattern );

	public String getNumberPattern();

	public DT setNumberPattern( String numberPattern );

	public OT getGovernmentOfficeType();

	public DT setGovernmentOfficeType( OT governmentOfficeType );

	public Set<D> getDocuments();

	public DT setDocuments( Set<D> documents );

	public DT addDocument( D document );

	public DT addDocuments( Collection<D> documents );

	public boolean equals( Object object );

	public int hashCode();

	public String toString();

}

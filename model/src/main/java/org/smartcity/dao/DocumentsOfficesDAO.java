package org.smartcity.dao;


import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;
import org.smartcity.entity.User;

import java.math.BigInteger;
import java.util.Date;

public interface DocumentsOfficesDAO {

	DocumentTemplate createDocumentTemplate(
			String name,
			String seriesPattern,
			String numberPattern,
			String officeTypeName,
			GovernmentOfficeType parent );

	DocumentTemplate createDocumentTemplate(
			String name,
			String seriesPattern,
			String numberPattern,
			GovernmentOfficeType officeType );

	DocumentTemplate createDocumentTemplate(
			String name,
			String seriesPattern,
			String numberPattern,
			String officeTypeName );

	void removeDocumentTemplate( DocumentTemplate documentTemplate );

	DocumentTemplate findDocumentTemplate( BigInteger documentTemplateID );

	Document createDocument(
			String name,
			String series,
			Long number,
			Date deliveryDate,
			User owner,
			GovernmentOffice office,
			DocumentTemplate template );

	Document createIdentifyDocument(
			String name,
			String series,
			Long number,
			Date deliveryDate,
			User owner,
			GovernmentOffice office,
			DocumentTemplate template );

	void removeDocument( Document document );

	Document findDocument( BigInteger documentID );

	GovernmentOfficeType createOfficeType( String name, GovernmentOfficeType parent );

	GovernmentOfficeType createRootOfficeType( String name );

	void removeOfficeType( GovernmentOfficeType officeType );

	GovernmentOfficeType findOfficeType( BigInteger officeTypeID );

	GovernmentOffice createOffice(
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern,
			GovernmentOfficeType officeType);

	void removeOffice( GovernmentOffice office );

	GovernmentOffice findOffice( BigInteger officeID );

}

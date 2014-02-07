package org.smartcity.dao;

import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;
import org.smartcity.entity.User;

import java.util.Date;

public class DocumentsOfficesDAOImpl
		extends AbstractEmbeddableEntityDAO
		implements DocumentsOfficesDAO,
				   EmbeddableEntityDAO {

	@Override
	public DocumentTemplate createDocumentTemplate(
			String name,
			String seriesPattern,
			String numberPattern,
			String officeTypeName,
			GovernmentOfficeType parent ) {
		return null;
	}

	@Override
	public void removeDocumentTemplate( DocumentTemplate documentTemplate ) {

	}

	@Override
	public Document createDocument(
			String name,
			String series,
			Long number,
			Date deliveryDate,
			User owner,
			GovernmentOffice office,
			DocumentTemplate template ) {
		return null;
	}

	@Override
	public Document createIdentifyDocument(
			String name,
			String series,
			Long number,
			Date deliveryDate,
			User owner,
			GovernmentOffice office,
			DocumentTemplate template ) {
		return null;
	}

	@Override
	public void removeDocument( Document document ) {

	}

	@Override
	public GovernmentOfficeType createOfficeType( String name, GovernmentOfficeType parent ) {
		return null;
	}

	@Override
	public GovernmentOfficeType createRootOfficeType( String name ) {
		return null;
	}

	@Override
	public void removeOfficeType( GovernmentOfficeType officeType ) {

	}

	@Override
	public GovernmentOffice createOffice(
			String name,
			String state,
			String city,
			String street,
			Short buildingNumber,
			Short stateCode,
			Short cityCode,
			Integer number,
			String pattern,
			GovernmentOfficeType officeType ) {
		return null;
	}

	@Override
	public void removeOffice( GovernmentOffice office ) {

	}

}

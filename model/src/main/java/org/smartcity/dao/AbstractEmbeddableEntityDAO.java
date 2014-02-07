package org.smartcity.dao;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public abstract class AbstractEmbeddableEntityDAO
		implements EmbeddableEntityDAO {

	private static final Log LOG = LogFactory.getLog( AbstractEmbeddableEntityDAO.class );

	@Inject
	private EntityDAO          entityDAO;
	@Inject
	private AddressDAO         addressDAO;
	@Inject
	private TelephoneNumberDAO telephoneNumberDAO;

	public void setAddressDAO( AddressDAO addressDAO ) {
		LOG.debug( "Address dao implementation is " + addressDAO.getClass() );
		this.addressDAO = addressDAO;
	}

	public void setTelephoneDAO( TelephoneNumberDAO telephoneNumberDAO ) {
		LOG.debug( "Telephone dao implementation is " + telephoneNumberDAO.getClass() );
		this.telephoneNumberDAO = telephoneNumberDAO;
	}

	public void setEntityDAO( EntityDAO entityDAO ) {
		LOG.debug( "Entity dao implementation is " + entityDAO.getClass() );
		this.entityDAO = entityDAO;
	}

	public AddressDAO getAddressDAO() {
		LOG.debug( "Get address dao" );
		return addressDAO;
	}

	public TelephoneNumberDAO getTelephoneNumberDAO() {
		LOG.debug( "Get telephone number dao" );
		return telephoneNumberDAO;
	}

	public EntityDAO getEntityDAO() {
		LOG.debug( "Get entity dao" );
		return entityDAO;
	}

}

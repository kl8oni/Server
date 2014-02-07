package org.smartcity.dao;


public interface EmbeddableEntityDAO {

	void setAddressDAO( AddressDAO addressDAO );

	void setTelephoneDAO( TelephoneNumberDAO telephoneNumberDAO );

	void setEntityDAO( EntityDAO entityDAO );

}

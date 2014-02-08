package org.smartcity.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.smartcity.UtilTestClass;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

@RunWith(
		value = Arquillian.class
)
@Named
@RequestScoped
public class EmbeddableEntityDAOInjectionTest {

	@Inject
	private BankDAOImpl             bankDAO;
	@Inject
	private UserDAOImpl             userDAO;
	@Inject
	private DocumentsOfficesDAOImpl documentsOfficesDAO;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive createArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	public void setBankDAO( BankDAOImpl bankDAO ) {
		this.bankDAO = bankDAO;
	}

	public void setUserDAO( UserDAOImpl userDAO ) {
		this.userDAO = userDAO;
	}

	public void setDocumentsOfficesDAO( DocumentsOfficesDAOImpl documentsOfficesDAO ) {
		this.documentsOfficesDAO = documentsOfficesDAO;
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testBankDAOInjection() {
		Assert.assertNotNull( bankDAO.getAddressDAO() );
		Assert.assertNotNull( bankDAO.getEntityDAO() );
		Assert.assertNotNull( bankDAO.getTelephoneNumberDAO() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testUserDAOInjection() {
		Assert.assertNotNull( userDAO.getAddressDAO() );
		Assert.assertNotNull( userDAO.getEntityDAO() );
		Assert.assertNotNull( userDAO.getTelephoneNumberDAO() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testDocumentsOfficesDAOInjection() {
		Assert.assertNotNull( documentsOfficesDAO.getAddressDAO() );
		Assert.assertNotNull( documentsOfficesDAO.getEntityDAO() );
		Assert.assertNotNull( documentsOfficesDAO.getTelephoneNumberDAO() );
	}

}

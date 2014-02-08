package org.smartcity.dao;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.runner.RunWith;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

@RunWith(
		value = Arquillian.class
)
@Named
@RequestScoped
public abstract class AbstractDAOTest {

	@Inject
	private BankDAO             bankDAO;
	@Inject
	private UserDAO             userDAO;
	@Inject
	private DocumentsOfficesDAO documentsOfficesDAO;

	public void setBankDAO( BankDAO bankDAO ) {
		this.bankDAO = bankDAO;
	}

	public void setDocumentsOfficesDAO( DocumentsOfficesDAO documentsOfficesDAO ) {
		this.documentsOfficesDAO = documentsOfficesDAO;
	}

	public void setUserDAO( UserDAO userDAO ) {
		this.userDAO = userDAO;
	}

	protected BankDAO getBankDAO() {
		return bankDAO;
	}

	protected UserDAO getUserDAO() {
		return userDAO;
	}

	protected DocumentsOfficesDAO getDocumentsOfficesDAO() {
		return documentsOfficesDAO;
	}

}

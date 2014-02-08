package org.smartcity.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

import org.smartcity.UtilTestClass;
import org.smartcity.entity.Address;
import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.Email;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;
import org.smartcity.entity.TelephoneNumber;
import org.smartcity.entity.User;

import javax.enterprise.context.RequestScoped;

import javax.inject.Inject;
import javax.inject.Named;

import javax.transaction.UserTransaction;

@RunWith(
		value = Arquillian.class
)
@Named
@RequestScoped
public class CreateEntitiesThroughDAOTest {

	@Inject
	private BankDAO             bankDAO;
	@Inject
	private UserDAO             userDAO;
	@Inject
	private DocumentsOfficesDAO documentsOfficesDAO;
	@Inject
	private UserTransaction     utx;

	public void setBankDAO( BankDAO bankDAO ) {
		this.bankDAO = bankDAO;
	}

	public void setDocumentsOfficesDAO( DocumentsOfficesDAO documentsOfficesDAO ) {
		this.documentsOfficesDAO = documentsOfficesDAO;
	}

	public void setUserDAO( UserDAO userDAO ) {
		this.userDAO = userDAO;
	}

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUser()
			throws Exception {
		try {
			utx.begin();
			User user = userDAO.createUser( "last", "first", "middle", "nick", "password" );
			Assert.assertNotNull( user );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUserMainEmail()
			throws Exception {
		try {
			utx.begin();
			User user = userDAO.createUser( "new last", "new first", "new middle", "new nick", "new password" );
			Email mainEmail = userDAO.createMainUserEmail( "user", user );
			Assert.assertNotNull( mainEmail );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUserEmail()
			throws Exception {
		try {
			utx.begin();
			User user = userDAO.createUser( "last", "first", "middle", "nick", "password" );
			Email email = userDAO.createUserEmail( "email", user );
			Assert.assertNotNull( email );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUserEmailWithUser()
			throws Exception {
		try {
			utx.begin();
			Email email = userDAO.createUserEmail(
					"email-address",
					"last",
					"first",
					"middle",
					"nick",
					"password",
					false );
			Assert.assertNotNull( email );
			Assert.assertNotNull( email.getOwner() );
		}
		finally {
			utx.commit();
		}
	}
  /*
	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBank()
			throws Exception {
		try {
			utx.begin();
			Bank bank = bankDAO.createBank( "bankName", "bankWebSite" );
			Assert.assertNotNull( bank );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankBranch()
			throws Exception {
		try {
			utx.begin();
			Bank bank = bankDAO.createBank( "otherBankName", "otherBankWebSite" );
			BankBranch bankBranch = bankDAO.createBankBranch(
					bank,
					"branchName",
					"state",
					"city",
					"street",
					(short) 10,
					(short) 10,
					(short) 50,
					159842,
					null );
			Assert.assertNotNull( bankBranch );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankBranchWithBank()
			throws Exception {
		try {
			utx.begin();
			BankBranch bankBranch = bankDAO.createBankBranch(
					"bankName",
					"bankWebsite",
					"nameBranch",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					145875,
					null );
			Assert.assertNotNull( bankBranch );
			Assert.assertNotNull( bankBranch.getBank() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccount()
			throws Exception {
		try {
			utx.begin();
			Bank bank = bankDAO.createBank( "otherBankName", "otherBankWebSite" );
			BankBranch bankBranch = bankDAO.createBankBranch(
					bank,
					"branchName",
					"state",
					"city",
					"street",
					(short) 10,
					(short) 10,
					(short) 50,
					159842,
					null );
			User user = userDAO.createUser( "last", "first", "middle", "nick", "password" );
			BankAccount bankAccount = bankDAO.createBankAccount( 1000L, user, bankBranch, bank );
			Assert.assertNotNull( bankAccount );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccountWithBankBranch()
			throws Exception {
		try {
			utx.begin();
			User owner = userDAO.createUser( "last", "first", "middle", "nick", "password" );
			Bank bank = bankDAO.createBank( "bank name", "bank website" );
			BankAccount bankAccount = bankDAO.createBankAccount(
					200L,
					owner,
					"state",
					"city",
					"street",
					(short) 20,
					(short) 38,
					(short) 542,
					587512,
					null,
					bank );
			Assert.assertNotNull( bankAccount );
			Assert.assertNotNull( bankAccount.getBankBranch() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccountWithBankAndBankAccount()
			throws Exception {
		try {
			utx.begin();
			User owner = userDAO.createUser(
					"user last name",
					"user first name",
					"user middle name",
					"user nick name",
					"user password" );
			BankAccount bankAccount = bankDAO.createBankAccount(
					2000L,
					owner,
					"bank name",
					"bank website",
					"account name",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					158984,
					null );
			Assert.assertNotNull( bankAccount );
			Assert.assertNotNull( bankAccount.getBankBranch() );
			Assert.assertNotNull( bankAccount.getBank() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateRootOfficeType()
			throws Exception {
		try {
			utx.begin();
			GovernmentOfficeType officeType = documentsOfficesDAO.createRootOfficeType( "officeTypeName" );
			Assert.assertNotNull( officeType );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateOfficeType()
			throws Exception {
		try {
			utx.begin();
			GovernmentOfficeType parent = documentsOfficesDAO.createRootOfficeType( "parent" );
			GovernmentOfficeType child = documentsOfficesDAO.createOfficeType( "child", parent );
			Assert.assertNotNull( child );
			Assert.assertTrue( child.getParent() != null );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateOffice()
			throws Exception {
		try {
			utx.begin();
			GovernmentOfficeType officeType = documentsOfficesDAO.createRootOfficeType( "name" );
			GovernmentOffice office = documentsOfficesDAO.createOffice(
					"name",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					546123,
					null,
					officeType );
			Assert.assertNotNull( office );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocumentType()
			throws Exception {
		try {
			utx.begin();
			DocumentTemplate documentTemplate = documentsOfficesDAO.createDocumentTemplate(
					"name",
					"%s",
					"%d",
					"officeType" );
			Assert.assertNotNull( documentTemplate );
			Assert.assertNotNull( documentTemplate.getGovernmentOfficeType() );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocumentTypeWithOfficeType()
			throws Exception {
		try {
			utx.begin();
			DocumentTemplate documentTemplate = documentsOfficesDAO.createDocumentTemplate(
					"name",
					"%s",
					"%d",
					"officeTypeName",
					null );
			Assert.assertNotNull( documentTemplate );
			Assert.assertNotNull( documentTemplate.getGovernmentOfficeType() );
		}
		finally {
			utx.commit();
		}
	}
  */
}

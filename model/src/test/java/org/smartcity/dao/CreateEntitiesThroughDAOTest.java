package org.smartcity.dao;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;

import org.jboss.shrinkwrap.api.Archive;

import org.junit.Assert;
import org.junit.Test;

import org.smartcity.UtilTestClass;
import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.Document;
import org.smartcity.entity.DocumentTemplate;
import org.smartcity.entity.Email;
import org.smartcity.entity.GovernmentOffice;
import org.smartcity.entity.GovernmentOfficeType;
import org.smartcity.entity.User;

import javax.inject.Inject;

import javax.transaction.UserTransaction;
import java.util.Date;

public class CreateEntitiesThroughDAOTest
		extends AbstractDAOTest {

	@Inject
	private UserTransaction utx;

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
			User user = getUserDAO().createUser( "last", "first", "middle", "nick", "password" );
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
			User user = getUserDAO().createUser( "new last", "new first", "new middle", "new nick", "new password" );
			Email mainEmail = getUserDAO().createMainUserEmail( "user", user );
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
			User user = getUserDAO().createUser( "last", "first", "middle", "nick", "password" );
			Email email = getUserDAO().createUserEmail( "email", user );
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
			Email email = getUserDAO().createUserEmail(
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

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBank()
			throws Exception {
		try {
			utx.begin();
			Bank bank = getBankDAO().createBank( "bankName", "bankWebSite" );
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
			Bank bank = getBankDAO().createBank( "otherBankName", "otherBankWebSite" );
			BankBranch bankBranch = getBankDAO().createBankBranch(
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
			BankBranch bankBranch = getBankDAO().createBankBranch(
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
			Bank bank = getBankDAO().createBank( "otherBankName", "otherBankWebSite" );
			BankBranch bankBranch = getBankDAO().createBankBranch(
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
			User user = getUserDAO().createUser( "last", "first", "middle", "nick", "password" );
			BankAccount bankAccount = getBankDAO().createBankAccount( 1000L, user, bankBranch, bank );
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
			User owner = getUserDAO().createUser( "last", "first", "middle", "nick", "password" );
			Bank bank = getBankDAO().createBank( "bank name", "bank website" );
			BankAccount bankAccount = getBankDAO().createBankAccount(
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
			User owner = getUserDAO().createUser(
					"user last name",
					"user first name",
					"user middle name",
					"user nick name",
					"user password" );
			BankAccount bankAccount = getBankDAO().createBankAccount(
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
			GovernmentOfficeType officeType = getDocumentsOfficesDAO().createRootOfficeType( "officeTypeName" );
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
			GovernmentOfficeType parent = getDocumentsOfficesDAO().createRootOfficeType( "parent" );
			GovernmentOfficeType child = getDocumentsOfficesDAO().createOfficeType( "child", parent );
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
			GovernmentOfficeType officeType = getDocumentsOfficesDAO().createRootOfficeType( "name" );
			GovernmentOffice office = getDocumentsOfficesDAO().createOffice(
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
	public void testCreateDocumentTemplate()
			throws Exception {
		try {
			utx.begin();
			DocumentTemplate documentTemplate = getDocumentsOfficesDAO().createDocumentTemplate(
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
	public void testCreateDocumentTemplateWithOfficeType()
			throws Exception {
		try {
			utx.begin();
			DocumentTemplate documentTemplate = getDocumentsOfficesDAO().createDocumentTemplate(
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

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocumentTemplateWithRootOfficeType()
			throws Exception {
		try {
			utx.begin();
			GovernmentOfficeType parent = getDocumentsOfficesDAO().createRootOfficeType( "parent" );
			DocumentTemplate documentTemplate = getDocumentsOfficesDAO().createDocumentTemplate(
					"name",
					"%s",
					"%d",
					parent );
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
	public void testCreateDocument()
			throws Exception {
		try {
			utx.begin();
			GovernmentOfficeType officeType = getDocumentsOfficesDAO().createRootOfficeType( "name" );
			GovernmentOffice office = getDocumentsOfficesDAO().createOffice(
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
			DocumentTemplate documentTemplate = getDocumentsOfficesDAO().createDocumentTemplate(
					"name",
					"%s",
					"%d",
					officeType );
			User owner = getUserDAO().createUser(
					"user last name",
					"user first name",
					"user middle name",
					"user nick name",
					"user password" );
			Document document = getDocumentsOfficesDAO().createDocument(
					"passport",
					"MA",
					128554L,
					new Date(),
					owner,
					office,
					documentTemplate );
			Assert.assertNotNull( document );
			Assert.assertNotNull( document.getOwner() );
			Assert.assertNotNull( document.getTemplate() );
			Assert.assertNotNull( document.getOffice() );
			Assert.assertNotNull( document.getOffice().getOfficeType() );
		}
		finally {
			utx.commit();
		}

	}

}

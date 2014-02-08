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

import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import java.math.BigInteger;
import java.util.Date;

public class RemoveEntitiesThroughDAOTest
		extends AbstractDAOTest {

	@Inject
	private UserTransaction utx;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive createArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveUser()
			throws Exception {
		User user;
		try {
			utx.begin();
			user = getUserDAO().createUser( "name", "name", "name", "nick", "password" );
		}
		finally {
			utx.commit();
		}

		if( user == null ) {
			Assert.fail( "User shouldn't be NULL" );
		}

		BigInteger userID = user.getID();

		try {
			utx.begin();
			try {
				User foundUser = getUserDAO().findUser( userID );
				Assert.assertNotNull( foundUser );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created user is not found" );
			}
			getUserDAO().removeUser( user );
			getUserDAO().findUser( userID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveEmail()
			throws Exception {
		Email email;
		try {
			utx.begin();
			User owner = getUserDAO().createUser( "name", "name", "not name", "nick", "without password" );
			email = getUserDAO().createUserEmail( "address", owner );
		}
		finally {
			utx.commit();
		}

		if( email == null ) {
			Assert.fail( "Email shouldn't be NULL" );
		}

		BigInteger emailID = email.getID();

		try {
			utx.begin();
			try {
				Email foundEmail = getUserDAO().findEmail( emailID );
				Assert.assertNotNull( foundEmail );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created email is not found" );
			}
			getUserDAO().removeUserEmail( email );
			getUserDAO().findEmail( emailID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveBank()
			throws Exception {
		Bank bank;
		try {
			utx.begin();
			bank = getBankDAO().createBank( "bank", "site" );
		}
		finally {
			utx.commit();
		}

		if( bank == null ) {
			Assert.fail( "Bank shouldn't be NULL" );
		}

		BigInteger bankID = bank.getID();

		try {
			utx.begin();
			try {
				Bank foundBank = getBankDAO().findBank( bankID );
				Assert.assertNotNull( foundBank );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created bank is not found" );
			}
			getBankDAO().removeBank( bank );
			getBankDAO().findBank( bankID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveBankBranch()
			throws Exception {
		BankBranch bankBranch;
		try {
			utx.begin();
			Bank bank = getBankDAO().createBank( "some bank", "some web site" );
			bankBranch = getBankDAO().createBankBranch(
					bank,
					"name",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					264847,
					null );
		}
		finally {
			utx.commit();
		}

		if( bankBranch == null ) {
			Assert.fail( "Bank branch shouldn't be NULL" );
		}

		BigInteger bankBranchID = bankBranch.getID();

		try {
			utx.begin();
			try {
				BankBranch foundBankBranch = getBankDAO().findBankBranch( bankBranchID );
				Assert.assertNotNull( foundBankBranch );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created bank branch is not found");
			}
			getBankDAO().removeBankBranch( bankBranch );
			getBankDAO().findBank( bankBranchID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveBankAccount()
			throws Exception {
		BankAccount bankAccount;
		try {
			utx.begin();
			Bank bank = getBankDAO().createBank( "some bank", "some web site" );
			BankBranch bankBranch = getBankDAO().createBankBranch(
					bank,
					"name",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					264847,
					null );
			User owner = getUserDAO().createUser( "user", "not user", "another user", "nick user", "password" );
			bankAccount = getBankDAO().createBankAccount( 3004L, owner, bankBranch, bank );
		}
		finally {
			utx.commit();
		}

		if( bankAccount == null ) {
			Assert.fail( "Bank account shouldn't be NULL" );
		}

		BigInteger bankAccountID = bankAccount.getID();

		try {
			utx.begin();
			try {
				BankAccount foundBankAccount = getBankDAO().findBankAccount( bankAccount.getID() );
				Assert.assertNotNull( foundBankAccount );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created bank account is not found");
			}
			getBankDAO().removeBankAccount( bankAccount );
			getBankDAO().findBankAccount( bankAccountID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveOfficeType()
			throws Exception {
		GovernmentOfficeType officeType;
		try {
			utx.begin();
			officeType = getDocumentsOfficesDAO().createRootOfficeType( "name" );
		}
		finally {
			utx.commit();
		}

		if( officeType == null ) {
			Assert.fail( "Office type shouldn't be NULL" );
		}

		BigInteger officeTypeID = officeType.getID();

		try {
			utx.begin();
			try {
				GovernmentOfficeType foundOfficeType = getDocumentsOfficesDAO().findOfficeType( officeType.getID() );
				Assert.assertNotNull( foundOfficeType );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created office type is not found");
			}
			getDocumentsOfficesDAO().removeOfficeType( officeType );
			getDocumentsOfficesDAO().findOfficeType( officeTypeID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveOffice()
			throws Exception {
		GovernmentOffice office;
		try {
			utx.begin();
			GovernmentOfficeType officeType = getDocumentsOfficesDAO().createRootOfficeType( "name" );
			office = getDocumentsOfficesDAO().createOffice(
					"name",
					"state",
					"city",
					"street",
					(short) 50,
					(short) 38,
					(short) 542,
					5687541,
					null,
					officeType );
		}
		finally {
			utx.commit();
		}

		if( office == null ) {
			Assert.fail( "Office shouldn't be NULL" );
		}

		BigInteger officeID = office.getID();

		try {
			utx.begin();
			try {
				GovernmentOffice foundOffice = getDocumentsOfficesDAO().findOffice( office.getID() );
				Assert.assertNotNull( foundOffice );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created office is not found" );
			}
			getDocumentsOfficesDAO().removeOffice( office );
			getDocumentsOfficesDAO().findOfficeType( officeID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveDocumentTemplate()
			throws Exception {
		DocumentTemplate documentTemplate;
		try {
			utx.begin();
			documentTemplate = getDocumentsOfficesDAO().createDocumentTemplate( "name", "%s", "%d", "officeTypeName" );
		}
		finally {
			utx.commit();
		}

		if( documentTemplate == null ) {
			Assert.fail( "Document template shouldn't be NULL" );
		}

		BigInteger documentTemplateID = documentTemplate.getID();

		try {
			utx.begin();
			try {
				DocumentTemplate foundDocumentTemplate =
						getDocumentsOfficesDAO().findDocumentTemplate( documentTemplate.getID() );
				Assert.assertNotNull( foundDocumentTemplate );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created document template is not found");
			}
			getDocumentsOfficesDAO().removeDocumentTemplate( documentTemplate );
			getDocumentsOfficesDAO().findDocumentTemplate( documentTemplateID );
		}
		finally {
			utx.commit();
		}
	}

	@Test(
			expected = EntityNotFoundException.class
	)
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testRemoveDocument()
			throws Exception {
		Document document;
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
			document = getDocumentsOfficesDAO().createDocument(
					"passport",
					"MA",
					128554L,
					new Date(),
					owner,
					office,
					documentTemplate );
		}
		finally {
			utx.commit();
		}

		if( document == null ) {
			Assert.fail( "Document shouldn't be NULL" );
		}

		BigInteger documentID = document.getID();

		try {
			utx.begin();
			try {
				Document foundDocument = getDocumentsOfficesDAO().findDocument( document.getID() );
				Assert.assertNotNull( foundDocument );
			}
			catch( EntityNotFoundException e ) {
				Assert.fail( "Created document is not found");
			}
			getDocumentsOfficesDAO().removeDocument( document );
			getDocumentsOfficesDAO().findDocument( documentID );
		}
		finally {
			utx.commit();
		}
	}

}

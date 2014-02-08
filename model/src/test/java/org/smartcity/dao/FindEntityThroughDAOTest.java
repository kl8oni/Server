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

public class FindEntityThroughDAOTest
		extends AbstractDAOTest {

	@Inject
	private UserTransaction utx;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public Archive<?> createArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindUser()
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

		try {
			utx.begin();
			User foundUser = getUserDAO().findUser( user.getID() );
			Assert.assertNotNull( foundUser );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindEmail()
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

		try {
			utx.begin();
			Email foundEmail = getUserDAO().findEmail( email.getID() );
			Assert.assertNotNull( foundEmail );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindBank()
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

		try {
			utx.begin();
			Bank foundBank = getBankDAO().findBank( bank.getID() );
			Assert.assertNotNull( foundBank );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindBankBranch()
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

		try {
			utx.begin();
			BankBranch foundBankBranch = getBankDAO().findBankBranch( bankBranch.getID() );
			Assert.assertNotNull( foundBankBranch );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindBankAccount()
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

		try {
			utx.begin();
			BankAccount foundBankAccount = getBankDAO().findBankAccount( bankAccount.getID() );
			Assert.assertNotNull( foundBankAccount );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindOfficeType()
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

		try {
			utx.begin();
			GovernmentOfficeType foundOfficeType = getDocumentsOfficesDAO().findOfficeType( officeType.getID() );
			Assert.assertNotNull( foundOfficeType );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindOffice()
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

		try {
			utx.begin();
			GovernmentOffice foundOffice = getDocumentsOfficesDAO().findOffice( office.getID() );
			Assert.assertNotNull( foundOffice );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindDocumentTemplate()
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

		try {
			utx.begin();
			DocumentTemplate foundDocumentTemplate =
					getDocumentsOfficesDAO().findDocumentTemplate( documentTemplate.getID() );
			Assert.assertNotNull( foundDocumentTemplate );
		}
		finally {
			utx.commit();
		}
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testFindDocument()
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

		try {
			utx.begin();
			Document foundDocument = getDocumentsOfficesDAO().findDocument( document.getID() );
			Assert.assertNotNull( foundDocument );
		}
		finally {
			utx.commit();
		}
	}

}

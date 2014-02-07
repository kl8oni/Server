package org.smartcity.entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import javax.transaction.UserTransaction;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.smartcity.UtilTestClass;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(
		value = Arquillian.class
)
public class EntitiesCreatingTest {

	@PersistenceContext
	private EntityManager   em;
	@Inject
	private UserTransaction utx;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createDeploymentArchive() {
		return UtilTestClass.INSTANCE
				.createDeploymentArchive();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBank()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		Bank bank = new Bank( "Bank", "Website" );
		em.persist( bank );
		utx.commit();
		Assert.assertNotNull( bank.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankBranch()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		Bank bank = new Bank( "Bank", "Website" );
		TelephoneNumber telephoneNumber = new TelephoneNumber( (short) 10, (short) 10, 50, null );
		Address address = new Address( "State", "City", "Street", (short) 10, telephoneNumber );
		BankBranch bankBranch = new BankBranch( "Bank Branch", address, bank );
		bank.addBankBranch( bankBranch );
		em.persist( bankBranch );
		utx.commit();
		Assert.assertNotNull( bankBranch.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUser()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		em.persist( user );
		utx.commit();
		Assert.assertNotNull( user.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateMainEmail()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		Email email = new Email( "main-user-email@email.com", Boolean.TRUE, user );
		user.addUserEmail( email );
		em.persist( email );
		utx.commit();
		Assert.assertNotNull( email.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateNonMainEmail()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		Email email = new Email( "user-email@email.com", Boolean.FALSE, user );
		user.addUserEmail( email );
		em.persist( email );
		utx.commit();
		Assert.assertNotNull( email.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccount()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		Bank bank = new Bank( "Bank", "Website" );
		TelephoneNumber telephoneNumber = new TelephoneNumber( (short) 10, (short) 10, 50, null );
		Address address = new Address( "State", "City", "Street", (short) 10, telephoneNumber );
		BankBranch bankBranch = new BankBranch( "Bank Branch", address, bank );
		BankAccount bankAccount = new BankAccount( 1000L, user, bank, bankBranch );
		em.persist( bankAccount );
		utx.commit();
		Assert.assertNotNull( bankAccount.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateGovernmentOfficeType()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeType governmentOfficeType = new GovernmentOfficeType( "office type", null );
		em.persist( governmentOfficeType );
		utx.commit();
		Assert.assertNotNull( governmentOfficeType.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateGovernmentOffice()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeType governmentOfficeType = new GovernmentOfficeType( "office type", null );
		TelephoneNumber telephoneNumber = new TelephoneNumber( (short) 10, (short) 10, 50, null );
		Address address = new Address( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOffice governmentOffice = new GovernmentOffice(
				"government office", address,
				governmentOfficeType );
		governmentOfficeType.addOfficeThisType( governmentOffice );
		em.persist( governmentOffice );
		utx.commit();
		Assert.assertNotNull( governmentOffice.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocumentTemplate()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeType governmentOfficeType = new GovernmentOfficeType( "office type", null );
		DocumentTemplate documentTemplate = new DocumentTemplate(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		governmentOfficeType.addDocumentTemplate( documentTemplate );
		em.persist( documentTemplate );
		utx.commit();
		Assert.assertNotNull( documentTemplate.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateIdentifyDocument()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		GovernmentOfficeType governmentOfficeType = new GovernmentOfficeType( "office type", null );
		DocumentTemplate documentTemplate = new DocumentTemplate(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		TelephoneNumber telephoneNumber = new TelephoneNumber( (short) 10, (short) 10, 50, null );
		Address address = new Address( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOffice governmentOffice = new GovernmentOffice(
				"government office", address,
				governmentOfficeType );
		Document identifyDocument = new Document(
				"passport",
				"MA",
				456875L,
				new Date(),
				user,
				governmentOffice,
				documentTemplate,
				Boolean.TRUE );
		documentTemplate.addDocument( identifyDocument );
		governmentOffice.addReleasedDocument( identifyDocument );
		user.addDocument( identifyDocument );
		em.persist( identifyDocument );
		utx.commit();
		Assert.assertNotNull( user.getID() );
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocument()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		User user = new User( "lastName", "firstName", "middleName", "nickName", "password" );
		GovernmentOfficeType governmentOfficeType = new GovernmentOfficeType( "office type", null );
		DocumentTemplate documentTemplate = new DocumentTemplate(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		TelephoneNumber telephoneNumber = new TelephoneNumber( (short) 10, (short) 10, 50, null );
		Address address = new Address( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOffice governmentOffice = new GovernmentOffice(
				"government office", address,
				governmentOfficeType );
		Document document = new Document(
				"passport",
				"MA",
				456875L,
				new Date(),
				user,
				governmentOffice,
				documentTemplate,
				Boolean.FALSE );
		documentTemplate.addDocument( document );
		governmentOffice.addReleasedDocument( document );
		user.addDocument( document );
		em.persist( document );
		utx.commit();
		Assert.assertNotNull( document.getID() );
	}

}

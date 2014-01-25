package org.smartcity.entity.jpa;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

import org.smartcity.UtilTestClass;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;

@RunWith(
		value = Arquillian.class
)
public class EntitiesCreatingTest {

	@PersistenceContext
	private EntityManager em;
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
		BankEntity bank = new BankEntity( "Bank", "Website" );
		em.persist( bank );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankBranch()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		BankEntity bank = new BankEntity( "Bank", "Website" );
		TelephoneNumberEmbedded telephoneNumber = new TelephoneNumberEmbedded(
				(short) 10,
				(short) 10,
				(short) 50,
				null );
		AddressEmbedded address = new AddressEmbedded( "State", "City", "Street", (short) 10, telephoneNumber );
		BankBranchEntity bankBranch = new BankBranchEntity( "Bank Branch", address, bank );
		bank.addBankBranch( bankBranch );
		em.persist( bankBranch );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateUser()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		em.persist( user );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateMainEmail()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		EmailEntity email = new EmailEntity( "main-user-email@email.com", Boolean.TRUE, user );
		user.addUserEmail( email );
		em.persist( email );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateNonMainEmail()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		EmailEntity email = new EmailEntity( "user-email@email.com", Boolean.FALSE, user );
		user.addUserEmail( email );
		em.persist( email );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccount()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		BankEntity bank = new BankEntity( "Bank", "Website" );
		TelephoneNumberEmbedded telephoneNumber = new TelephoneNumberEmbedded(
				(short) 10,
				(short) 10,
				(short) 50,
				null );
		AddressEmbedded address = new AddressEmbedded( "State", "City", "Street", (short) 10, telephoneNumber );
		BankBranchEntity bankBranch = new BankBranchEntity( "Bank Branch", address, bank );
		BankAccountEntity bankAccount = new BankAccountEntity( 1000L, user, bank, bankBranch );
		em.persist( bankAccount );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateGovernmentOfficeType()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeTypeEntity governmentOfficeType = new GovernmentOfficeTypeEntity( "office type", null );
		em.persist( governmentOfficeType );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateGovernmentOffice()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeTypeEntity governmentOfficeType = new GovernmentOfficeTypeEntity( "office type", null );
		TelephoneNumberEmbedded telephoneNumber = new TelephoneNumberEmbedded(
				(short) 10,
				(short) 10,
				(short) 50,
				null );
		AddressEmbedded address = new AddressEmbedded( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOfficeEntity governmentOffice = new GovernmentOfficeEntity(
				"government office", address,
				governmentOfficeType );
		governmentOfficeType.addOfficeThisType( governmentOffice );
		em.persist( governmentOffice );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocumentTemplate()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		GovernmentOfficeTypeEntity governmentOfficeType = new GovernmentOfficeTypeEntity( "office type", null );
		DocumentTemplateEntity documentTemplate = new DocumentTemplateEntity(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		governmentOfficeType.addDocumentTemplate( documentTemplate );
		em.persist( documentTemplate );
		utx.commit();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateIdentifyDocument()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		GovernmentOfficeTypeEntity governmentOfficeType = new GovernmentOfficeTypeEntity( "office type", null );
		DocumentTemplateEntity documentTemplate = new DocumentTemplateEntity(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		TelephoneNumberEmbedded telephoneNumber = new TelephoneNumberEmbedded(
				(short) 10,
				(short) 10,
				(short) 50,
				null );
		AddressEmbedded address = new AddressEmbedded( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOfficeEntity governmentOffice = new GovernmentOfficeEntity(
				"government office", address,
				governmentOfficeType );
		DocumentEntity identifyDocument = new DocumentEntity(
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
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateDocument()
			throws Exception {
		utx.begin();
		em.joinTransaction();
		UserEntity user = new UserEntity( "lastName", "firstName", "middleName", "nickName", "password" );
		GovernmentOfficeTypeEntity governmentOfficeType = new GovernmentOfficeTypeEntity( "office type", null );
		DocumentTemplateEntity documentTemplate = new DocumentTemplateEntity(
				"Passport Type",
				"cc",
				"999999",
				governmentOfficeType );
		TelephoneNumberEmbedded telephoneNumber = new TelephoneNumberEmbedded(
				(short) 10,
				(short) 10,
				(short) 50,
				null );
		AddressEmbedded address = new AddressEmbedded( "State", "City", "Street", (short) 10, telephoneNumber );
		GovernmentOfficeEntity governmentOffice = new GovernmentOfficeEntity(
				"government office", address,
				governmentOfficeType );
		DocumentEntity document = new DocumentEntity(
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
	}

}

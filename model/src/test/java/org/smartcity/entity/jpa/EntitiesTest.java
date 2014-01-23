package org.smartcity.entity.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.OperateOnDeployment;
import org.jboss.arquillian.junit.Arquillian;

import org.jboss.shrinkwrap.api.Archive;

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

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(
		value = Arquillian.class
)
public class EntitiesTest {

	private Address              address;
	private Bank                 bank;
	private BankAccount          bankAccount;
	private BankBranch           bankBranch;
	private Document             document;
	private DocumentTemplate     documentTemplate;
	private Email                email;
	private GovernmentOffice     governmentOffice;
	private GovernmentOfficeType governmentOfficeType;
	private TelephoneNumber      telephoneNumber;
	private User                 user;
	@PersistenceContext
	private EntityManager        em;

	@Deployment(
			name = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public static Archive<?> createDeploymentArchive() {
		return UtilTestClass.INSTANCE.createDeploymentArchive();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBank() {
		bank = new BankEntity( "Bank", "Website" );
		em.persist( bank );
		em.flush();
	}

	@Test
	@OperateOnDeployment(
			value = UtilTestClass.PRODUCTION_DEPLOYMENT
	)
	public void testCreateBankAccount() {
		telephoneNumber = new TelephoneNumberEmbedded( (short) 10, (short) 10, (short) 50, null );
		address = new AddressEmbedded( "State",
									   "City",
									   "Street",
									   (short) 10,
									   (TelephoneNumberEmbedded) telephoneNumber );
		bankBranch = new BankBranchEntity( "Bank Branch", (AddressEmbedded) address, (BankEntity) bank );
		em.persist( bankBranch );
		em.flush();
	}

}
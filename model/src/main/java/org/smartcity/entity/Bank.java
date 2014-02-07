package org.smartcity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.io.Serializable;
import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 */
@Entity
@Table(
		name = Bank.TABLE_NAME
)
public class Bank
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( Bank.class );

	public static final String TABLE_NAME          = "BANKS";
	public static final String GENERATOR_NAME      = "BANK_ID_GENERATOR";
	public static final String SEQUENCE_NAME       = "BANK_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME      = "BANK_ID";
	public static final String NAME_COLUMN_NAME    = "NAME";
	public static final String WEBSITE_COLUMN_NAME = "WEBSITE";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD            = "ID";
	public static final String NAME_FIELD          = "name";
	public static final String WEBSITE_FIELD       = "website";
	public static final String BANK_BRANCHES_FIELD = "bankBranches";
	public static final String BANK_ACCOUNTS_FIELD = "bankAccounts";

	@Id
	@GeneratedValue(
			generator = Bank.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = Bank.GENERATOR_NAME,
			sequenceName = Bank.SEQUENCE_NAME,
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = Bank.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger       ID;
	@Column(
			name = Bank.NAME_COLUMN_NAME,
			nullable = false
	)
	private String           name;
	@Column(
			name = Bank.WEBSITE_COLUMN_NAME
	)
	private String           website;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = BankBranch.BANK_FIELD
	)
	private Set<BankBranch>  bankBranches;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = BankAccount.BANK_FIELD
	)
	private Set<BankAccount> bankAccounts;

	public Bank() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public Bank( String name, String website ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setWebsite( website )
				.setBankBranches( new HashSet<BankBranch>() )
				.setBankAccounts( new HashSet<BankAccount>() );
	}

	public BigInteger getID() {
		LOG.debug( "ID = " + ID );
		return ID;
	}

	public String getName() {
		return name;
	}

	public Bank setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public String getWebsite() {
		return website;
	}

	public Bank setWebsite( String website ) {
		LOG.debug( "Website is " + website );
		this.website = website;
		return this;
	}

	public Set<BankBranch> getBankBranches() {
		return bankBranches;
	}

	public Bank setBankBranches( Set<BankBranch> bankBranches ) {
		this.bankBranches = bankBranches;
		return this;
	}

	public Bank addBankBranch( BankBranch bankBranch ) {
		bankBranches.add( bankBranch );
		return this;
	}

	public Bank addBankBranches( Collection<BankBranch> bankBranches ) {
		this.bankBranches.addAll( bankBranches );
		return this;
	}

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public Set<BankAccount> getBankAccountsByBankBranch( BankBranch bankBranch ) {
		return bankBranch.getBankAccounts();
	}

	public Bank setBankAccounts( Set<BankAccount> bankAccounts ) {
		this.bankAccounts = bankAccounts;
		return this;
	}

	public Bank addBankBranchBankAccounts( BankBranch bankBranch ) {
		bankAccounts.addAll( bankBranch.getBankAccounts() );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			Bank bank = (Bank) object;
			return getName().equals( bank.getName() )
				   && getWebsite().equals( bank.getWebsite() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getWebsite().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Bank with Name = " )
				.append( getName() )
				.append( " ]" );
		return sb.toString();
	}

}

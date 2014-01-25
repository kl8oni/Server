package org.smartcity.entity.jpa;

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

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Bank;

@Entity
@Table(
		name = Bank.TABLE_NAME
)
public class BankEntity
		implements Bank<
		BankEntity,
		BankBranchEntity,
		BankAccountEntity> {

	private static final Log LOG = LogFactory.getLog( BankEntity.class );

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
			initialValue = 1
	)
	@Column(
			name = Bank.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger             ID;
	@Column(
			name = Bank.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                 name;
	@Column(
			name = Bank.WEBSITE_COLUMN_NAME
	)
	private String                 website;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = BankBranchEntity.BANK_FIELD
	)
	private Set<BankBranchEntity>  bankBranches;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = BankAccountEntity.BANK_FIELD
	)
	private Set<BankAccountEntity> bankAccounts;

	public BankEntity() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public BankEntity( String name, String website ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setName( name )
				.setWebsite( website )
				.setBankBranches( new HashSet<BankBranchEntity>() )
				.setBankAccounts( new HashSet<BankAccountEntity>() );
	}

	@Override
	public BigInteger getID() {
		LOG.debug( "ID = " + ID );
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BankEntity setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	@Override
	public String getWebsite() {
		return website;
	}

	@Override
	public BankEntity setWebsite( String website ) {
		LOG.debug( "Website is " + website );
		this.website = website;
		return this;
	}

	@Override
	public Set<BankBranchEntity> getBankBranches() {
		return bankBranches;
	}

	@Override
	public BankEntity setBankBranches( Set<BankBranchEntity> bankBranches ) {
		this.bankBranches = bankBranches;
		return this;
	}

	@Override
	public BankEntity addBankBranch( BankBranchEntity bankBranch ) {
		bankBranches.add( bankBranch );
		return this;
	}

	@Override
	public BankEntity addBankBranches( Collection<BankBranchEntity> bankBranches ) {
		this.bankBranches.addAll( bankBranches );
		return this;
	}

	@Override
	public Set<BankAccountEntity> getBankAccounts() {
		return bankAccounts;
	}

	@Override
	public Set<BankAccountEntity> getBankAccountsByBankBranch( BankBranchEntity bankBranch ) {
		return bankBranch.getBankAccounts();
	}

	@Override
	public BankEntity setBankAccounts( Set<BankAccountEntity> bankAccounts ) {
		this.bankAccounts = bankAccounts;
		return this;
	}

	@Override
	public BankEntity addBankBranchBankAccounts( BankBranchEntity bankBranch ) {
		bankAccounts.addAll( bankBranch.getBankAccounts() );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			BankEntity bank = (BankEntity) object;
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

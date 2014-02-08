package org.smartcity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
 * todo provide access to bank account through get( index ) method
 */
@Entity
@Table(
		name = BankBranch.TABLE_NAME
)
public class BankBranch
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( BankBranch.class );

	public static final String TABLE_NAME          = "BANK_BRANCHES";
	public static final String GENERATOR_NAME      = "BANK_BRANCH_ID_GENERATOR";
	public static final String SEQUENCE_NAME       = "BANK_BRANCH_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME      = "BANK_BRANCH_ID";
	public static final String NAME_COLUMN_NAME    = "NAME";
	public static final String BANK_ID_COLUMN_NAME = "BANK_ID";

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD            = "ID";
	public static final String NAME_FIELD          = "name";
	public static final String BANK_FIELD          = "bank";
	public static final String ADDRESS_FIELD       = "address";
	public static final String BANK_ACCOUNTS_FIELD = "bankAccounts";

	@Id
	@GeneratedValue(
			generator = BankBranch.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			sequenceName = BankBranch.SEQUENCE_NAME,
			name = BankBranch.GENERATOR_NAME,
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = BankBranch.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger       ID;
	@Column(
			name = BankBranch.NAME_COLUMN_NAME,
			nullable = false
	)
	private String           name;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankBranch.BANK_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Bank.ID_COLUMN_NAME
	)
	private Bank             bank;
	@Embedded
	private Address          address;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			mappedBy = BankAccount.BANK_BRANCH_FIELD
	)
	private Set<BankAccount> bankAccounts;

	public BankBranch() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public BankBranch(
			String name,
			Address address,
			Bank bank ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setBank( bank )
				.setName( name )
				.setAddress( address )
				.setBankAccounts( new HashSet<BankAccount>() );
	}

	public BigInteger getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public BankBranch setName( String name ) {
		LOG.debug( "Name is " + name );
		this.name = name;
		return this;
	}

	public Address getAddress() {
		return address;
	}

	public BankBranch setAddress( Address address ) {
		LOG.debug( "Address is " + address );
		this.address = address;
		return this;
	}

	public Bank getBank() {
		return bank;
	}

	public BankBranch setBank( Bank bank ) {
		LOG.debug( "Bank is " + bank );
		this.bank = bank;
		return this;
	}

	public Set<BankAccount> getBankAccounts() {
		return bankAccounts;
	}

	public BankBranch setBankAccounts( Set<BankAccount> bankAccounts ) {
		this.bankAccounts = bankAccounts;
		return this;
	}

	public BankBranch addBankAccount( BankAccount bankAccount ) {
		bankAccounts.add( bankAccount );
		return this;
	}

	public BankBranch addBankAccounts( Collection<BankAccount> bankAccounts ) {
		this.bankAccounts.addAll( bankAccounts );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			BankBranch bankBranch = (BankBranch) object;
			return bankBranch.getName().equals( getName() )
				   && bankBranch.getAddress().equals( getAddress() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getName().hashCode() + getAddress().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ BankBranch with ID = " )
				.append( getID() )
				.append( " Name = " )
				.append( getName() );
		return sb.toString();
	}

}

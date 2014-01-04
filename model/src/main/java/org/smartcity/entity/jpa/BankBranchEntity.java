package org.smartcity.entity.jpa;

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

import java.math.BigInteger;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Bank;
import org.smartcity.entity.BankBranch;

@Entity
@Table(
		name = BankBranch.TABLE_NAME
)
public class BankBranchEntity
		implements	BankBranch<BankBranchEntity, BankEntity, AddressEmbedded, BankAccountEntity> {

	private static final Log LOG = LogFactory.getLog( BankBranchEntity.class );

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
			initialValue = 1
	)
	@Column(
			name = BankBranch.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger             ID;
	@Column(
			name = BankBranch.NAME_COLUMN_NAME,
			nullable = false
	)
	private String                 name;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankBranch.BANK_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Bank.ID_COLUMN_NAME/*,
			insertable = false,
			updatable = false    */
	)
	private BankEntity             bank;
	@Embedded
	private AddressEmbedded        address;
	@OneToMany(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY,
			targetEntity = BankAccountEntity.class,
			mappedBy = BankAccountEntity.BANK_BRANCH_FIELD
	)
	private Set<BankAccountEntity> bankAccounts;

	public BankBranchEntity() {
	}

	public BankBranchEntity(
			String name,
			AddressEmbedded address,
			BankEntity bank ) {
		setBank( bank )
				.setName( name )
				.setAddress( address )
				.setBankAccounts( new HashSet<BankAccountEntity>() );
	}

	@Override
	public BigInteger getID() {
		return ID;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public BankBranchEntity setName( String name ) {
		this.name = name;
		return this;
	}

	@Override
	public AddressEmbedded getAddress() {
		return address;
	}

	@Override
	public BankBranchEntity setAddress( AddressEmbedded address ) {
		this.address = address;
		return this;
	}

	@Override
	public BankEntity getBank() {
		return bank;
	}

	@Override
	public BankBranchEntity setBank( BankEntity bank ) {
		this.bank = bank;
		return this;
	}

	@Override
	public Set<BankAccountEntity> getBankAccounts() {
		return bankAccounts;
	}

	@Override
	public BankBranchEntity setBankAccounts( Set<BankAccountEntity> bankAccounts ) {
		this.bankAccounts = bankAccounts;
		return this;
	}

	@Override
	public BankBranchEntity addBankAccount( BankAccountEntity bankAccount ) {
		bankAccounts.add( bankAccount );
		return this;
	}

	@Override
	public BankBranchEntity addBankAccounts( Collection<BankAccountEntity> bankAccounts ) {
		this.bankAccounts.addAll( bankAccounts );
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			BankBranchEntity bankBranch = (BankBranchEntity) object;
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
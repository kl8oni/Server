package org.smartcity.entity.jpa;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.smartcity.entity.Bank;
import org.smartcity.entity.BankAccount;
import org.smartcity.entity.BankBranch;
import org.smartcity.entity.User;

@Entity
@Table(
		name = BankAccount.TABLE_NAME
)
public class BankAccountEntity
		implements	BankAccount<BankAccountEntity, UserEntity, BankEntity, BankBranchEntity> {

	private static final Log LOG = LogFactory.getLog( BankAccountEntity.class );

	/*
	 * Constants for fields embeddable class
	 */
	public static final String ID_FIELD                  = "ID";
	public static final String BANK_ACCOUNT_NUMBER_FIELD = "bankAccountNumber";
	public static final String OWNER_FIELD               = "owner";
	public static final String BANK_FIELD                = "bank";
	public static final String BANK_BRANCH_FIELD         = "bankBranch";

	@Id
	@GeneratedValue(
			generator = BankAccount.GENERATOR_NAME,
			strategy = GenerationType.SEQUENCE
	)
	@SequenceGenerator(
			name = BankAccount.GENERATOR_NAME,
			sequenceName = BankAccount.SEQUENCE_NAME,
			initialValue = 1
	)
	@Column(
			name = BankAccount.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger       ID;
	@Column(
			name = BankAccount.ACCOUNT_NUMBER_COLUMN_NAME,
			nullable = false,
			precision = 16,
			scale = 0
	)
	private long             bankAccountNumber;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.USER_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = User.ID_COLUMN_NAME
	)
	private UserEntity       owner;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.BANK_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Bank.ID_COLUMN_NAME
	)
	private BankEntity       bank;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.BANK_BRANCH_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = BankBranch.ID_COLUMN_NAME
	)
	private BankBranchEntity bankBranch;


	public BankAccountEntity() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public BankAccountEntity(
			long bankAccountNumber,
			UserEntity owner,
			BankEntity bank,
			BankBranchEntity bankBranch ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setBankAccountNumber( bankAccountNumber )
				.setBankAccountOwner( owner )
				.setBank( bank )
				.setBankBranch( bankBranch );
	}

	@Override
	public BigInteger getID() {
		return ID;
	}

	@Override
	public long getBankAccountNumber() {
		return bankAccountNumber;
	}

	@Override
	public BankAccountEntity setBankAccountNumber( long bankAccountNumber ) {
		LOG.debug( "Bank account number is " + bankAccountNumber );
		this.bankAccountNumber = bankAccountNumber;
		return this;
	}

	@Override
	public UserEntity getBankAccountOwner() {
		return owner;
	}

	@Override
	public BankAccountEntity setBankAccountOwner( UserEntity owner ) {
		LOG.debug( "Bank account owner is " + owner );
		this.owner = owner;
		return this;
	}

	@Override
	public BankEntity getBank() {
		return bank;
	}

	@Override
	public BankAccountEntity setBank( BankEntity bank ) {
		LOG.debug( "Bank is " + bank );
		this.bank = bank;
		return this;
	}

	@Override
	public BankBranchEntity getBankBranch() {
		return bankBranch;
	}

	@Override
	public BankAccountEntity setBankBranch( BankBranchEntity bankBranch ) {
		LOG.debug( "Bank branch is " + bankBranch );
		this.bankBranch = bankBranch;
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if ( object == this ) {
			return true;
		}
		if ( object != null
			 && object.getClass().equals( getClass() ) ) {
			BankAccountEntity bankAccount = (BankAccountEntity) object;
			return bankAccount.getBankAccountNumber() == getBankAccountNumber()
				   && bankAccount.getBank().equals( getBank() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (int) getBankAccountNumber() + getBank().hashCode();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append( "[ Bank Account with ID = " )
				.append( getID() )
				.append( " Bank Account Number = " )
				.append( getBankAccountNumber() );
		return sb.toString();
	}

}

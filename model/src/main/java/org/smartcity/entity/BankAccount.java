package org.smartcity.entity;

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

import java.io.Serializable;
import java.math.BigInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * todo javadoc
 */
@Entity
@Table(
		name = BankAccount.TABLE_NAME
)
public class BankAccount
		implements Serializable {

	private static final Log LOG = LogFactory.getLog( BankAccount.class );

	public static final String TABLE_NAME                 = "BANK_ACCOUNTS";
	public static final String GENERATOR_NAME             = "BANK_ACCOUNT_ID_GENERATOR";
	public static final String SEQUENCE_NAME              = "BANK_ACCOUNT_ID_SEQUENCE";
	public static final String ID_COLUMN_NAME             = "BANK_ACCOUNT_ID";
	public static final String ACCOUNT_NUMBER_COLUMN_NAME = "ACCOUNT_NUMBER";
	public static final String USER_ID_COLUMN_NAME        = "USER_ID";
	public static final String BANK_ID_COLUMN_NAME        = "BANK_ID";
	public static final String BANK_BRANCH_ID_COLUMN_NAME = "BANK_BRANCH_ID";

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
			initialValue = 1,
			allocationSize = 1
	)
	@Column(
			name = BankAccount.ID_COLUMN_NAME,
			nullable = false,
			precision = 20,
			scale = 0
	)
	private BigInteger ID;
	@Column(
			name = BankAccount.ACCOUNT_NUMBER_COLUMN_NAME,
			nullable = false,
			precision = 16,
			scale = 0
	)
	private Long       bankAccountNumber;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.USER_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = User.ID_COLUMN_NAME
	)
	private User       owner;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.BANK_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = Bank.ID_COLUMN_NAME
	)
	private Bank       bank;
	@ManyToOne(
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY
	)
	@JoinColumn(
			name = BankAccount.BANK_BRANCH_ID_COLUMN_NAME,
			nullable = false,
			referencedColumnName = BankBranch.ID_COLUMN_NAME
	)
	private BankBranch bankBranch;


	public BankAccount() {
		LOG.debug( "Empty constructor is invoked" );
	}

	public BankAccount(
			Long bankAccountNumber,
			User owner,
			Bank bank,
			BankBranch bankBranch ) {
		LOG.debug( "Constructor with parameters is invoked" );
		setBankAccountNumber( bankAccountNumber ).setBankAccountOwner( owner )
				.setBank( bank )
				.setBankBranch( bankBranch );
	}

	public BigInteger getID() {
		return ID;
	}

	public Long getBankAccountNumber() {
		return bankAccountNumber;
	}

	public BankAccount setBankAccountNumber( Long bankAccountNumber ) {
		LOG.debug( "Bank account number is " + bankAccountNumber );
		this.bankAccountNumber = bankAccountNumber;
		return this;
	}

	public User getBankAccountOwner() {
		return owner;
	}

	public BankAccount setBankAccountOwner( User owner ) {
		LOG.debug( "Bank account owner is " + owner );
		this.owner = owner;
		return this;
	}

	public Bank getBank() {
		return bank;
	}

	public BankAccount setBank( Bank bank ) {
		LOG.debug( "Bank is " + bank );
		this.bank = bank;
		return this;
	}

	public BankBranch getBankBranch() {
		return bankBranch;
	}

	public BankAccount setBankBranch( BankBranch bankBranch ) {
		LOG.debug( "Bank branch is " + bankBranch );
		this.bankBranch = bankBranch;
		return this;
	}

	@Override
	public boolean equals( Object object ) {
		if( object == this ) {
			return true;
		}
		if( object != null && object.getClass().equals( getClass() ) ) {
			BankAccount bankAccount = (BankAccount) object;
			return bankAccount.getBankAccountNumber().equals( getBankAccountNumber() )
				   && bankAccount.getBank().equals( getBank() );
		}
		return false;
	}

	@Override
	public int hashCode() {
		return getBankAccountNumber().hashCode() + getBank().hashCode();
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

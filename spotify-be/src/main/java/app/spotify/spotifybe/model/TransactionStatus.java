package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the transaction_status database table.
 * 
 */
@Entity
@Table(name="transaction_status")
@NamedQuery(name="TransactionStatus.findAll", query="SELECT t FROM TransactionStatus t")
public class TransactionStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String description;

	//bi-directional many-to-one association to Transaction
	@OneToMany(mappedBy="transactionStatus")
	private List<Transaction> transactions;

	public TransactionStatus() {
	}

	public TransactionStatus(int id, String description, List<Transaction> transactions) {
		super();
		this.id = id;
		this.description = description;
		this.transactions = transactions;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Transaction> getTransactions() {
		return this.transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Transaction addTransaction(Transaction transaction) {
		getTransactions().add(transaction);
		transaction.setTransactionStatus(this);

		return transaction;
	}

	public Transaction removeTransaction(Transaction transaction) {
		getTransactions().remove(transaction);
		transaction.setTransactionStatus(null);

		return transaction;
	}

	@Override
	public String toString() {
		return "TransactionStatus [id=" + id + ", description=" + description + ", transactions=" + transactions + "]";
	}

}

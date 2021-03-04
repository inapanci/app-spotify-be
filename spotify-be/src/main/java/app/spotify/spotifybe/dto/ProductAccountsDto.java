package app.spotify.spotifybe.dto;

import app.spotify.spotifybe.model.Product;

public class ProductAccountsDto {
	
	private Product product;
	private int nrOfAccounts;
	
	public ProductAccountsDto() {
		super();
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNrOfAccounts() {
		return nrOfAccounts;
	}
	public void setNrOfAccounts(int nrOfAccounts) {
		this.nrOfAccounts = nrOfAccounts;
	}

}

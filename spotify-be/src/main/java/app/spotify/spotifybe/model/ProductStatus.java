package app.spotify.spotifybe.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the product_status database table.
 * 
 */
@Entity
@Table(name="product_status")
@NamedQuery(name="ProductStatus.findAll", query="SELECT p FROM ProductStatus p")
public class ProductStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="description", length=100)
	private String description;

	//bi-directional many-to-one association to Product
	@OneToMany(mappedBy="productStatus")
	private List<Product> products;

	public ProductStatus() {
	}

	public ProductStatus(int id, String description, List<Product> products) {
		super();
		this.id = id;
		this.description = description;
		this.products = products;
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

	public List<Product> getProducts() {
		return this.products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Product addProduct(Product product) {
		getProducts().add(product);
		product.setProductStatus(this);

		return product;
	}

	public Product removeProduct(Product product) {
		getProducts().remove(product);
		product.setProductStatus(null);

		return product;
	}

	@Override
	public String toString() {
		return "ProductStatus [id=" + id + ", description=" + description + ", products=" + products + "]";
	}

}

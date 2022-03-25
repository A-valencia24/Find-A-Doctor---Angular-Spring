package com.training.pms.service;

import java.util.List;

import com.training.pms.model.Product;

public interface ProductService {

	public String addProduct(Product product);
	public String updateProduct(int productId, Product product);
	public String deleteProduct(int productId);
	public List<Product> getProducts();
	public Product getProduct(int productId);
	public boolean isProductExists(int productId);
	public String deleteProduct();
	

	public List<Product> getProductByName(String productName);
	public List<Product> getProductsByPriceRange(int lowerPrice, int upperPrice);
	public int getProductQuanitiyOnHand(int productId);
}

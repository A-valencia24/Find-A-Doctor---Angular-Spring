package com.training.pms.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.dao.ProductDAO;
import com.training.pms.model.Product;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	ProductDAO productDAO;

	@Override
	public String addProduct(Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) {
			return "Product could not be saved since price or quantity on hand was less than 0";
		} else {
			productDAO.save(product);
			return "Product saved successfully";
		}
	}

	@Override
	public String updateProduct(int productId, Product product) {
		if (product.getPrice() < 0 || product.getQuantityOnHand() < 0) {
			return "Product (id:"+productId+") could not be updated since price or quantity on hand was less than 0";
		} else {
			productDAO.save(product);
			return "Product (id:"+productId+") updated successfully";
		}
	}

	@Override
	public String deleteProduct(int productId) {
		productDAO.deleteById(productId);
		return "Product (id:"+productId+") deleted successfully";
	}

	@Override
	public List<Product> getProducts() {
		return (List<Product>) productDAO.findAll();
	}

	@Override
	public Product getProduct(int productId) {
		Optional<Product> product = productDAO.findById(productId);
		return product.get();
	}

	@Override
	public boolean isProductExists(int productId) {
		return productDAO.existsById(productId);
	}

	@Override
	public String deleteProduct() {
		productDAO.deleteAll();
		return "All products deleted successfully";
	}

	@Override
	public List<Product> getProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductsByPriceRange(int lowerPrice, int upperPrice) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getProductQuanitiyOnHand(int productId) {
		// TODO Auto-generated method stub
		return 0;
	}

}

package com.training.pms.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.dao.ProductDAO;
import com.training.pms.model.Product;
import com.training.pms.service.ProductService;
import com.training.pms.service.ProductServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	ProductService productService = new ProductServiceImpl();

	@GetMapping
	public ResponseEntity<List<Product>> getProducts() {
		List<Product> result = productService.getProducts();
//		ResponseEntity<List<Product>> responseEntity = new ResponseEntity<List<Product>>(result,((result.size() == 0)?HttpStatus.NO_CONTENT:HttpStatus.OK));
		ResponseEntity<List<Product>> responseEntity = null;
		if (result.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(result,HttpStatus.NO_CONTENT);
		} else {
			responseEntity = new ResponseEntity<List<Product>>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@PostMapping
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.isProductExists(product.getProductId())) {
			result = "Product (id:"+product.getProductId()+") already exists";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = productService.addProduct(product);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.CREATED);
		}
		return responseEntity;
	}
	
	@PutMapping("{productId}")
	public ResponseEntity<String> updateProduct(@PathVariable("productId")int productId, @RequestBody Product product) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.isProductExists(product.getProductId())) {
			result = productService.updateProduct(productId, product);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Product (id:"+product.getProductId()+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_MODIFIED);
		}
		return responseEntity;
	}
	
	@GetMapping("{productId}")
	public ResponseEntity<Product> getByProductId(@PathVariable("productId")int productId) {
		ResponseEntity<Product> responseEntity = null;
		Product product = new Product();
		if (productService.isProductExists(productId)) {
			product = productService.getProduct(productId);
			responseEntity = new ResponseEntity<Product>(product,HttpStatus.OK);
		} else {
			responseEntity = new ResponseEntity<Product>(product,HttpStatus.NO_CONTENT);
		}
		return responseEntity;
	}
	
	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteByProductId(@PathVariable("productId")int productId) {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.isProductExists(productId)) {
			result = productService.deleteProduct(productId);
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		} else {
			result = "Product (id:"+productId+") does not exist";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		}
		return responseEntity;
	}
	
	@DeleteMapping("deleteAll")
	public ResponseEntity<String> deleteAll() {
		ResponseEntity<String> responseEntity = null;
		String result = null;
		if (productService.getProducts().size() == 0) {
			result = "Table empty, no products to delete";
			responseEntity = new ResponseEntity<String>(result,HttpStatus.NOT_FOUND);
		} else {
			result = productService.deleteProduct();
			responseEntity = new ResponseEntity<String>(result,HttpStatus.OK);
		}
		return responseEntity;
	}
	

	
//	@GetMapping("searchByProductName/{productName}") //localhost:5050/product/searchByProductName/Lakme
//	public String getProductByName(@PathVariable("productName")String productName) {
//		return "Getting one product by name: "+productName;
//	}
//	
//	@GetMapping("filterByProductPrice/{lowerPrice}/{upperPrice}") //localhost:5050/product/filterByProductPrice/250/300
//	public String filterProductByPrice(@PathVariable("lowerPrice") int lowerPrice, @PathVariable("upperPrice") int upperPrice) {
//		if (lowerPrice > upperPrice) {
//			return "First number("+lowerPrice+") cannot be larger than second number("+upperPrice+")";
//		} else {
//			return "Getting products in the range: "+lowerPrice+"-"+upperPrice;
//		}
//	}
	

	
}

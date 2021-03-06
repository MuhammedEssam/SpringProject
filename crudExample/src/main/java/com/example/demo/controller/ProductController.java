package com.example.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Product;
import com.example.demo.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService product;

	@GetMapping("/products")
	public List<Product> getAll() {
		System.out.println(product.findAll());
		return product.findAll();
	}

	@PostMapping("/product")
	public Product addProduct(@RequestBody Product theproduct) {
		System.out.println(theproduct.getName());
		System.out.println(theproduct.getPrice());
		return product.saveProduct(theproduct);
	}

	@GetMapping("/product/{productId}")
	public ResponseEntity<String> get(@PathVariable Integer productId) {
		try {
			Product theProduct = product.findById(productId);
			return new ResponseEntity<String>(HttpStatus.OK).ok(theProduct.toString());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND).badRequest().body("Product Not Found");
		}
	}

	@PutMapping("/product/{productId}")
	public ResponseEntity<?> updateUser(@RequestBody Product theproduct, @PathVariable Integer productId) {
		try {
			Product getProduct = product.findById(productId);
			getProduct.setName(theproduct.getName());
			getProduct.setPrice(theproduct.getPrice());
			product.saveProduct(getProduct);
			return new ResponseEntity<>(HttpStatus.OK).ok(theproduct.toString());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<>(HttpStatus.NOT_FOUND).badRequest().body("Product Not Found");
		}
	}

	@DeleteMapping("/product/{productId}")
	public ResponseEntity<String> deleteUser(@PathVariable Integer productId) {
		try {
			Product theProduct = product.findById(productId);
			product.Delete(theProduct.getId());
			return new ResponseEntity<String>(HttpStatus.OK).ok(theProduct.toString());
		} catch (NoSuchElementException e) {
			// TODO: handle exception
			return new ResponseEntity<String>(HttpStatus.NOT_FOUND).badRequest().body("Product Not Found");
		}
	}

}

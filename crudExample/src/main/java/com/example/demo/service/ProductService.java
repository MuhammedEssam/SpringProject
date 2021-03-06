package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepository repo;

	public List<Product> findAll() {
		return (List<Product>) repo.findAll();
	}

	public Product saveProduct(Product product) {
		return repo.save(product);
	}

	public Product findById(Integer id) {
		return repo.findById(id).get();
	}

	public void Delete(Integer id) {
		repo.deleteById(id);
	}
}

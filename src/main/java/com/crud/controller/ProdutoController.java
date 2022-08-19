package com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.model.Produto;
import com.crud.service.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping("")
	public ResponseEntity<List<Produto>> findAll() {
		return ResponseEntity.ok().body(produtoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Produto> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(produtoService.findById(id));
	}
	
	@PostMapping("")
	public ResponseEntity<Produto> create(@RequestBody Produto produto ) {
		return ResponseEntity.created(null).body(produtoService.create(produto));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Produto> update(@RequestBody Produto produto, @PathVariable Long id) throws Exception {
		produto.setId(id);
		produtoService.update(produto);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remove(@PathVariable Long id) throws Exception {
		produtoService.remove(id);
		return ResponseEntity.noContent().build();
	}

}

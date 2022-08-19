package com.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.exception.ProdutoConflictException;
import com.crud.exception.ProdutoNullException;
import com.crud.model.Produto;
import com.crud.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository repository;
	
	public List<Produto> findAll() {
		return repository.findAll();
	}
	
	public Produto findById(Long id) {
		return repository.findById(id).orElse(null);
	}
	
	public Produto create(Produto produto) {
		if(produto.getDataValidade() == null || produto.getIdFuncionario() == null || produto.getNome() == null 
			|| produto.getQuantidade() == null || produto.getValor() == null || produto.getValor() < 0) {
			throw new ProdutoNullException();
		}
			
		return repository.save(produto);
	}
	
	public Produto update(Produto produto) throws ProdutoConflictException {
		Produto produtoAtual = repository.findById(produto.getId()).orElse(null);
		if(produtoAtual == null) {
			throw new ProdutoConflictException();
		}
		
		if(produto.getDataValidade() == null) produto.setDataValidade(produtoAtual.getDataValidade());
		if(produto.getIdFuncionario() == null) produto.setIdFuncionario(produtoAtual.getIdFuncionario());
		if(produto.getNome() == null) produto.setNome(produtoAtual.getNome());;
		if(produto.getQuantidade() == null) produto.setQuantidade(produtoAtual.getQuantidade());
		if(produto.getValor() == null) produto.setValor(produtoAtual.getValor());
		
		return repository.save(produto);
	}
	
	public void remove(Long id) throws Exception {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			throw new ProdutoConflictException();			
		}
	}
	

}

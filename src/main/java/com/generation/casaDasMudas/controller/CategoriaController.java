package com.generation.casaDasMudas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.casaDasMudas.model.Categoria;
import com.generation.casaDasMudas.repository.CategoriaRepository;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/categoria")

public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	@GetMapping("/buscar")
	public ResponseEntity<List<Categoria>> getAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/buscar/{idCategoria}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id){
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/buscar/tipo/{tipoCategoria}")
	public ResponseEntity<List<Categoria>> getByTipo(@PathVariable String tipoCategoria){
		return ResponseEntity.ok(repository.findAllByTipoCategoriaContainingIgnoreCase(tipoCategoria));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> post (@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Categoria> put (@Valid @RequestBody Categoria categoria){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}
	
	@DeleteMapping("/deletar/{id}")
	public void delete (@PathVariable Long id) {
		repository.deleteById(id);
	}
	
}

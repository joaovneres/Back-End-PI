package com.generation.casaDasMudas.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categoria {

	// Criando chave primária
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	// Usar long maiusculo em tudo
	private Long idCategoria;

	@NotNull
	@Size(min = 0, max = 255)
	private String tipoCategoria;
	@NotNull
	@Size(min = 0, max = 255)
	private String tamanhoCategoria;
	@NotNull
	private Boolean alimenticiaCategoria;

	//Criando chave
	@OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;
	
	// Criando Getters and setters
	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getTipoCategoria() {
		return tipoCategoria;
	}

	public void setTipoCategoria(String tipoCategoria) {
		this.tipoCategoria = tipoCategoria;
	}

	public String getTamanhoCategoria() {
		return tamanhoCategoria;
	}

	public void setTamanhoCategoria(String tamanhoCategoria) {
		this.tamanhoCategoria = tamanhoCategoria;
	}

	public Boolean isAlimenticiaCategoria() {
		return alimenticiaCategoria;
	}

	public void setAlimenticiaCategoria(Boolean alimenticiaCategoria) {
		this.alimenticiaCategoria = alimenticiaCategoria;
	}

	public List<Produto> getProduto() {
		return produto;
	}

	public void setProduto(List<Produto> produto) {
		this.produto = produto;
	}

	public Boolean getAlimenticiaCategoria() {
		return alimenticiaCategoria;
	}
}
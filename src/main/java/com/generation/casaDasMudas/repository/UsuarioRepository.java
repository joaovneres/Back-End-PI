package com.generation.casaDasMudas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.casaDasMudas.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	public Optional<Usuario> findByEmailUsuario(String emailUsuario);;

	public List<Usuario> findAllByNomeUsuarioContainingIgnoreCase(@Param("nome") String nomeUsuario);

}

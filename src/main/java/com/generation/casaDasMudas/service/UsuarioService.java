package com.generation.casaDasMudas.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.casaDasMudas.model.Usuario;
import com.generation.casaDasMudas.model.UsuarioLogin;
import com.generation.casaDasMudas.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// Método responsável por cadastrar usuário
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {
		if (usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario()).isPresent())
			return Optional.empty();
		usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));
		return Optional.of(usuarioRepository.save(usuario));

	}

	// Método responsável por atualizar usuário
	public Optional<Usuario> atualizarUsuario(Usuario usuario) {

		if (usuarioRepository.findById(usuario.getIdUsuario()).isPresent()) {
			Optional<Usuario> buscaUsuario = usuarioRepository.findByEmailUsuario(usuario.getEmailUsuario());

			if ((buscaUsuario.isPresent()) && (buscaUsuario.get().getIdUsuario() != usuario.getIdUsuario()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			usuario.setSenhaUsuario(criptografarSenha(usuario.getSenhaUsuario()));

			return Optional.ofNullable(usuarioRepository.save(usuario));

		}

		return Optional.empty();

	}

	// Método responsável por autenticar o usuário
	public Optional<UsuarioLogin> autenticarUsuario(Optional<UsuarioLogin> usuarioLogin) {

		Optional<Usuario> usuario = usuarioRepository.findByEmailUsuario(usuarioLogin.get().getUsuarioLogin());

		if (usuario.isPresent()) {

			if (compararSenhas(usuarioLogin.get().getSenhaLogin(), usuario.get().getSenhaUsuario())) {

				usuarioLogin.get().setIdLogin(usuario.get().getIdUsuario());
				usuarioLogin.get().setNomeLogin(usuario.get().getNomeUsuario());
				usuarioLogin.get().setFotoLogin(usuario.get().getFotoUsuario());
				usuarioLogin.get().setTokenLogin(gerarBasicToken(usuarioLogin.get().getUsuarioLogin(), usuarioLogin.get().getSenhaLogin()));
				usuarioLogin.get().setSenhaLogin(usuario.get().getSenhaUsuario());

				return usuarioLogin;

			}
		}
		
		return Optional.empty();

	}

	//Método responsável por criptografar as senhas
	private String criptografarSenha(String senha) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.encode(senha);

	}

	//Método responsável por comparar senhas
	private boolean compararSenhas(String senhaDigitada, String senhaBanco) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		return encoder.matches(senhaDigitada, senhaBanco);

	}

	//Método responsável por gerar token basico
	private String gerarBasicToken(String usuario, String senha) {

		String token = usuario + ":" + senha;
		byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(Charset.forName("US-ASCII")));
		return "Basic " + new String(tokenBase64);

	}

}
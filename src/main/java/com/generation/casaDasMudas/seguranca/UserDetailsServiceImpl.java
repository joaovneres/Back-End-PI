package com.generation.casaDasMudas.seguranca;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.casaDasMudas.model.Usuario;
import com.generation.casaDasMudas.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository oUserRepository;

	@Override
	public UserDetails loadUserByUsername(String oUserName) throws UsernameNotFoundException {
 
		Optional<Usuario> oUser = oUserRepository.findByEmailUsuario(oUserName);
		
		oUser.orElseThrow(() -> new UsernameNotFoundException(oUserName + " not found."));
		return oUser.map(UserDetailsImpl::new).get();
	}
}

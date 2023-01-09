package com.example.arriendos;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.arriendos.repositories.UsuarioRepository;



@Service("UserDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{


    @Autowired
	private UsuarioRepository repository;

    private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String rut) throws UsernameNotFoundException {
		
		com.example.arriendos.model.Usuario user = repository.getById(rut);

        if(user == null) {
        	logger.error("Error en el Login: no existe el usuario '" + rut + "' en el sistema!");		
        	throw new UsernameNotFoundException("Username: " + rut + " no existe en el sistema!");

        }
        
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        if(authorities.isEmpty()) {
			System.out.print(authorities.isEmpty());
			
        	logger.error("Error en el Login: Usuario '" + rut + "' no tiene roles asignados!");
        	throw new UsernameNotFoundException("Error en el Login: usuario '" + rut + "' no tiene roles asignados!");
        }else{
			System.out.println(user.getId());
			System.out.println(authorities.get(0).getAuthority());
		}

		return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), true , true, true, true, authorities);

	}

    
}

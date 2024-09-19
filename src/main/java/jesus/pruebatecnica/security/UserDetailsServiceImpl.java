package jesus.pruebatecnica.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jesus.pruebatecnica.models.Usuario;
import jesus.pruebatecnica.repositories.UsuariosRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuariosRepository usuariosRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuariosRepository
        .findOneByNombre(username)
        .orElseThrow(() -> new UsernameNotFoundException("not foundxd"));
        
        return new UserDetailsImpl(usuario);
    }
    
}

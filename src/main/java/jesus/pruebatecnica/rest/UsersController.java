package jesus.pruebatecnica.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jesus.pruebatecnica.models.Usuario;
import jesus.pruebatecnica.repositories.UsuariosRepository;

@RestController
@RequestMapping("/usuarios")
public class UsersController {

    @Autowired
    UsuariosRepository usuariosRepository;
   
    @PostMapping("/")
    public void agregarUsuario(@RequestBody Usuario usuario){
        usuariosRepository.save(usuario);
    }


}

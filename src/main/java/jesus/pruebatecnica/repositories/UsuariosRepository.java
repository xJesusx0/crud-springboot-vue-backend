package jesus.pruebatecnica.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import jesus.pruebatecnica.models.Usuario;

@Service
public interface UsuariosRepository extends JpaRepository<Usuario, Long> {
    public Optional<Usuario> findOneByNombre(String nombre);
}

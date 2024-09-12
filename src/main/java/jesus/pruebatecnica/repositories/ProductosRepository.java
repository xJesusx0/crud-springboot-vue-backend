package jesus.pruebatecnica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jesus.pruebatecnica.models.Producto;

@Repository
public interface ProductosRepository extends JpaRepository<Producto, Long> {
}
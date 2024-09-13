package jesus.pruebatecnica.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jesus.pruebatecnica.models.Producto;
import jesus.pruebatecnica.repositories.ProductosRepository;
import jesus.pruebatecnica.response.ApiResponse;

@RestController
public class MainController {

    @Autowired
    private ProductosRepository repository;

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return repository.findAll();
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @GetMapping("/productos/{id}")
    public ResponseEntity obtenerProductoPorId(@PathVariable Long id) {

        if(id == null){
            return ResponseEntity.status(400)
            .body(new ApiResponse(null, "El parametro id no debe estar vacio", false));
        }

        return repository.findById(id)
            .map(producto -> 
                ResponseEntity.status(200)
                .body(new ApiResponse(producto, "Producto encontrado",true))
            )
            .orElseGet( () -> 
                ResponseEntity.status(404)
                .body(new ApiResponse(null,"Producto no encontrado",false))
            );
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked", "unused" })
    @PostMapping("/productos")
    public ResponseEntity insertarProducto(@RequestBody Producto producto){
        
        if(!producto.productoValido()){
            return ResponseEntity.status(400)
            .body(
                new ApiResponse(null,"Producto invalido", false)
            );
        } 

        Producto productoGuardado = repository.save(producto);

        if(productoGuardado != null){
            return ResponseEntity.status(201)
            .body(
                new ApiResponse(productoGuardado,"Producto guardado correctamente",true)
            );

        } else {
            return ResponseEntity.status(500)
            .body(
                new ApiResponse(null,"Ha ocurrido un error al insertar el producto", false)
            );
        }

    }

    @SuppressWarnings({ "unused", "rawtypes", "unchecked" })
    @PutMapping("/productos/{id}")
    public ResponseEntity actualizarProducto(@PathVariable long id, @RequestBody Producto nuevoProducto){
        
        if(!nuevoProducto.productoValido()){
            return ResponseEntity.status(400)
            .body(
                new ApiResponse(null, "Producto invalido", false)
            );
        }
        
        return repository.findById(id)
            .map(productoExistente -> {
                productoExistente.setNombre(nuevoProducto.getNombre());
                productoExistente.setCantidad(nuevoProducto.getCantidad());
                productoExistente.setPrecio(nuevoProducto.getPrecio());

                Producto productoActualizado = repository.save(productoExistente);
                
                return ResponseEntity.status(200)
                .body(
                    new ApiResponse(productoExistente,"Producto actualizado correctamente",true)
                );
            })
            .orElseGet(() -> 
                ResponseEntity.status(404)
                .body(new ApiResponse(null,"Producto no encontrado", false))
            );
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping("/productos/{id}")
    public ResponseEntity eliminarProducto(@PathVariable Long id){
        
        if(id == null){
            return ResponseEntity.status(400)
            .body(
                new ApiResponse(null,"El parametro id no puede estar vacio", false)
            );
        }

        return repository.findById(id)
            .map(producto -> {
                repository.delete(producto);
                
                return ResponseEntity.status(200)
                .body(
                    new ApiResponse(null,"Producto eliminado correctamente",true)
                );
            })
            .orElseGet(() -> 
                ResponseEntity.status(404)
                .body(
                    new ApiResponse(null,"Producto no encontrado",false)
                )
            );

    }

    @GetMapping("/productos/")
        public List<Producto> obtenerProductosConBarra() {
            return repository.findAll();
        }
        
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @PutMapping("/productos/")
        public ResponseEntity editarProductoConBarra(){
            return ResponseEntity.status(400).body(
                new ApiResponse(null,"El id no puede estar vacio", false)
            );
        }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @DeleteMapping("/productos/")
        public ResponseEntity eliminarProductoConBarra(){
            return ResponseEntity.status(400).body(
                new ApiResponse(null,"El id no puede estar vacio", false)
            );
        }
}


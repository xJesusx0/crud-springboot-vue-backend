package jesus.pruebatecnica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import jesus.pruebatecnica.models.Producto;
import jesus.pruebatecnica.repositories.ProductosRepository;
import jesus.pruebatecnica.response.ApiResponse;
import jesus.pruebatecnica.rest.MainController;

public class MainControllerTest {

    @Mock
    private ProductosRepository repository;

    @InjectMocks
    private MainController mainController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        Producto producto1 = new Producto();
        producto1.setId(1L);
        producto1.setNombre("Teclado");
        producto1.setPrecio(100.0);
        producto1.setCantidad(20);

        Producto producto2 = new Producto();
        producto2.setId(2L);
        producto2.setNombre("Ratón");
        producto2.setPrecio(50.0);
        producto2.setCantidad(15);

        List<Producto> productos = Arrays.asList(producto1, producto2);
        when(repository.findAll()).thenReturn(productos);
        when(repository.findById(1L)).thenReturn(Optional.of(producto1));
        when(repository.findById(3L)).thenReturn(Optional.empty());

        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Laptop gamer");
        productoNuevo.setCantidad(10);
        productoNuevo.setPrecio(199.99);

        // when(repository.save(Mockito.any(Producto.class))).thenReturn(productoNuevo);
        when(repository.save(any(Producto.class))).thenAnswer(invocation -> invocation.getArgument(0));
        doNothing().when(repository).deleteById(1L);
    }

    @Test
    public void testObtenerProductos() {
        List<Producto> productos = mainController.obtenerProductos();
        assertNotNull(productos);
        assertEquals(2, productos.size());
        assertEquals("Teclado", productos.get(0).getNombre());
    }

    @Test
    public void testObtenerProductoPorId() {
        ResponseEntity<ApiResponse<Producto>> response = mainController.obtenerProductoPorId(1L);
        ApiResponse<Producto> responseBody = response.getBody();

        assertTrue(responseBody.isSuccess());
        assertEquals("Producto encontrado", responseBody.getMessage());
        assertEquals("Teclado", responseBody.getData().getNombre());
    }

    @Test
    public void testObtenerProductoPorIdIncorrecto() {
        ResponseEntity<ApiResponse<Producto>> response = mainController.obtenerProductoPorId(3L);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNull(responseBody.getData());
        assertFalse(responseBody.isSuccess());
    }

    @Test
    public void testInsertarProducto() {
        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Laptop gamer");
        productoNuevo.setCantidad(10);
        productoNuevo.setPrecio(199.99);

        ResponseEntity<ApiResponse<Producto>> response = mainController.insertarProducto(productoNuevo);
        ApiResponse<Producto> responseBody = response.getBody();
        System.out.println(responseBody.getMessage());
        assertNotNull(responseBody);
        assertTrue(responseBody.isSuccess());
        assertEquals("Producto guardado correctamente", responseBody.getMessage());
        assertEquals(productoNuevo.getNombre(), responseBody.getData().getNombre());
        assertEquals(productoNuevo.getCantidad(), responseBody.getData().getCantidad());
        assertEquals(productoNuevo.getPrecio(), responseBody.getData().getPrecio());
    }

    @Test
    public void testInsertarProductoIncorrecto() {
        Producto productoInvalido = new Producto();
        productoInvalido.setNombre("");
        productoInvalido.setCantidad(-1);
        productoInvalido.setPrecio(-199.99);

        ResponseEntity<ApiResponse<Producto>> response = mainController.insertarProducto(productoInvalido);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNotNull(responseBody);
        assertFalse(responseBody.isSuccess());
        assertEquals("Producto invalido", responseBody.getMessage());
        assertNull(responseBody.getData());

    }

    @Test
    public void testActualizarProducto(){

        Producto productoNuevo = new Producto();
        productoNuevo.setNombre("Monitor 4k");
        productoNuevo.setCantidad(10);
        productoNuevo.setPrecio(199.99);

        ResponseEntity<ApiResponse<Producto>> response = mainController.actualizarProducto(1L,productoNuevo);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNotNull(responseBody);
        assertTrue(responseBody.isSuccess());
        assertEquals("Producto actualizado correctamente", responseBody.getMessage());
        assertNotNull(responseBody.getData());       
    }

    @Test
    public void testActualizarProductoIncorrecto() {
        Producto productoInvalido = new Producto();
        productoInvalido.setNombre("");
        productoInvalido.setCantidad(-1);
        productoInvalido.setPrecio(-199.99);

        ResponseEntity<ApiResponse<Producto>> response = mainController.actualizarProducto(1L,productoInvalido);
        ApiResponse<Producto> responseBody = response.getBody();
        
        assertNotNull(responseBody);
        assertFalse(responseBody.isSuccess());
        assertEquals("Producto invalido", responseBody.getMessage());
        assertNull(responseBody.getData());
    }

    @Test
    public void testActualizarProductoNoEncontrado() {
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre("Monitor 4k");
        nuevoProducto.setCantidad(5);
        nuevoProducto.setPrecio(99.99);

        ResponseEntity<ApiResponse<Producto>> response = mainController.actualizarProducto(3L, nuevoProducto);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNotNull(responseBody);
        assertFalse(responseBody.isSuccess());
        assertEquals("Producto no encontrado", responseBody.getMessage());
        assertNull(responseBody.getData());
    }

    @Test
    public void testEliminarProducto() {
        
        ResponseEntity<ApiResponse<Producto>> response = mainController.eliminarProducto(1L);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNotNull(responseBody);
        assertTrue(responseBody.isSuccess());
        assertEquals(responseBody.getMessage(), "Producto eliminado correctamente");
    }

    @Test
    public void testEliminarProductoNoEncontrado() {
        
        ResponseEntity<ApiResponse<Producto>> response = mainController.eliminarProducto(3L);
        ApiResponse<Producto> responseBody = response.getBody();

        assertNotNull(responseBody);
        assertFalse(responseBody.isSuccess());
        assertEquals(responseBody.getMessage(), "Producto no encontrado");
    }
}

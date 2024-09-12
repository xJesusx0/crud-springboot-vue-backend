package jesus.pruebatecnica.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false, unique = true)
    @Getter
    @Setter
    private String nombre;

    @Column(nullable = false)
    @Getter
    @Setter
    private Double precio;

    @Column(nullable = false)
    @Getter
    @Setter
    private Integer cantidad;


    public boolean productoValido(){
        
        if (this.precio < 0){
            return false;
        }

        if(this.cantidad < 0 ){
            return false;
        }

        if(this.nombre == null || this.nombre.isEmpty()){
            return false;
        }

        return true;
    }
}
package jesus.pruebatecnica.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class AuthCredentials {

    private String nombre;
    private String contraseña; 

    public AuthCredentials(){

    }    

}

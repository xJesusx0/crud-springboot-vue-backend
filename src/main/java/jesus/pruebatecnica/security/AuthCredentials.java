package jesus.pruebatecnica.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthCredentials {

    private String nombre;
    private String contraseña; 

    public AuthCredentials(){

    }    

}

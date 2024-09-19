package jesus.pruebatecnica.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Data
public class AuthCredentials {
    @Setter @Getter
    private String nombre;
    
    @Setter @Getter
    private String contraseña; 

    public AuthCredentials(){

    }    

}

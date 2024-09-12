package jesus.pruebatecnica.response;

import lombok.Getter;
import lombok.Setter;

public class ApiResponse<T> {

    @Getter @Setter
    private T data;
    
    @Getter @Setter
    private String message;
    
    @Getter @Setter
    private boolean success;

    public ApiResponse(T data, String message, boolean success) {
        this.data = data;
        this.message = message;
        this.success = success;
    }

}
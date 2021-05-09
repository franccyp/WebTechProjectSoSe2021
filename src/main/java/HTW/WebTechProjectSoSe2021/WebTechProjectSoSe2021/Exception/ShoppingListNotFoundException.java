package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ShoppingListNotFoundException extends Exception{

    private static final long serialVersionUID = 1L;

    public ShoppingListNotFoundException(String message){
        super(message);
    }
}

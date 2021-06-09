package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListDTO {

    public String author;
    public String list_name;
    public List<String> list_items = new ArrayList<String>();

    public ShoppingListDTO() {
    }

    public ShoppingListDTO(String author, String list_name, List<String> list_items) {
        this.author = author;
        this.list_name = list_name;
        this.list_items = list_items;
    }


}

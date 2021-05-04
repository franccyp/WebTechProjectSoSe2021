package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import java.util.ArrayList;
import java.util.List;

public class UserEntity {

    private long userId;
    private String username;
    private List<ShoppingList> ShoppingLists = new ArrayList<ShoppingList>();

    public UserEntity(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<ShoppingList> getShoppingLists() {
        return ShoppingLists;
    }

    public void setShoppingList(List<ShoppingList> shoppingLists) {
        ShoppingLists = shoppingLists;
    }
}

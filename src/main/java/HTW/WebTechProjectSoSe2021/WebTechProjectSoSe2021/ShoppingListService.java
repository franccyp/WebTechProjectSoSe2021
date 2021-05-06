package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.stereotype.Component;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Component
public class ShoppingListService {

    @OneToMany
    private final List<ShoppingListEntity> SHOPPING_LIST_ENTITIES = new ArrayList<ShoppingListEntity>();

    public ShoppingListService() {
    }

    public static List<ShoppingListEntity> allShoppingLists() {
        return SHOPPING_LIST_ENTITIES;
    }

    public static void createList(String listName, String author) {

        ShoppingListEntity shoppinglist = new ShoppingListEntity(listName, author);
        SHOPPING_LIST_ENTITIES.add(shoppinglist);

    }

}

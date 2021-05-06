package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    private final ShoppingListRepository repository;

    public ShoppingListService(ShoppingListRepository repository) {
        this.repository = repository;
    }


    //saves list to DB
    public void saveList(ShoppingListEntity shoppinglist) {
        repository.save(shoppinglist);
    }

}

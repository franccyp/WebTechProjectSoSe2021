package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingListService {

    @Autowired
    private ShoppingListRepository shopRepository;

    public ShoppingListService(ShoppingListRepository repository) {

        this.shopRepository = repository;
    }

    //saves list to DB
    public void saveList(ShoppingListEntity shoppinglist) {

        shopRepository.save(shoppinglist);
    }

    //find all shopping lists
    public List<ShoppingListEntity> findAll() {

        var it = shopRepository.findAll();

        var sList = new ArrayList<ShoppingListEntity>();
        it.forEach(e -> sList.add(e));

        return sList;
    }

    //determine the total number of shopping lists available
    public Long count() {

        return shopRepository.count();
    }

    //remove or delete shopping list by its id
    public void deleteById(Long shopId) {

        shopRepository.deleteById(shopId);
    }
}

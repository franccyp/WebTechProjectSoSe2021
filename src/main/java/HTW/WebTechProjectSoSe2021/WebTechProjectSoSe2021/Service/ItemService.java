package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private final ItemRepository itemRepository;
    private RestTemplate restTemplate;

    public ItemService(ItemRepository repository) {
        this.itemRepository = repository;
    }

    //saves list item to DB
    public ItemEntity saveListItem(ItemEntity listItem) {

        return itemRepository.save(listItem);

    }


    //find list items by shopping list id
    public List<ItemEntity> findByShoppingListId(Long shoppingListId) {

        var it = itemRepository.findAll();

        var sList = new ArrayList<ItemEntity>();
        it.forEach(e -> {
            if (e.getList_id() == shoppingListId) {
                sList.add(e);
            }
        });
        return sList;
    }

    //delete list items by shopping list id
    public void deleteByShoppingListId(Long shoppingListId) {

        var it = itemRepository.findAll();

        it.forEach(e -> {
            if (e.getList_id() == shoppingListId) {
                itemRepository.deleteById(e.getItem_id());
            }
        });
    }
}

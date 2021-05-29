package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    //find an item by its id
    public Optional<ItemEntity> findById(Long itemId) {
        return itemRepository.findById(itemId);
    }

    //find list items by shopping list id
    public List<ItemEntity> findByShoppingListId(Long shoppingListId) {

        var it = itemRepository.findAll();

        var sList = new ArrayList<ItemEntity>();
        it.forEach(e -> {
            if (e.getList_id().equals(shoppingListId)) {
                sList.add(e);
            }
        });
        return sList;
    }

    //delete list items by shopping list id
    public void deleteByShoppingListId(Long shoppingListId) {

        var it = itemRepository.findAll();

        it.forEach(e -> {
            if (e.getList_id().equals(shoppingListId)) {
                itemRepository.deleteById(e.getItem_id());
            }
        });
    }

    //remove an item by its id
    public void deleteById(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ListItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListItemService {

    @Autowired
    private final ListItemRepository itemRepository;
    private RestTemplate restTemplate;

    public ListItemService(ListItemRepository repository) {
        this.itemRepository = repository;
    }

    //saves list item to DB
    public ListItemEntity saveListItem(ListItemEntity listItem) {

        return itemRepository.save(listItem);

    }


    //find list items by shopping list id
    public List<ListItemEntity> findByShoppingListId(Long shoppingListId) {

        var it = itemRepository.findAll();

        var sList = new ArrayList<ListItemEntity>();
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

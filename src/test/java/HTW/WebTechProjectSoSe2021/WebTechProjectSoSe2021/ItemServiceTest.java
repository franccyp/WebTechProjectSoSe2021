package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ItemRepository;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ItemServiceTest {


    @Autowired
    private ItemService itemTestService;

    @MockBean
    private ItemRepository itemRepository;

    @Test
    @DisplayName("should find item 1 and 3 from a list id")
    void testFindByListId() {

        var item1 = new ItemEntity("Banana");
        item1.setList_id(1L);
        var item2 = new ItemEntity("Apple");
        item2.setList_id(2L);
        var item3 = new ItemEntity("Orange");
        item3.setList_id(1L);
        doReturn(List.of(item1, item2, item3)).when(itemRepository).findAll();

        List<ItemEntity> testList = itemTestService.findByShoppingListId(1L);

        Assertions.assertSame(testList.size(), 2, "The number of shopping lists returned was wrong.");
        Assertions.assertSame(testList.get(0).getItem_name(), "Banana", "The wrong item was returned.");
        Assertions.assertSame(testList.get(1).getItem_name(), "Orange", "The wrong item was returned.");
    }

}

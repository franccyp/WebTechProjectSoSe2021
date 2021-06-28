package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Repo.ShoppingListRepository;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@SpringBootTest
public class ShoppingListServiceTest {

    @Autowired
    private ShoppingListService testService;

    @MockBean
    private ShoppingListRepository repository;

    @Test
    @DisplayName("should find shopping list through the user's name")
    void testFindByAuthorName() {
        var s1 = new ShoppingListEntity("Monday's Food");
        s1.setAuthor("franccyp");
        var s2 = new ShoppingListEntity("Dinner");
        s2.setAuthor("Kris");
        doReturn(List.of(s1, s2)).when(repository).findAll();

        List<ShoppingListEntity> franccysList = testService.findAll("franccyp");

        Assertions.assertSame(franccysList.size(), 1, "The number of shopping lists returned was wrong.");
        Assertions.assertSame(franccysList.get(0).getList_name(), "Monday's Food", "The wrong list was returned.");
    }
    
}

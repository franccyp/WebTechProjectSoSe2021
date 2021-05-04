package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {

        return "Hello World!";
    }

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/shoppinglists")
    public List<ShoppingList> allShoppingLists() {

        return ShoppingListService.allShoppingLists();
    }

    @PostMapping("/addList/{listname}/{author}")
    public void createList(
            @PathVariable("listname") String listname,
            @PathVariable("author") String author
    ) {

        ShoppingListService.createList(listname, author);
    }
}


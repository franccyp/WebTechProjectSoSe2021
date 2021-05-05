package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ShoppingListController {

    @RequestMapping("/")
    public String index() {

        return "Hello World!";
    }

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/shoppinglists")
    public List<ShoppingListEntity> allShoppingLists() {

        return ShoppingListService.allShoppingLists();
    }

    @GetMapping("/addlist/{listname}/{author}")
    public String createList(
            @PathVariable("listname") String listname,
            @PathVariable("author") String author
    ) {
        ShoppingListService.createList(listname, author);
        String s = "Die Liste " + listname + " von " + author + " wurde hinzugefügt!";
        return s;
    }
}


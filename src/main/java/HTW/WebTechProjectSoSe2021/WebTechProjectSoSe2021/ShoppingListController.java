package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class ShoppingListController {

    @RequestMapping("/")
    public String index() {

        return "Hello World!";
    }

    @Autowired
    private ShoppingListService shoppingListService;

//    @GetMapping("/shoppinglists")
//    public List<ShoppingListEntity> allShoppingLists() {
//
//        return ShoppingListService.allShoppingLists();
//    }

    @GetMapping("/add-list")
    public String addListPage(@ModelAttribute ShoppingListEntity shoppingList, Model model) {
        model.addAttribute("shoppingList", shoppingList);
        shoppingListService.saveList(shoppingList);
        return "list-added";
    }

    @PostMapping("/list-added")
    public String addListPage(Model model) {
        return "List added!";
    }
}


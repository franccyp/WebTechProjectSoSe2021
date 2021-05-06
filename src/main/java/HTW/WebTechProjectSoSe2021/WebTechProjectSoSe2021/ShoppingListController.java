package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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

    @PostMapping("/add-list")
    public String addListPage(@ModelAttribute ShoppingListEntity shoppingList, Model model) {
        ShoppingListService.saveList(shoppingList);
        //model.addAttribute("shoppingList", shoppingList);
        return "list-added";
    }

    @PostMapping("/list-added")
    public String addListPage(Model model) {

        return "List added!";
    }
}


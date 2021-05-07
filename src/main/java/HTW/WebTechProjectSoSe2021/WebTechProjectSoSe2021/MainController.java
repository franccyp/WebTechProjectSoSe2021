package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MainController {

//    //Codes for UserEntity
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/users")
//    public List<UserEntity> allUsers() {
//
//        return userService.findAll();
//    }
//
//    @GetMapping("/users/count")
//    public Long count() {
//
//        return userService.count();
//    }
//
//    @DeleteMapping("/users/{id}")
//    public void delete(@PathVariable String id) {
//
//        Long userId = Long.parseLong(id);
//        userService.deleteById(userId);
//    }

    @RequestMapping("/")
    public String index() {

        return "Hello World!";
    }

    @Autowired
    private ShoppingListService shoppingListService;

    @GetMapping("/shoppinglists")
    public List<ShoppingListEntity> allShoppingLists() {

        return shoppingListService.findAll();
    }

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


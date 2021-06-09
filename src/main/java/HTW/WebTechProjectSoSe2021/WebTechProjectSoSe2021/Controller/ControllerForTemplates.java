package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListDTO;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Controller
public class ControllerForTemplates {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ShoppingListService shoppingListService;


    @GetMapping("/alllists")
    public String listsTable(Model model) {
        List<ShoppingListEntity> slists = shoppingListService.findAll();
        model.addAttribute("slists", slists);
        return "listtable";
    }

    @GetMapping("/singlelistview/{id}")
    public String listsItemsFromList(Model model, @PathVariable("id") Long shoppingListId) {
        List<ItemEntity> shoppingItems = itemService.findByShoppingListId(shoppingListId);
        model.addAttribute("shoppingItems", shoppingItems);
        return "singlelistview";
    }

    @GetMapping("/createlist")
    public String createShoppingListForm(Model model) {
        //model.addAttribute("shoppingList", new ShoppingListEntity());
        return "listcreation";
    }

    //create a shopping list through a userform
    @PostMapping("/createlist")
    public String createShoppingList(
            Model model,
            @RequestBody ShoppingListDTO listDTO) {
        ShoppingListEntity shoppingList = new ShoppingListEntity(listDTO.list_name, listDTO.author);
        var it = listDTO.list_items;
        it.forEach(item -> {
                    String itemName = item;
                    ItemEntity listItem = new ItemEntity(itemName);
                    shoppingList.addListItem(listItem);
                }
        );
        shoppingListService.saveList(shoppingList);
        //model.addAttribute("shoppingList", shoppingList);
        return "redirect:/createlist";//"listresult";
    }


}


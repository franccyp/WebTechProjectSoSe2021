package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.Endpoints;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.ViewNames;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ControllerForTemplates {

    @Autowired
    private ItemService itemService;
    @Autowired
    private ShoppingListService shoppingListService;


    @GetMapping(path = Endpoints.Site.INDEX)
    public ModelAndView showHomePage(){
        return new ModelAndView(ViewNames.INDEX);
    }

    @GetMapping(path = Endpoints.Site.SINGLE_LIST + "/{id}")
    public ModelAndView listsItemsFromList(Model model, @PathVariable("id") Long shoppingListId) {
        List<ItemEntity> shoppingItems = itemService.findByShoppingListId(shoppingListId);
        model.addAttribute("shoppingItems", shoppingItems);
        return new ModelAndView(ViewNames.SINGLE_LIST);
    }

    @GetMapping(path = Endpoints.Site.LIST)
    public ModelAndView createShoppingListForm(Model model) {
        model.addAttribute("shoppingList", new ShoppingListEntity());
        return new ModelAndView(ViewNames.LIST);
    }

    //create a shopping list through a userform
    @PostMapping(path = Endpoints.Site.LIST)
    public ModelAndView createShoppingList(@AuthenticationPrincipal OidcUser author, @ModelAttribute ShoppingListEntity shoppingList, Model model, @RequestParam(value = "itemName") String itemName) {
        ItemEntity listItem = new ItemEntity(itemName);
        shoppingList.addListItem(listItem);
        shoppingList.setAuthor(author.getGivenName());
        shoppingListService.saveList(shoppingList);
        model.addAttribute("shoppingList", shoppingList);
        return new ModelAndView(ViewNames.POST_LIST);
    }

    @GetMapping(path = Endpoints.Site.ALL_LISTS)
    public ModelAndView listsTable(@AuthenticationPrincipal OidcUser author,Model model) {
        List<ShoppingListEntity> slists = shoppingListService.findAll(author.getGivenName());
        model.addAttribute("slists", slists);
        return new ModelAndView(ViewNames.ALL_LISTS);
    }


}


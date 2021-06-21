package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.Endpoints;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.ViewNames;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListDTO;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class ControllerForTemplates {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ShoppingListService shoppingListService;

    //Homepage of the web app
    @GetMapping(path = Endpoints.Site.INDEX)
    public ModelAndView showHomePage(){
        return new ModelAndView(ViewNames.INDEX);
    }

    //about page for public view
    @GetMapping(path = Endpoints.Site.ABOUT)
    public ModelAndView showAbout(){
        return new ModelAndView(ViewNames.ABOUT);
    }

    //contact page for public view
    @GetMapping(path = Endpoints.Site.CONTACT)
    public ModelAndView showContact(){
        return new ModelAndView(ViewNames.CONTACT);
    }

    //get the userform for creating a shopping list
    @GetMapping(path = Endpoints.Site.LIST)
    public ModelAndView createShoppingListForm(Model model) {
        model.addAttribute("shoppingList", new ShoppingListEntity());
        return new ModelAndView(ViewNames.LIST);
    }

    //create a shopping list through a userform
    @PostMapping(path = Endpoints.Site.LIST)
    public String createShoppingList(@AuthenticationPrincipal OidcUser author, Model model, @RequestBody ShoppingListDTO listDTO) {
        ShoppingListEntity shoppingList = new ShoppingListEntity(listDTO.list_name, author.getGivenName());
        var it = listDTO.list_items;
        it.forEach(item -> {
                    String itemName = item;
                    ItemEntity listItem = new ItemEntity(itemName);
                    shoppingList.addListItem(listItem);
                }
        );
        shoppingListService.saveList(shoppingList);
        model.addAttribute("shoppingList", shoppingList);
        return "redirect:/createlist";
    }

    //get the lists created by a user
    @GetMapping(path = Endpoints.Site.ALL_LISTS)
    public ModelAndView listsTable(@AuthenticationPrincipal OidcUser author,Model model) {
        List<ShoppingListEntity> slists = shoppingListService.findAll(author.getGivenName());
        model.addAttribute("slists", slists);
        return new ModelAndView(ViewNames.ALL_LISTS);
    }
}


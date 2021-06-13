package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.Endpoints;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListDTO;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Exception.ResourceNotFoundException;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerForRest {

    @Autowired
    private ShoppingListService shoppingListService;

    @Autowired
    private ItemService itemService;

    //list out all available shopping lists
    @GetMapping(path = Endpoints.Rest.SHOPPING_LIST+"/{author}")
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingLists(@PathVariable(value = "author") @AuthenticationPrincipal OidcUser author) {
        var allLists = shoppingListService.findAll(author.getGivenName());
        return ResponseEntity.ok(allLists);
    }

    //list out the particular shopping list with the given id, if not found, exception is thrown
    @GetMapping(path = Endpoints.Rest.SHOPPING_LIST+"/{id}")
    public ResponseEntity<ShoppingListEntity> getShoppingListById(@PathVariable(value = "id") Long shoppingListId)
            throws ResourceNotFoundException {
        ShoppingListEntity shoppingList = shoppingListService.findById(shoppingListId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank."));
        return ResponseEntity.ok().body(shoppingList);
    }

    //update a particular shopping list (its details) with the input id //doesnt really work
    @PutMapping(path = Endpoints.Rest.SHOPPING_LIST+"/{id}")
    public ResponseEntity<ShoppingListEntity> updateShoppingLists(@PathVariable(value = "id") Long shoppingListId,
                                                                  @Validated @RequestBody ShoppingListEntity listDetails) throws ResourceNotFoundException {
        ShoppingListEntity shoppingList = shoppingListService.findById(shoppingListId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank."));

        shoppingList.setList_name(listDetails.getList_name());
        shoppingList.setAuthor(listDetails.getAuthor());
        final ShoppingListEntity updatedList = shoppingListService.saveList(shoppingList);
        return ResponseEntity.ok(updatedList);
    }

    //remove a shopping list with input id from db
    @RequestMapping(path = Endpoints.Rest.SHOPPING_LIST + "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteList(@PathVariable("id") HttpStatus httpResponse, Long shoppingListId) throws ResourceNotFoundException {
        try {
            itemService.deleteByShoppingListId(shoppingListId);
            shoppingListService.deleteById(shoppingListId);
            return "redirect:/alllists"; //ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank.");
        }
    }

    @RequestMapping(path = Endpoints.Rest.ITEM+"/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ItemEntity> deleteItem(@PathVariable("id") Long itemId) throws ResourceNotFoundException {
        try {
            itemService.deleteById(itemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Item with the id : " + itemId + " is not available in the shopping list.");
        }

    }

    //create a shopping list through a userform
    @PostMapping(path = Endpoints.Rest.SHOPPING_LIST + "/createlist")
    public ResponseEntity<ShoppingListEntity> createShoppingList(@RequestBody ShoppingListDTO listDTO) {
        ShoppingListEntity shoppingList = new ShoppingListEntity(listDTO.list_name, listDTO.author);
        var it = listDTO.list_items;
        it.forEach(item -> {
                    String itemName = item;
                    ItemEntity listItem = new ItemEntity(itemName);
                    shoppingList.addListItem(listItem);
                }
        );
        shoppingListService.saveList(shoppingList);
        return ResponseEntity.ok(shoppingList);
    }


}
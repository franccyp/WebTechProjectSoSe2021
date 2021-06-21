package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Config.Endpoints;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ItemEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Exception.ResourceNotFoundException;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ItemService;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //list out all available shopping lists from a user
    @GetMapping(path = Endpoints.Rest.SHOPPING_LIST)
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingLists(@AuthenticationPrincipal OidcUser author) {
        var allLists = shoppingListService.findAll(author.getGivenName());
        return ResponseEntity.ok(allLists);
    }

    //list out the particular shopping list with the given id, if not found, exception is thrown
    @GetMapping(path = Endpoints.Rest.SHOPPING_LIST + "/{id}")
    public ResponseEntity<ShoppingListEntity> getShoppingListById(@PathVariable(value = "id") Long shoppingListId)
            throws ResourceNotFoundException {
        ShoppingListEntity shoppingList = shoppingListService.findById(shoppingListId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank."));
        return ResponseEntity.ok().body(shoppingList);
    }

    //update a particular shopping list (its details) with the input id
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
    public String deleteList(@PathVariable("id") Long shoppingListId) throws ResourceNotFoundException {
        try {
            itemService.deleteByShoppingListId(shoppingListId);
            shoppingListService.deleteById(shoppingListId);
            return "redirect:/alllists";
        } catch (Exception e) {
            throw new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank.");
        }
    }

    //remove an item from a shopping list
    @RequestMapping(path = Endpoints.Rest.ITEM + "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ItemEntity> deleteItem(@PathVariable("id") Long itemId) throws ResourceNotFoundException {
        try {
            itemService.deleteById(itemId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Item with the id : " + itemId + " is not available in the shopping list.");
        }
    }

    //update a particular item on a shopping list
    @PutMapping(path = Endpoints.Rest.ITEM + "/updatename/{id}/{itemName}")
    public ResponseEntity<ItemEntity> updateItemName(@PathVariable(value = "id") Long itemId,
                                                     @PathVariable(value = "itemName") String itemName) throws ResourceNotFoundException {
        ItemEntity item = itemService.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item with the id : " + itemId + " is not available in the databank."));
        item.setItem_name(itemName);
        final ItemEntity updatedItem = itemService.saveListItem(item);
        return ResponseEntity.ok(updatedItem);
    }
}
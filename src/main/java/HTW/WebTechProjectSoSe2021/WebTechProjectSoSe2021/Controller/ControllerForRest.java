package HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Controller;

import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Entity.ShoppingListEntity;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Exception.ResourceNotFoundException;
import HTW.WebTechProjectSoSe2021.WebTechProjectSoSe2021.Service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ControllerForRest {

    @Autowired
    private ShoppingListService shoppingListService;

    @RequestMapping("/greeting")
    public String index() {
        return "Welcome to ShopChop!";
    }

    //list out all available shopping lists
    @GetMapping("/shoppinglists")
    public ResponseEntity<List<ShoppingListEntity>> getAllShoppingLists() {
        var allLists = shoppingListService.findAll();
        return ResponseEntity.ok(allLists);
    }

    //list out the particular shopping list with the given id, if not found, exception is thrown
    @GetMapping("/shoppinglists/{id}")
    public ResponseEntity<ShoppingListEntity> getShoppingListById(@PathVariable(value = "id") Long shoppingListId)
            throws ResourceNotFoundException {
        ShoppingListEntity shoppingList = shoppingListService.findById(shoppingListId)
                .orElseThrow(() -> new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank."));
        return ResponseEntity.ok().body(shoppingList);
    }

    //update a particular shopping list (its details) with the input id //doesnt really work
    @PutMapping("/shoppinglists/{id}")
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
    @RequestMapping(value = "/shoppinglists/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<ShoppingListEntity> deleteList(@PathVariable("id") Long shoppingListId) throws ResourceNotFoundException {
        try {
            shoppingListService.deleteById(shoppingListId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Shopping list with the id : " + shoppingListId + " is not available in the databank.");
        }
    }
}

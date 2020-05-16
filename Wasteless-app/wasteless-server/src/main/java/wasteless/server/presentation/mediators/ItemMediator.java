package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.Item;
import wasteless.server.presentation.command_controller.ItemCommandController;
import wasteless.server.presentation.dto.ItemDTO;
import wasteless.server.presentation.query_controller.ItemQueryController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@Controller
public class ItemMediator {

    private final ItemQueryController itemQueryController;
    private final ItemCommandController itemCommandController;

    public ItemMediator(ItemQueryController itemQueryController, ItemCommandController itemCommandController) {
        this.itemQueryController = itemQueryController;
        this.itemCommandController = itemCommandController;
    }

    @GetMapping("/{groceryListId}/items")
    public List<ItemDTO> getAllItems(@PathVariable("groceryListId") Long groceryListId){
        return itemQueryController.getAllItems(groceryListId);
    }

    @GetMapping("/{groceryListId}/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(value = "groceryListId") Long groceryListId,
                                               @PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException{
        return itemQueryController.getItemById(groceryListId, itemId);
    }

    @PostMapping("/{groceryListId}/createItem")
    public ItemDTO createItem(@PathVariable(value = "groceryListId") Long groceryListId, @Valid @RequestBody Item item){
        return itemCommandController.createItem(groceryListId, item);
    }

    @PutMapping("/{groceryListId}/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable(value = "id") Long itemId,
                                              @PathVariable(value = "groceryListId") Long groceryListId,
                                              @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException{
        return itemCommandController.updateItem(itemId, groceryListId, itemDetails);
    }

    @GetMapping("/deleteItem/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException{
        return itemCommandController.deleteItem(itemId);
    }


}

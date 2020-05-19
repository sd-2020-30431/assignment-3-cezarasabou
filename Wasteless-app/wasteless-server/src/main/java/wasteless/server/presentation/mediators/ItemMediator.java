package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.Item;
import wasteless.server.business.command_handler.ItemCommandHandler;
import wasteless.server.presentation.dto.ItemDTO;
import wasteless.server.business.query_handler.ItemQueryHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController

public class ItemMediator {

    private final ItemQueryHandler itemQueryHandler;
    private final ItemCommandHandler itemCommandHandler;

    public ItemMediator(ItemQueryHandler itemQueryController, ItemCommandHandler itemCommandHandler) {
        this.itemQueryHandler = itemQueryController;
        this.itemCommandHandler = itemCommandHandler;
    }

    @GetMapping("/{groceryListId}/items")
    public List<ItemDTO> getAllItems(@PathVariable("groceryListId") Long groceryListId){
        return itemQueryHandler.getAllItems(groceryListId);
    }

    @GetMapping("/{groceryListId}/{id}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable(value = "groceryListId") Long groceryListId,
                                               @PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException{
        return itemQueryHandler.getItemById(groceryListId, itemId);
    }

    @PostMapping("/{groceryListId}/createItem")
    public ItemDTO createItem(@PathVariable(value = "groceryListId") Long groceryListId, @Valid @RequestBody Item item){
        return itemCommandHandler.createItem(groceryListId, item);
    }

    @PutMapping("/{groceryListId}/items/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable(value = "id") Long itemId,
                                              @PathVariable(value = "groceryListId") Long groceryListId,
                                              @Valid @RequestBody Item itemDetails) throws ResourceNotFoundException{
        return itemCommandHandler.updateItem(itemId, groceryListId, itemDetails);
    }

    @GetMapping("/deleteItem/{id}")
    public Map<String, Boolean> deleteItem(@PathVariable(value = "id") Long itemId)
            throws ResourceNotFoundException{
        return itemCommandHandler.deleteItem(itemId);
    }


}

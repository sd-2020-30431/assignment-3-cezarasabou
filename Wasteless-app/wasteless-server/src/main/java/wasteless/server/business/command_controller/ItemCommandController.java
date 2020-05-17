package wasteless.server.business.command_controller;

import org.springframework.http.ResponseEntity;
import wasteless.server.business.command_service.ItemCommandService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.Item;
import wasteless.server.presentation.dto.ItemDTO;
import wasteless.server.presentation.mapper.ItemMapper;

import java.util.HashMap;
import java.util.Map;

public class ItemCommandController {

    private final ItemCommandService itemService;
    private final ItemMapper itemMapper;

    public ItemCommandController(ItemCommandService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    public ItemDTO createItem(Long groceryListId, Item item) {
        return itemMapper.convertToDTO(itemService.createItem(groceryListId, item));
    }

    public ResponseEntity<ItemDTO> updateItem( Long itemId, Long groceryListId, Item itemDetails)
            throws ResourceNotFoundException {


        final Item updatedItem = itemService.updateItem(groceryListId,itemId,itemDetails);
        return ResponseEntity.ok(itemMapper.convertToDTO(updatedItem));
    }

    public Map<String, Boolean> deleteItem(Long itemId)
            throws ResourceNotFoundException {
        itemService.deleteItem(itemId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}

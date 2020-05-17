package wasteless.server.business.query_controller;

import org.springframework.http.ResponseEntity;
import wasteless.server.business.query_service.ItemQueryService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.Item;
import wasteless.server.presentation.dto.ItemDTO;
import wasteless.server.presentation.mapper.ItemMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ItemQueryController {

    private final ItemQueryService itemService;
    private final ItemMapper itemMapper;

    public ItemQueryController(ItemQueryService itemService, ItemMapper itemMapper) {
        this.itemService = itemService;
        this.itemMapper = itemMapper;
    }

    public List<ItemDTO> getAllItems( Long groceryListId) {
        return itemService.getAllItems(groceryListId)
                .stream()
                .map(itemMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ItemDTO> getItemById(Long groceryListId, Long itemId) throws ResourceNotFoundException {
        Item item = itemService.getItemById(groceryListId,itemId);
        return ResponseEntity.ok().body(itemMapper.convertToDTO(item));
    }
}

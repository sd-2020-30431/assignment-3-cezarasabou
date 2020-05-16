package wasteless.server.presentation.query_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import wasteless.server.business.ExportService;
import wasteless.server.business.WasteManagerService;
import wasteless.server.business.query_service.GroceryListQueryService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.mapper.GroceryListMapper;

import java.util.List;
import java.util.stream.Collectors;


public class GroceryListQueryController {

    private final GroceryListQueryService groceryListService;
    private final GroceryListMapper groceryListMapper;
    private final WasteManagerService wasteManagerService;
    private final ExportService exportService;

    public GroceryListQueryController(GroceryListQueryService groceryListService,
                                 GroceryListMapper groceryListMapper,
                                 WasteManagerService wasteManagerService,
                                 ExportService exportService) {
        this.groceryListService = groceryListService;
        this.groceryListMapper = groceryListMapper;
        this.wasteManagerService = wasteManagerService;
        this.exportService = exportService;
    }

    public List<GroceryListDTO> getAllGroceryLists( Long userId) {
        return groceryListService.getAllGroceryLists(userId)
                .stream()
                .map(groceryListMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<GroceryListDTO> getGroceryListById(Long groceryListId, Long userId)
            throws ResourceNotFoundException {
        GroceryList groceryList = groceryListService.getGroceryListById(userId,groceryListId);
        return ResponseEntity.ok().body(groceryListMapper.convertToDTO(groceryList));
    }
}

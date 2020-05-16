package wasteless.server.presentation.command_controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wasteless.server.business.ExportService;
import wasteless.server.business.WasteManagerService;
import wasteless.server.business.command_service.GroceryListCommandService;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.dto.WasteCalculatorDTO;
import wasteless.server.presentation.mapper.GroceryListMapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

public class GroceryListCommandController {

    private final GroceryListCommandService groceryListService;
    private final GroceryListMapper groceryListMapper;
    private final WasteManagerService wasteManagerService;
    private final ExportService exportService;

    public GroceryListCommandController(GroceryListCommandService groceryListService,
                                 GroceryListMapper groceryListMapper,
                                 WasteManagerService wasteManagerService,
                                 ExportService exportService) {
        this.groceryListService = groceryListService;
        this.groceryListMapper = groceryListMapper;
        this.wasteManagerService = wasteManagerService;
        this.exportService = exportService;
    }

    public GroceryListDTO createGroceryList(Long userId, String groceryListName) {
        GroceryList groceryList = groceryListService.createGroceryList(userId,groceryListName);
        return groceryListMapper.convertToDTO(groceryList);
    }

    //public ceva calculaterates(@PathVariable(value="userId") Long userId,@Valid @RequestBody ceva )

    public WasteCalculatorDTO calculateWaste(Long userId, Long groceryListId, WasteCalculatorDTO wasteCalculatorDTO)
            throws ResourceNotFoundException {

        return wasteManagerService.computeWasteDTO(userId, groceryListId, wasteCalculatorDTO);
    }

    public ResponseEntity<GroceryListDTO> updateGroceryList( Long userId, Long groceryListId, GroceryList groceryListDetails)
            throws ResourceNotFoundException {

        final GroceryList updatedGroceryList =
                groceryListService.updateGroceryList(userId,groceryListId,groceryListDetails);
        return ResponseEntity.ok(groceryListMapper.convertToDTO(updatedGroceryList));
    }

    public Map<String, Boolean> deleteGroceryList( Long groceryListId, Long userId)
            throws ResourceNotFoundException {
        groceryListService.deleteGroceryList(userId,groceryListId);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

    public void generateTextReport( WasteCalculatorDTO wasteCalculatorDTO) {
        exportService.exportTextWasteReport(wasteCalculatorDTO);
    }

    public void generateJsonReport(WasteCalculatorDTO wasteCalculatorDTO){
        exportService.exportJsonWasteReport(wasteCalculatorDTO);
    }
}

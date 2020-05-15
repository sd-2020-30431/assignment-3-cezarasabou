package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.presentation.command_controller.GroceryListCommandController;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.dto.WasteCalculatorDTO;
import wasteless.server.presentation.query_controller.GroceryListQueryController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

public class GroceryListMediator {

    private final GroceryListCommandController groceryListCommandController;
    private final GroceryListQueryController groceryListQueryController;


    public GroceryListMediator(GroceryListCommandController groceryListCommandController, GroceryListQueryController groceryListQueryController) {
        this.groceryListCommandController = groceryListCommandController;
        this.groceryListQueryController = groceryListQueryController;
    }

    @GetMapping("{userId}/groceryLists")
    public List<GroceryListDTO> getAllGroceryLists(@PathVariable(value = "userId") Long userId){
        return groceryListQueryController.getAllGroceryLists(userId);
    }

    @GetMapping("{userId}/groceryList/{id}")
    public ResponseEntity<GroceryListDTO> getGroceryListById(@PathVariable(value = "id") Long groceryListId,
                                                             @PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException{
        return groceryListQueryController.getGroceryListById(groceryListId, userId);
    }

    @PostMapping("{userId}/createGroceryList")
    public GroceryListDTO createGroceryList(@PathVariable(value = "userId") Long userId,
                                            @Valid @RequestBody String groceryListName){
        return groceryListCommandController.createGroceryList(userId, groceryListName);
    }

    @PostMapping("{userId}/{groceryListId}")
    public WasteCalculatorDTO calculateWaste(@PathVariable(value = "userId") Long userId,
                                             @PathVariable(value = "groceryListId") Long groceryListId,
                                             @Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO) throws ResourceNotFoundException{
        return groceryListCommandController.calculateWaste(userId,groceryListId,wasteCalculatorDTO);
    }

    @PutMapping("{userId}/updateGroceryListItem/{id}")
    public ResponseEntity<GroceryListDTO> updateGroceryList(@PathVariable(value = "userId") Long userId,
                                                            @PathVariable(value = "id") Long groceryListId,
                                                            @Valid @RequestBody GroceryList groceryListDetails)
            throws ResourceNotFoundException{
        return groceryListCommandController.updateGroceryList(userId, groceryListId, groceryListDetails);
    }

    @DeleteMapping("{userId}/deleteGroceryList/{id}")
    public Map<String, Boolean> deleteGroceryList(@PathVariable(value = "id") Long groceryListId,
                                                  @PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException{
        return groceryListCommandController.deleteGroceryList(groceryListId, userId);
    }

    @PostMapping("textFile")
    public void generateTextReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO){
        groceryListCommandController.generateTextReport(wasteCalculatorDTO);
    }

    @PostMapping("jsonFile")
    public void generateJsonReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO){
        groceryListCommandController.generateJsonReport(wasteCalculatorDTO);
    }
}

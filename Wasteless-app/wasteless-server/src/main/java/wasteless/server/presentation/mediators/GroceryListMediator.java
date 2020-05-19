package wasteless.server.presentation.mediators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.business.command_handler.GroceryListCommandHandler;
import wasteless.server.presentation.dto.GroceryListDTO;
import wasteless.server.presentation.dto.WasteCalculatorDTO;
import wasteless.server.business.query_handler.GroceryListQueryHandler;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
public class GroceryListMediator {

    private final GroceryListCommandHandler groceryListCommandHandler;
    private final GroceryListQueryHandler groceryListQueryController;


    public GroceryListMediator(GroceryListCommandHandler groceryListCommandHandler,
                               GroceryListQueryHandler groceryListQueryController) {
        this.groceryListCommandHandler = groceryListCommandHandler;
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
        return groceryListCommandHandler.createGroceryList(userId, groceryListName);
    }

    @PostMapping("{userId}/{groceryListId}")
    public WasteCalculatorDTO calculateWaste(@PathVariable(value = "userId") Long userId,
                                             @PathVariable(value = "groceryListId") Long groceryListId,
                                             @Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO) throws ResourceNotFoundException{
        return groceryListCommandHandler.calculateWaste(userId,groceryListId,wasteCalculatorDTO);
    }

    @PutMapping("{userId}/updateGroceryListItem/{id}")
    public ResponseEntity<GroceryListDTO> updateGroceryList(@PathVariable(value = "userId") Long userId,
                                                            @PathVariable(value = "id") Long groceryListId,
                                                            @Valid @RequestBody GroceryList groceryListDetails)
            throws ResourceNotFoundException{
        return groceryListCommandHandler.updateGroceryList(userId, groceryListId, groceryListDetails);
    }

    @DeleteMapping("{userId}/deleteGroceryList/{id}")
    public Map<String, Boolean> deleteGroceryList(@PathVariable(value = "id") Long groceryListId,
                                                  @PathVariable(value = "userId") Long userId)
            throws ResourceNotFoundException{
        return groceryListCommandHandler.deleteGroceryList(groceryListId, userId);
    }

    @PostMapping("textFile")
    public void generateTextReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO){
        groceryListCommandHandler.generateTextReport(wasteCalculatorDTO);
    }

    @PostMapping("jsonFile")
    public void generateJsonReport(@Valid @RequestBody WasteCalculatorDTO wasteCalculatorDTO){
        groceryListCommandHandler.generateJsonReport(wasteCalculatorDTO);
    }
}

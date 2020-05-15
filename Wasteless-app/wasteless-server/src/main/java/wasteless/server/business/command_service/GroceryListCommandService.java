package wasteless.server.business.command_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.User;
import wasteless.server.persistance.GroceryListRepository;
import wasteless.server.persistance.UserRepository;
import wasteless.server.presentation.mapper.GroceryListMapper;

@Service
public class GroceryListCommandService {

    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final GroceryListMapper groceryListMapper;

    @Autowired
    public GroceryListCommandService(GroceryListRepository groceryListRepository, UserRepository userRepository, GroceryListMapper groceryListMapper) {
        this.groceryListRepository = groceryListRepository;
        this.userRepository = userRepository;
        this.groceryListMapper = groceryListMapper;
    }

    public GroceryList createGroceryList(Long userId, String groceryListName){
        GroceryList groceryList = new GroceryList(groceryListName);
        User user = userRepository.getOne(userId);
        groceryList.setUser(user);
        return groceryListRepository.save(groceryList);
    }

    public GroceryList updateGroceryList(Long userId,Long groceryListId, GroceryList groceryListDetails) throws ResourceNotFoundException {

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + groceryListId));


        groceryList.setGroceryListName(groceryListDetails.getGroceryListName());
        groceryList.setGroceryItems(groceryListDetails.getGroceryItems());

        final GroceryList updatedGroceryList = groceryListRepository.save(groceryList);
        return groceryList;
    }

    public void deleteGroceryList(Long userId, Long groceryListId) throws ResourceNotFoundException{

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Grocery list not found for this id :: " + groceryListId));

        groceryListRepository.delete(groceryList);
    }
}

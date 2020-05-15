package wasteless.server.business.query_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.User;
import wasteless.server.persistance.GroceryListRepository;
import wasteless.server.persistance.UserRepository;
import wasteless.server.presentation.mapper.GroceryListMapper;

import java.util.List;

@Service
public class GroceryListQueryService {

    private final GroceryListRepository groceryListRepository;
    private final UserRepository userRepository;
    private final GroceryListMapper groceryListMapper;

    @Autowired
    public GroceryListQueryService(GroceryListRepository groceryListRepository, UserRepository userRepository, GroceryListMapper groceryListMapper) {
        this.groceryListRepository = groceryListRepository;
        this.userRepository = userRepository;
        this.groceryListMapper = groceryListMapper;
    }

    public List<GroceryList> getAllGroceryLists(Long userId) {
        User user = userRepository.getOne(userId);
        return user.getGroceryLists();
    }

    public GroceryList getGroceryListById(Long userId, Long groceryListId) throws ResourceNotFoundException {

        //aici iar nu stiu cum sa caut lista dupa id-ul ei si al userului...
        //adica e functia find by id data de repo, eu nu am acces la ea
        //stiu ca din user pot gasi toate listele userului
        User user = userRepository.getOne(userId);

        GroceryList groceryList = groceryListRepository.findById(groceryListId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + groceryListId));

        return groceryList;
    }
}

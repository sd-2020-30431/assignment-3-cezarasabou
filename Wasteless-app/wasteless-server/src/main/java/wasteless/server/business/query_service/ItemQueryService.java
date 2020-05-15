package wasteless.server.business.query_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wasteless.server.exception.ResourceNotFoundException;
import wasteless.server.model.GroceryList;
import wasteless.server.model.Item;
import wasteless.server.persistance.GroceryListRepository;
import wasteless.server.persistance.ItemRepository;

import java.util.List;

@Service
public class ItemQueryService {

    private final ItemRepository itemRepository;
    private final GroceryListRepository groceryListRepository;

    @Autowired
    public ItemQueryService(ItemRepository itemRepository, GroceryListRepository groceryListRepository) {
        this.itemRepository = itemRepository;
        this.groceryListRepository = groceryListRepository;
    }

    public List<Item> getAllItems(Long groceryListId) {
        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        return groceryList.getGroceryItems();
    }

    public Item getItemById(Long groceryListId, Long itemId) throws ResourceNotFoundException {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found for this id :: " + itemId));
        GroceryList groceryList = groceryListRepository.getOne(groceryListId);
        item.setGroceryList(groceryList);

        return item;
    }
}

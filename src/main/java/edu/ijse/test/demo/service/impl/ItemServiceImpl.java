package edu.ijse.test.demo.service.impl;

import edu.ijse.test.demo.dto.ItemDTO;
import edu.ijse.test.demo.entity.Item;
import edu.ijse.test.demo.repository.ItemRepository;
import edu.ijse.test.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        try {

            log.info("||--------- Save Item Method Executed --------||");

            Item saveItem =  new Item();

            saveItem.setName(itemDTO.getName());
            saveItem.setQuantity(itemDTO.getQuantity());
            saveItem.setPrice(itemDTO.getPrice());

            itemRepository.save(saveItem);

            log.info("||--------- Item Saved Successfully -------||");

        } catch (Exception ex) {
            log.error("Something went wrong while saving item", ex);
        }
    }

    @Override
    public List<ItemDTO> filterItemByName(String itemName) {
        try {

            List<ItemDTO> itemDTOResponseList = new ArrayList<>();

            log.info("||--------- Filter Item By Name Method Executed --------||");

            List<Item> itemList = itemRepository.findItemByName(itemName);

            for (Item item : itemList) {
                ItemDTO itemDTO = new ItemDTO();

                itemDTO.setId(item.getId());
                itemDTO.setName(item.getName());
                itemDTO.setQuantity(item.getQuantity());
                itemDTO.setPrice(item.getPrice());

                itemDTOResponseList.add(itemDTO);
            }

            log.info("||--------- Filter Item By Name Successfully -------||");
            return itemDTOResponseList;

        } catch (Exception ex) {
            log.error("Something went wrong while filtering item", ex);
            throw ex;
        }
    }
}

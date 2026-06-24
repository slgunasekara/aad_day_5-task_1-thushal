package edu.ijse.test.demo.service;

import edu.ijse.test.demo.dto.ItemDTO;

import java.util.List;

public interface ItemService {

    void saveItem (ItemDTO itemDTO);
    List<ItemDTO> filterItemByName(String itemName);
}

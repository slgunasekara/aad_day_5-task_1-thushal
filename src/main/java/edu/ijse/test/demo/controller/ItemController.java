package edu.ijse.test.demo.controller;

import edu.ijse.test.demo.contant.CommonResponse;
import edu.ijse.test.demo.dto.ItemDTO;
import edu.ijse.test.demo.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static edu.ijse.test.demo.contant.ResponseCode.OPERATION_SUCCESS;
import static edu.ijse.test.demo.contant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequestMapping(value = "v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse itemSave (@RequestBody ItemDTO itemDTO) {
        itemService.saveItem(itemDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getItemsByName(@RequestParam(value = "itemName", required = false) String itemName) {
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE, itemService.filterItemByName(itemName));
    }
}

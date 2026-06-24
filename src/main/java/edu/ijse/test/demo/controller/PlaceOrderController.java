package edu.ijse.test.demo.controller;

import edu.ijse.test.demo.contant.CommonResponse;
import edu.ijse.test.demo.dto.PlaceOrderDTO;
import edu.ijse.test.demo.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import static edu.ijse.test.demo.contant.ResponseCode.OPERATION_SUCCESS;
import static edu.ijse.test.demo.contant.ResponseMessage.SUCCESS_MESSAGE;

@RestController
@RequestMapping(value = "v1/placeOrders")
@RequiredArgsConstructor
public class PlaceOrderController {

    private final PlaceOrderService placeOrderService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse placeOrder (@RequestBody PlaceOrderDTO placeOrderDTO) {
        placeOrderService.placeOrder(placeOrderDTO);
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE);
    }

    @GetMapping(value = "/{customerID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getOrdersByCustomerID(@PathVariable Long customerID) {
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE, placeOrderService.getCustomerOrderDetails(customerID));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public CommonResponse getOrdersByCustomerName(@RequestParam(value = "customerName", required = false) String customerName) {
        return new CommonResponse(OPERATION_SUCCESS, SUCCESS_MESSAGE, placeOrderService.getOrderDetailsByCustomerName(customerName));
    }
}

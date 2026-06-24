package edu.ijse.test.demo.service;

import edu.ijse.test.demo.dto.PlaceOrderDTO;
import edu.ijse.test.demo.dto.response.GetOrderDetailsDTO;

import java.util.List;

public interface PlaceOrderService {
    void placeOrder(PlaceOrderDTO placeOrderDTO);
    List<GetOrderDetailsDTO> getCustomerOrderDetails(Long orderId);
    List<GetOrderDetailsDTO> getOrderDetailsByCustomerName(String customerName);
}

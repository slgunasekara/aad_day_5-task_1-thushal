package edu.ijse.test.demo.dto;

import edu.ijse.test.demo.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaceOrderDTO {
    private Long orderId;
    private Long customerId;

    private OrderStatus orderStatus;
    private LocalDateTime orderDate;
    private Double total;
    private List<Long> itemIdList;

    public PlaceOrderDTO(Long customerId, LocalDateTime orderDate, Double total, List<Long> itemIdList, OrderStatus orderStatus) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.total = total;
        this.itemIdList = itemIdList;
        this.orderStatus = orderStatus;
    }
}

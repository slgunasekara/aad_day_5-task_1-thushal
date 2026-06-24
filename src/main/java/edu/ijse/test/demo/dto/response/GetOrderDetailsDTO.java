package edu.ijse.test.demo.dto.response;

import edu.ijse.test.demo.dto.ItemDTO;
import edu.ijse.test.demo.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetOrderDetailsDTO {
    private Long orderId;
    private LocalDateTime orderDate;
    private Double total;

    private OrderStatus status;
    private Long customerId;
    private String customerName;
    List<ItemDTO> itemList;
}

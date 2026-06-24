package edu.ijse.test.demo.service.impl;

import edu.ijse.test.demo.dto.ItemDTO;
import edu.ijse.test.demo.dto.PlaceOrderDTO;
import edu.ijse.test.demo.dto.response.GetOrderDetailsDTO;
import edu.ijse.test.demo.entity.Item;
import edu.ijse.test.demo.entity.Order;
import edu.ijse.test.demo.entity.OrderItem;
import edu.ijse.test.demo.entity.User;
import edu.ijse.test.demo.enumeration.EnumUser;
import edu.ijse.test.demo.repository.ItemRepository;
import edu.ijse.test.demo.repository.OrderItemRepository;
import edu.ijse.test.demo.repository.OrderRepository;
import edu.ijse.test.demo.repository.UserRepository;
import edu.ijse.test.demo.service.PlaceOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceOrderServiceImpl implements PlaceOrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final OrderItemRepository orderItemRepository;

    @Override
    public void placeOrder(PlaceOrderDTO placeOrderDTO) {
        try {
            log.info("||------- Place Order Method Executed ------||");

            Optional<User> optionalUser = userRepository.findById(placeOrderDTO.getCustomerId());

            if (optionalUser.isEmpty())
                throw new Exception("No User Found For That User ID!!!");

            User user = optionalUser.get();

            if (!user.getRole().equals(EnumUser.CUSTOMER)) {
                throw new Exception("No Customer Found For That User ID!!!");
            }

            Order order = new Order();

            order.setDate(placeOrderDTO.getOrderDate());
            order.setStatus(placeOrderDTO.getOrderStatus());
            order.setTotal(placeOrderDTO.getTotal());

            order.setUser(user);

            Order saveOrder = orderRepository.save(order);

            for (Long itemId : placeOrderDTO.getItemIdList()) {

                OrderItem placeOrderItem = new OrderItem();

                placeOrderItem.setOrder(saveOrder);

                Optional<Item> optionalItem = itemRepository.findById(itemId);

                if (optionalItem.isEmpty())
                    throw new Exception("No Item Found For That Item ID!!!");

                log.info("|| ======= Item Found ======= ||");
                Item item = optionalItem.get();

                placeOrderItem.setItem(item);
                placeOrderItem.setUnit_price(item.getPrice());
                placeOrderItem.setQuantity(item.getQuantity());

                orderItemRepository.save(placeOrderItem);
                log.info("|| ======= Order Item Saved Successfully ======= ||");
            }
        } catch (Exception ex) {
            log.error("Error in PlaceOrder Method");
        }
    }

    @Override
    public List<GetOrderDetailsDTO> getCustomerOrderDetails(Long customerId) {
        try {

            log.info("||------- Get Customer Details Method Executed(Passing Customer ID) ------||");

            Optional<User> userOptional = userRepository.findById(customerId);

            if (userOptional.isEmpty())
                throw new RuntimeException("No User Found For That User ID!!!");

            User user = userOptional.get();

            if (!user.getRole().equals(EnumUser.CUSTOMER))
                throw new RuntimeException("No Customer Found For That User ID!!!");

            List<Order> orderList = orderRepository.findOrdersByCustomerID(customerId);

            List<GetOrderDetailsDTO> getOrderDetailsDTOList = new ArrayList<>();

            for (Order order : orderList) {
                GetOrderDetailsDTO getOrderDetailsDTO = new GetOrderDetailsDTO();

                // Order Details -> Order
                getOrderDetailsDTO.setOrderId(order.getId());
                getOrderDetailsDTO.setOrderDate(order.getDate());
                getOrderDetailsDTO.setStatus(order.getStatus());
                getOrderDetailsDTO.setTotal(order.getTotal());

                // Customer Details
                getOrderDetailsDTO.setCustomerId(order.getUser().getId());
                getOrderDetailsDTO.setCustomerName(order.getUser().getName());

                List<ItemDTO> itemDTOList = new ArrayList<>();
                log.info("|| ========= Set Order and Customer Details Successfully To GetOrderDetailsDTO ======= ||");

                for (OrderItem orderItem : order.getOrderItemList()) {

                    ItemDTO itemDTO = new ItemDTO();

                    itemDTO.setId(orderItem.getItem().getId());
                    itemDTO.setName(orderItem.getItem().getName());
                    itemDTO.setPrice(orderItem.getItem().getPrice());
                    itemDTO.setQuantity(orderItem.getItem().getQuantity());

                    itemDTOList.add(itemDTO);
                }

                getOrderDetailsDTO.setItemList(itemDTOList);
                log.info("|| ========= Set Item Details Successfully To GetOrderDetailsDTO ======= ||");
                getOrderDetailsDTOList.add(getOrderDetailsDTO);
            }
            log.info("|| ---- Retrieve Order Details For The Related Customer ");
            return getOrderDetailsDTOList;
        } catch (Exception ex) {
            log.error("Error in Get Order Details Method");
            throw ex;
        }
    }

    @Override
    public List<GetOrderDetailsDTO> getOrderDetailsByCustomerName(String customerName) {
        try {

            log.info("||------- Get Order Details By Customer Name Method Executed ------||");

            // Fetch Orders By Customer Name From DB
            List<Order> ordersByCustomerName = orderRepository.findOrdersByCustomerName(customerName);

            // Map Each Order To GetOrderDetailsDTO Object
            List<GetOrderDetailsDTO> getOrderDetailsList = new ArrayList<>();

            for (Order order : ordersByCustomerName) {

                GetOrderDetailsDTO getOrderDetailsDTO = new GetOrderDetailsDTO();

                // Order Details
                getOrderDetailsDTO.setOrderId(order.getId());
                getOrderDetailsDTO.setOrderDate(order.getDate());
                getOrderDetailsDTO.setStatus(order.getStatus());
                getOrderDetailsDTO.setTotal(order.getTotal());

                // Customer Details
                getOrderDetailsDTO.setCustomerId(order.getUser().getId());
                getOrderDetailsDTO.setCustomerName(order.getUser().getName());

                log.info("||======= Order Details Found ======= ||");

                List<ItemDTO> itemDTOList = new ArrayList<>();

                for (OrderItem orderItem : order.getOrderItemList()) {

                    ItemDTO itemDTO = new ItemDTO();

                    itemDTO.setId(orderItem.getItem().getId());
                    itemDTO.setName(orderItem.getItem().getName());
                    itemDTO.setPrice(orderItem.getItem().getPrice());
                    itemDTO.setQuantity(orderItem.getItem().getQuantity());

                    itemDTOList.add(itemDTO);
                    log.info("||======= Item Found ======= ||");
                }

                getOrderDetailsDTO.setItemList(itemDTOList);
                log.info("||======= Get Order Item Details Successfully ======= ||");

                getOrderDetailsList.add(getOrderDetailsDTO);
            }
            log.info("|| ------ Retrieve Order Details For The Related Customer/Customers ------- ||");
            return getOrderDetailsList;
        } catch (Exception ex) {
            log.error("Error in Get Order Details By Customer Name");
            throw ex;
        }
    }
}

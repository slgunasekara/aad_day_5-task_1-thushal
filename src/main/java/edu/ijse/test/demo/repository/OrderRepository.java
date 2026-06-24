package edu.ijse.test.demo.repository;

import edu.ijse.test.demo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query(value = "SELECT o.* FROM orders o " +
            "JOIN user u ON o.user_id = u.id " +
            "WHERE (u.role = 'CUSTOMER') " +
            "AND (?1 IS NULL OR u.name LIKE %?1%)", nativeQuery = true)
    List<Order> findOrdersByCustomerName(String customerName);

    @Query(value = "SELECT o.* FROM orders o " +
            "JOIN user u ON o.user_id = u.id " +
            "WHERE u.id = ?1 " +
            "AND u.role = 'CUSTOMER'", nativeQuery = true)
    List<Order> findOrdersByCustomerID(Long customerID);

}

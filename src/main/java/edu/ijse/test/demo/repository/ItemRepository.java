package edu.ijse.test.demo.repository;

import edu.ijse.test.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    @Query(value = "SELECT * FROM item WHERE (?1 IS NULL OR name LIKE %?1%)", nativeQuery = true)
    List<Item> findItemByName(String name);
}

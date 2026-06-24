package edu.ijse.test.demo.repository;

import edu.ijse.test.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

package com.example.PhanHuyTri.repository;
import com.example.PhanHuyTri.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
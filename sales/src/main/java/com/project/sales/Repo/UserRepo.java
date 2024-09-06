package com.project.sales.Repo;

import com.project.sales.Entity.User;
import com.project.sales.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);
    User findByRole(UserRole userRole);
}

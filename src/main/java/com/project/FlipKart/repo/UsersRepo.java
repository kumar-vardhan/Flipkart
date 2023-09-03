package com.project.FlipKart.repo;

import com.project.FlipKart.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<Users, Integer> {


    Users findByusername(String username);
}

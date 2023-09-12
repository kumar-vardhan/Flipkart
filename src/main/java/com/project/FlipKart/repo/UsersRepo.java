package com.project.FlipKart.repo;

import com.project.FlipKart.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepo extends JpaRepository<User, Integer> {


}

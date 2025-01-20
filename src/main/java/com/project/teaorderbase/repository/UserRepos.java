package com.project.teaorderbase.repository;

import com.project.teaorderbase.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepos extends JpaRepository<User, Long> {

}
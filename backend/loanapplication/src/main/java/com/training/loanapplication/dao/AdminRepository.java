package com.training.loanapplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.loanapplication.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}

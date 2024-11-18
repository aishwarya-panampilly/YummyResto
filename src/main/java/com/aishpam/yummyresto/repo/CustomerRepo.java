package com.aishpam.yummyresto.repo;

import com.aishpam.yummyresto.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

}

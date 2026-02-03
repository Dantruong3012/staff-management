package com.dantruong.staffmanagement.config;

import com.dantruong.staffmanagement.entity.Staff;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffReposi extends JpaRepository<Staff, Integer> {
}

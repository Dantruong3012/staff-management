package com.dantruong.staffmanagement.service;

import com.dantruong.staffmanagement.config.StaffReposi;
import com.dantruong.staffmanagement.entity.Staff;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

@Service
public class StaffService {
    private final StaffReposi staffReposi;

    public StaffService(StaffReposi staffReposi) {
        this.staffReposi = staffReposi;
    }

    @Transactional(rollbackFor = Exception.class)
    public String insertStaff(Staff staff){
        staffReposi.save(staff);
        return "Thêm nhân viên thành công";
    }

    @Transactional(rollbackFor = Exception.class)
    public String updateStaff(Integer id, Staff staff){
        Staff oldStaff = staffReposi.findById(id).orElseThrow(() -> new RuntimeException("Không thể tìm thấy nhân viên này!"));
        if (staff.getName() != null && !staff.getName().isEmpty()){oldStaff.setName(staff.getName());}
        if (staff.getEmail() != null && !staff.getEmail().isEmpty()){oldStaff.setEmail(staff.getEmail());}
        if (staff.getPosition() != null && !staff.getPosition().isEmpty()){oldStaff.setPosition(staff.getPosition());}
        if (staff.getPicture() != null && !staff.getPicture().isEmpty()){ oldStaff.setPicture(staff.getPicture()); }
        staffReposi.save(oldStaff);
        return "Đã sửa thông tin thành công";
    }

    public void delete(Integer id){
        staffReposi.deleteById(id);
    }
}

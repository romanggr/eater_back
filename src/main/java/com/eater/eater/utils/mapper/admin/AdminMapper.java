package com.eater.eater.utils.mapper.admin;

import com.eater.eater.dto.admin.*;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdminMapper {


    public AdminDTO toDTO(Admin admin) {
        if (admin == null) return null;

        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setId(admin.getId());
        adminDTO.setAccepted(admin.isAccepted());
        adminDTO.setName(admin.getName());
        adminDTO.setPhone(admin.getPhone());
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setRole(admin.getRole());

        return adminDTO;
    }

    public Admin updateRequestToEntity(UpdateAdminRequest request, Admin currentAdmin) {
        if (request == null || currentAdmin == null) return null;

        currentAdmin.setEmail(request.getEmail());
        currentAdmin.setPhone(request.getPhone());
        currentAdmin.setName(request.getName());

        return currentAdmin;
    }

}

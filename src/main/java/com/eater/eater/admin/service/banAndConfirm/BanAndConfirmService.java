package com.eater.eater.admin.service.banAndConfirm;

import com.eater.eater.admin.dto.AdminDTO;
import com.eater.eater.admin.dto.BanRequest;
import com.eater.eater.courier.dto.CourierDTO;

import java.util.List;

public interface BanAndConfirmService {
    CourierDTO confirmCourier(Long id);

    AdminDTO confirmAdmin(Long id);

    Long banCourier(BanRequest request);

    Long banClient(BanRequest request);

    Long banRestaurantOwner(BanRequest request);

    Long deleteAdmin(Long id);

    List<AdminDTO> getUnconfirmedAdmins();

}

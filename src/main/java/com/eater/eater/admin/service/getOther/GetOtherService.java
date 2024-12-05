package com.eater.eater.admin.service.getOther;

import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;
import com.eater.eater.admin.dto.ClientsForAdminDTO;
import com.eater.eater.admin.dto.CouriersForAdminDTO;
import com.eater.eater.admin.dto.RestaurantOwnersForAdminDTO;

import java.util.List;

public interface GetOtherService {
    List<CouriersForAdminDTO> getCouriers();

    List<ClientsForAdminDTO> getClients();

    List<RestaurantOwnersForAdminDTO> getRestaurantOwners();

    CourierDTO getCourierById(Long id);

    ClientDTO getClientById(Long id);

    RestaurantOwnerDTO getRestaurantOwnerById(Long id);
}

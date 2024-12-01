package com.eater.eater.service.admin;

import com.eater.eater.dto.admin.*;
import com.eater.eater.dto.auth.BanRequest;
import com.eater.eater.dto.client.ClientDTO;
import com.eater.eater.dto.courier.CourierDTO;
import com.eater.eater.dto.orders.OrderDTOAdmin;
import com.eater.eater.dto.restaurantOwner.RestaurantOwnerDTO;
import com.eater.eater.dto.statistic.StatisticDTO;

import java.util.List;

public interface AdminService {

    AdminDTO getAdmin();

    List<CouriersForAdminDTO> getCouriers();

    List<ClientsForAdminDTO> getClients();

    List<RestaurantOwnersForAdminDTO> getRestaurantOwners();

    CourierDTO getCourierById(Long id);

    ClientDTO getClientById(Long id);

    RestaurantOwnerDTO getRestaurantOwnerById(Long id);

    CourierDTO confirmCourier(Long id);

    AdminDTO confirmAdmin(Long id);

    Long banCourier(BanRequest request);

    Long banClient(BanRequest request);

    Long banRestaurantOwner(BanRequest request);

    Long deleteAdmin(Long id);

    List<AdminDTO> getUnconfirmedAdmins();

    List<OrderDTOAdmin> getOrders();

    OrderDTOAdmin getOrder(Long id);

    StatisticDTO getStatistics();
}

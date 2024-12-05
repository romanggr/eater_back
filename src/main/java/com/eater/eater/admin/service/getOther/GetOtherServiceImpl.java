package com.eater.eater.admin.service.getOther;

import com.eater.eater.admin.dto.ClientsForAdminDTO;
import com.eater.eater.admin.dto.CouriersForAdminDTO;
import com.eater.eater.admin.dto.RestaurantOwnersForAdminDTO;
import com.eater.eater.admin.repository.AdminRepository;
import com.eater.eater.client.dto.ClientDTO;
import com.eater.eater.courier.dto.CourierDTO;
import com.eater.eater.restaurantOwner.dto.RestaurantOwnerDTO;

import com.eater.eater.client.model.Client;
import com.eater.eater.courier.model.Courier;
import com.eater.eater.restaurantOwner.model.RestaurantOwner;
import com.eater.eater.client.repository.ClientRepository;
import com.eater.eater.courier.repository.CourierRepository;
import com.eater.eater.restaurantOwner.repository.RestaurantOwnerRepository;
import com.eater.eater.service.fakeData.FakeDataService;
import com.eater.eater.client.utils.ClientMapper;
import com.eater.eater.courier.utils.CourierMapper;
import com.eater.eater.restaurantOwner.utils.RestaurantOwnerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetOtherServiceImpl implements GetOtherService {
    private final CourierRepository courierRepository;
    private final ClientRepository clientRepository;
    private final RestaurantOwnerRepository restaurantOwnerRepository;


    public List<CouriersForAdminDTO> getCouriers() {
        List<Courier> couriers = courierRepository.findAll();
        return CourierMapper.allCourierToDTO(couriers);
    }

    public List<ClientsForAdminDTO> getClients() {
        List<Client> clients = clientRepository.findAll();
        return ClientMapper.allClientToDTO(clients);
    }

    public List<RestaurantOwnersForAdminDTO> getRestaurantOwners() {
        List<RestaurantOwner> restaurantOwners = restaurantOwnerRepository.findAll();
        return RestaurantOwnerMapper.allRestaurantOwnerToDTO(restaurantOwners);
    }


    public CourierDTO getCourierById(Long id) {
        Courier courier = courierRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return CourierMapper.toDTO(courier);
    }

    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return ClientMapper.toDTO(client);
    }

    public RestaurantOwnerDTO getRestaurantOwnerById(Long id) {
        RestaurantOwner restaurantOwner = restaurantOwnerRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Cannot find user with id " + id));
        return RestaurantOwnerMapper.toDTO(restaurantOwner);
    }

}

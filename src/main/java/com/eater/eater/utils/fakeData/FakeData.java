package com.eater.eater.utils.fakeData;

import com.eater.eater.enums.*;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.courier.CourierCoordinates;
import com.eater.eater.model.restaurantOwner.Restaurant;
import com.eater.eater.model.restaurantOwner.RestaurantDish;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FakeData {
    private final Faker faker;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<Courier> getFakeCouriers(int count) {
        List<Courier> couriers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Courier courier = new Courier();
            courier.setEmail(faker.internet().emailAddress());
            courier.setPhone(faker.phoneNumber().phoneNumber());
            courier.setName(faker.name().fullName());
            courier.setPassword(bCryptPasswordEncoder.encode("123456Qwer"));
            courier.setRole(Role.COURIER);
            courier.setAvatarUrl(faker.internet().avatar());
            courier.setTransportType(TransportType.values()[faker.random().nextInt(TransportType.values().length)]);
            courier.setCourierStatus(CourierStatus.AVAILABLE);

            CourierCoordinates courierCoordinates = new CourierCoordinates();
//            courierCoordinates.setLongitude(faker.number().randomDouble(6, -180, 180));
//            courierCoordinates.setLatitude(faker.number().randomDouble(6, -90, 90));
            courierCoordinates.setLongitude(24.005269);
            courierCoordinates.setLatitude(49.837581);
            courierCoordinates.setLastUpdate(LocalDateTime.now());

            courierCoordinates.setCourier(courier);
            courier.setCoordinates(courierCoordinates);
            couriers.add(courier);
        }
        return couriers;
    }

    public List<Admin> generateFakeAdmins(int count) {
        List<Admin> admins = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Admin admin = new Admin();
            admin.setEmail(faker.internet().emailAddress());
            admin.setPhone(faker.phoneNumber().cellPhone());
            admin.setName(faker.name().fullName());
            admin.setPassword(bCryptPasswordEncoder.encode("123456Qwer"));
            admin.setRole(Role.ADMIN);
            admin.setAccepted(true);

            admins.add(admin);
        }

        return admins;
    }

    public List<Client> generateFakeClients(int count) {
        List<Client> clients = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Client client = new Client();
            client.setEmail(faker.internet().emailAddress());
            client.setPhone(faker.phoneNumber().cellPhone());
            client.setName(faker.name().fullName());
            client.setPassword(bCryptPasswordEncoder.encode("123456Qwer"));
            client.setAddress(faker.address().fullAddress());
//            client.setLatitude(faker.number().randomDouble(6, -90, 90));
//            client.setLongitude(faker.number().randomDouble(6, -180, 180));
            client.setLatitude(49.751819);
            client.setLongitude(24.141594);
            client.setAvatarUrl(faker.internet().avatar());
            client.setClientStatus(ClientStatus.ACTIVE);
            client.setRole(Role.CLIENT);

            clients.add(client);
        }

        return clients;
    }


    public List<RestaurantOwner> generateFakeRestaurantOwners(int count) {
        List<RestaurantOwner> restaurantOwners = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            RestaurantOwner owner = new RestaurantOwner();
            owner.setEmail(faker.internet().emailAddress());
            owner.setPhone(faker.phoneNumber().cellPhone());
            owner.setName(faker.name().fullName());
            owner.setPassword(bCryptPasswordEncoder.encode("123456Qwer"));
            owner.setRestaurantOwnerStatus(RestaurantOwnerStatus.ACTIVE);
            owner.setRole(Role.RESTAURANT_OWNER);

            Restaurant restaurant = new Restaurant();
            restaurant.setName(faker.company().name());
            restaurant.setDescription(faker.lorem().paragraph());
            restaurant.setAvatarUrl(faker.internet().image());
            restaurant.setAddress(faker.address().fullAddress());
//            restaurant.setLatitude(faker.number().randomDouble(6, -90, 90));
//            restaurant.setLongitude(faker.number().randomDouble(6, -180, 180));
            restaurant.setLongitude(24.141594);
            restaurant.setLatitude(49.751819);
            restaurant.setIsOpenFrom(LocalTime.of(faker.number().numberBetween(6, 10), 0));
            restaurant.setIsOpenTo(LocalTime.of(faker.number().numberBetween(18, 22), 0));

            RestaurantDish restaurantDish1 = new RestaurantDish();
            restaurantDish1.setName(faker.food().dish());
            restaurantDish1.setDescription(faker.lorem().sentence());
            restaurantDish1.setPrice(faker.number().randomDouble(2, 5, 50));
            restaurantDish1.setWeight(faker.number().randomDouble(1, 100, 500));
            restaurantDish1.setImageUrl(faker.internet().image());
            restaurantDish1.setTimeOfCooking(faker.number().numberBetween(10, 60));
            restaurantDish1.setRestaurant(restaurant);

            RestaurantDish restaurantDish2 = new RestaurantDish();
            restaurantDish2.setName(faker.food().dish());
            restaurantDish2.setDescription(faker.lorem().sentence());
            restaurantDish2.setPrice(faker.number().randomDouble(2, 5, 50));
            restaurantDish2.setWeight(faker.number().randomDouble(1, 100, 500));
            restaurantDish2.setImageUrl(faker.internet().image());
            restaurantDish2.setTimeOfCooking(faker.number().numberBetween(10, 60));
            restaurantDish2.setRestaurant(restaurant);

            restaurant.setRestaurantOwner(owner);
            owner.setRestaurant(restaurant);
            restaurant.setRestaurantDishes(List.of(restaurantDish1, restaurantDish2));
            restaurantOwners.add(owner);
        }

        return restaurantOwners;
    }
}

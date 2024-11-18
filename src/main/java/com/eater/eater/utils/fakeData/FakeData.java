package com.eater.eater.utils.fakeData;

import com.eater.eater.enums.*;
import com.eater.eater.model.admin.Admin;
import com.eater.eater.model.client.Client;
import com.eater.eater.model.courier.Courier;
import com.eater.eater.model.restaurantOwner.RestaurantOwner;
import com.github.javafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeData {
    Faker faker = new Faker();

    public List<Courier> getFakeCouriers(int count) {
        List<Courier> couriers = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            Courier courier = new Courier();
            courier.setEmail(faker.internet().emailAddress());
            courier.setPhone(faker.phoneNumber().phoneNumber());
            courier.setName(faker.name().fullName());
            courier.setPassword(faker.internet().password());
            courier.setRole(Role.COURIER);
            courier.setAvatarUrl(faker.internet().avatar());
            courier.setTransportType(TransportType.values()[faker.random().nextInt(TransportType.values().length)]);
            courier.setCourierStatus(CourierStatus.OFF_DUTY);

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
            admin.setPassword(faker.internet().password());
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
            client.setPassword(faker.internet().password());
            client.setAddress(faker.address().fullAddress());
            client.setLatitude(faker.number().randomDouble(6, -90, 90));
            client.setLongitude(faker.number().randomDouble(6, -180, 180));
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
            owner.setPassword(faker.internet().password());
            owner.setRestaurantOwnerStatus(RestaurantOwnerStatus.ACTIVE);
            owner.setRole(Role.RESTAURANT_OWNER);

            restaurantOwners.add(owner);
        }

        return restaurantOwners;
    }
}

package com.eater.eater.service.client;

import org.springframework.stereotype.Service;

@Service
public class ClientService {
//    private final ClientMapper clientMapper;
//    private final CourierCoordinatesMapper courierCoordinatesMapper;
//    private final CourierRatingMapper courierRatingMapper;
//    private final ClientRepository clientRepository;
//    private final CourierRepository courierRepository;
//    private final CourierRatingRepository courierRatingRepository;
//
//    @Autowired
//    public ClientService(ClientMapper clientMapper, CourierCoordinatesMapper courierCoordinatesMapper, CourierRatingMapper courierRatingMapper, ClientRepository clientRepository, CourierRepository courierRepository, CourierRatingRepository courierRatingRepository) {
//        this.clientMapper = clientMapper;
//        this.courierCoordinatesMapper = courierCoordinatesMapper;
//        this.courierRatingMapper = courierRatingMapper;
//        this.clientRepository = clientRepository;
//        this.courierRepository = courierRepository;
//        this.courierRatingRepository = courierRatingRepository;
//    }
//
//
//    //  Get client data
//    public ClientDTO getClient() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Client currentUser = (Client) authentication.getPrincipal();
//        return clientMapper.toDTO(currentUser);
//    }
//
//    // Update user data
//    public ClientDTO updateClient(UpdateClientRequest updateClientRequest) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Client currentUser = (Client) authentication.getPrincipal();
//        Long currentUserId = currentUser.getId();
//
//        Client currentClient = clientRepository.findById(currentUserId).orElseThrow(
//                () -> new EntityNotFoundException("Client not found"));
//        Client response = clientRepository.save(clientMapper.toEntity(updateClientRequest, currentClient));
//
//        return clientMapper.toDTO(response);
//    }
//
//    // Get courier coordinates
//    public CourierCoordinatesDTO getCourierCoordinates(Long courierId) {
//        CourierCoordinates courierCoordinates = courierRepository.findCourierCoordinatesByCourierId(courierId).orElseThrow(
//                () -> new EntityNotFoundException("Courier with id: " + courierId + " not found"));
//
//        return courierCoordinatesMapper.toDTO(courierCoordinates);
//    }
//
//    // Give courier rating
//    public CourierRating setCourierRating(CourierRatingDTO courierRatingDTO) {
//        Courier courier = courierRepository.findById(courierRatingDTO.getCourierId())
//                .orElseThrow(() -> new EntityNotFoundException("Courier with id: " + courierRatingDTO.getCourierId() + " not found"));
//
//        Client client = clientRepository.findById(courierRatingDTO.getClientId())
//                .orElseThrow(() -> new EntityNotFoundException("Client with id: " + courierRatingDTO.getClientId() + " not found"));
//
//
//        CourierRating courierRating = courierRatingMapper.toEntity(courierRatingDTO, courier, client);
//
//        return courierRatingRepository.save(courierRating);
//    }


//todo
// Create order
// Confirm order getting
// Get order
// Get orders history

}

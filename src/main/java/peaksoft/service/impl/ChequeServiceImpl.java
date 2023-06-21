package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.WaiterRequest;
import peaksoft.dto.WaiterResponseOfDay;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;
import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.ChequeRepository;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.ChequeService;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChequeServiceImpl implements ChequeService {
    private final ChequeRepository chequeRepository;
    private final UserRepository userRepository;
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public SimpleResponse saveCheque(ChequeRequest request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User email: %s not found".formatted(email)));
        List<MenuItem> menuItems = new ArrayList<>();
        Cheque cheque = Cheque
                .builder()
                .user(user)
                .menuItems(menuItems)
                .createdAt(LocalDate.from(ZonedDateTime.now()))
                .build();
        chequeRepository.save(cheque);
        for (Long l : request.getMenuItems()) {
            MenuItem menuItem = menuItemRepository.findById(l).orElseThrow(() -> new NotFoundException("MenuItem with id: %s not found".formatted(l)));
            menuItems.add(menuItem);
            cheque.setPriceAverage(menuItem.getPrice());
        }
        return SimpleResponse
                .builder()
                .status(HttpStatus.OK)
                .message("Cheque successfully saved")
                .build();
    }




    @Override
    public ChequeResponse getByIdCheque(Long id) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cheque with id:%s is not present", id)));
        Restaurant restaurant = restaurantRepository.findById(1L).orElseThrow(() -> new NotFoundException(String.format("Restaurant with id:%s is not present", 1L)));
        return ChequeResponse.builder()
                .id(cheque.getId())
                .fullName(cheque.getUser().getFirstName() + " " + cheque.getUser().getLastName())
                .itemResponseList(cheque.getMenuItems())
                .services(restaurant.getService())
                .avaPrice(cheque.getPriceAverage())
                .grandTotal((int) (cheque.getPriceAverage() * 0.15))
                .build();
    }

    @Override
    public SimpleResponse update(Long id, ChequeRequest request) {
        Cheque cheque = chequeRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Cheque with id: " + id + "is not found")));
        cheque.setPriceAverage(request.getPriceAverage());
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Успешно")
                .build();
    }

    @Override
    public SimpleResponse delete(Long id) {
        if (chequeRepository.existsById(id)) {
            chequeRepository.deleteById(id);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Успешно")
                    .build();
        }else {
            throw new NotFoundException(String.format("Cheque with id: " + id + "is not found"));
        }
    }

    @Override  public ChequeResponse totalDaily(Long id) {
        return chequeRepository.sumAv(id).orElseThrow(()->new NotFoundException(String.format("Cheque with id: " + id + "is not found")));
    }

    @Override
    public WaiterResponseOfDay totalPriceWalter(WaiterRequest waiterRequest) {


        return null;
    }
}


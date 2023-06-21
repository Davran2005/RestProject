package peaksoft.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.ChequePaginationResponse;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.dto.stopList.StopListPaginationResponse;
import peaksoft.dto.stopList.StopListRequest;
import peaksoft.dto.stopList.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exception.AlreadyExistException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StopListServiceImpl  implements StopListService {
    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    @Override
    public StopListPaginationResponse getAllStopLists(int size, int page) {
        Pageable pageable = PageRequest.of(page-1, size, Sort.by("name"));
        Page<StopListResponse> allStopList = stopListRepository.getAllStopLists(pageable);
        return StopListPaginationResponse.builder()
                .stopListResponses(allStopList.getContent())
                .currentPage(allStopList.getNumber()+1)
                .pageSize(allStopList.getTotalPages())
                .build();
    }

    @Override
    public SimpleResponse saveStopList(Long menuItemId, StopListRequest stopListRequest) {
        MenuItem menu = menuItemRepository.findById(menuItemId).orElseThrow(
                () -> new NullPointerException("Menu with id: "));
        StopList stopList=new StopList();
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(stopListRequest.getDate());
        stopList.setMenuItem(menu);
        menu.setStopList(stopList);
        stopListRepository.save(stopList);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Успешно").build();
    }

    @Override
    public SimpleResponse updateStopList(Long id, StopListRequest stopListRequest) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Stop list with id: " + id + "is not found")));
        stopList.setReason(stopListRequest.getReason());
        stopList.setDate(stopListRequest.getDate());
        stopListRepository.save(stopList);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("Successfully updated")
                .build();
    }

    @Override
    public StopListResponse getStopListById(Long id) {
        StopList stopList = stopListRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("Stop list with id: " + id + "is not found")));
        return StopListResponse.builder()
                .id(stopList.getId())
                .reason(stopList.getReason())
                .date(stopList.getDate())
                .build();
    }

    @Override
    public SimpleResponse deleteStopListById(Long id) {
        if (stopListRepository.existsById(id)){
            stopListRepository.deleteById(id);
            return SimpleResponse.builder()
                    .status(HttpStatus.OK)
                    .message("successfully deleted")
                    .build();
        }else{
            throw new NotFoundException(String.format("Stop list with id:%s is not exist", id));
        }
    }
}

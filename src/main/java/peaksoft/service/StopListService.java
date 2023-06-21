package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.stopList.StopListPaginationResponse;
import peaksoft.dto.stopList.StopListRequest;
import peaksoft.dto.stopList.StopListResponse;
import peaksoft.entity.StopList;

import java.util.List;

public interface StopListService {
    StopListPaginationResponse getAllStopLists(int size, int page);
    SimpleResponse saveStopList(Long menuItemId, StopListRequest stopListRequest);
    SimpleResponse updateStopList(Long id, StopListRequest stopListRequest);
    StopListResponse getStopListById(Long id);
    SimpleResponse deleteStopListById(Long id);
}

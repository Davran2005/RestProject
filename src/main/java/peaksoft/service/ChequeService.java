package peaksoft.service;

import peaksoft.dto.SimpleResponse;
import peaksoft.dto.WaiterRequest;
import peaksoft.dto.WaiterResponseOfDay;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;

public interface ChequeService {
    SimpleResponse saveCheque(ChequeRequest request);
    ChequeResponse getByIdCheque(Long id);
    SimpleResponse update(Long id,ChequeRequest request);
    SimpleResponse delete(Long id);
    ChequeResponse totalDaily(Long id);
    WaiterResponseOfDay totalPriceWalter(WaiterRequest waiterRequest);

}

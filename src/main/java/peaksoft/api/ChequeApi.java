package peaksoft.api;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.cheque.ChequeRequest;
import peaksoft.dto.cheque.ChequeResponse;
import peaksoft.service.ChequeService;


@RestController
@RequestMapping("/api/restaurant/cheque")
@RequiredArgsConstructor
public class ChequeApi {
    private final ChequeService chequeServices;
    @PostMapping("/save")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER')")
    @Operation(summary = "token",description = "save")
    public SimpleResponse save(@RequestBody ChequeRequest request){
        return chequeServices.saveCheque(request);
    }

    @PostMapping("/get/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    @Operation(summary = "token",description = "get by id")
    public ChequeResponse getById(@PathVariable Long id){
        return chequeServices.getByIdCheque(id);
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "token",description = "update")
    public SimpleResponse update(@PathVariable Long id,@RequestBody ChequeRequest request){
        return chequeServices.update(id, request);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "token",description = "delete")
    public SimpleResponse delete(@PathVariable Long id){
        return chequeServices.delete(id);
    }
@GetMapping("/total/{id}")
    public ChequeResponse totalDaily(@PathVariable Long id){
        return chequeServices.totalDaily(id);
    }
}

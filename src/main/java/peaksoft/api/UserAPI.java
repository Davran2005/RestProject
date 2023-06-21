package peaksoft.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.UserPaginationResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;
import peaksoft.service.UserService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserAPI {
    private final UserService service;
    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse save(@Valid @RequestBody UserRequest userRequest) {
        return service.saveUser(userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    @GetMapping("/page")
    UserPaginationResponse getAllUsers(@RequestParam int pageSize, int currentPage){
        return service.getAllUser(currentPage,pageSize);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/ass/{userId}/{restaurantId}")
    public  SimpleResponse Ass(@PathVariable Long userId, @PathVariable Long restaurantId,  @Param("word") String word){
        return service.assign(userId, restaurantId, word);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public SimpleResponse updateUser(@PathVariable Long id,@RequestBody UserRequest userRequest){
        return service.updateUser(id, userRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    @GetMapping("{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','WAITER','CHEF')")
    @DeleteMapping("{id}")
    public SimpleResponse deleteUserById(@PathVariable Long id){
        return service.deleteUser(id);
    }

}

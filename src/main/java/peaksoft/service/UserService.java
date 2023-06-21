package peaksoft.service;



import peaksoft.dto.SimpleResponse;
import peaksoft.dto.user.UserPaginationResponse;
import peaksoft.dto.user.UserRequest;
import peaksoft.dto.user.UserResponse;


public interface UserService {
    SimpleResponse saveUser(UserRequest userRequest);
    UserResponse getUserById(Long id);
   UserPaginationResponse getAllUser(int size, int page);
    SimpleResponse deleteUser(Long id);
    SimpleResponse assign(Long userId,Long resId, String word);
    SimpleResponse updateUser(Long id, UserRequest userRequest);
}

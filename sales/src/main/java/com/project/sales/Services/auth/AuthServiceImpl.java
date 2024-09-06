package com.project.sales.Services.auth;

import com.project.sales.Dto.SingupRequest;
import com.project.sales.Dto.UserDto;
import com.project.sales.Entity.Order;
import com.project.sales.Entity.User;
import com.project.sales.Repo.OrderRepo;
import com.project.sales.Repo.UserRepo;
import com.project.sales.enums.OrderStatus;
import com.project.sales.enums.UserRole;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private OrderRepo orderRepo;

    public UserDto createUser(SingupRequest singupRequest){

        User user = new User();
        user.setName(singupRequest.getName());
        user.setEmail(singupRequest.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(singupRequest.getPassword()));
        user.setRole(UserRole.CUSTOMER);
        User createdUser = userRepo.save(user);

        Order order = new Order();
        order.setAmount(0L);
        order.setTotalAmount(0L);
        order.setDiscount(0L);
        order.setUser(createdUser);
        order.setOrderStatus(OrderStatus.Pending);
        orderRepo.save(order);



        UserDto userDto = new UserDto();
        userDto.setId(createdUser.getId());
        return userDto;

    }
public Boolean hasUserWithEmail(String email){
        return userRepo.findFirstByEmail(email).isPresent();
}
@PostConstruct
public void createAdminAccount(){
        User adminAccount = userRepo.findByRole(UserRole.ADMIN);
        if(null == adminAccount){
            User user = new User();
            user.setEmail("admin@test.com");
            user.setName("admin");
            user.setPassword(new BCryptPasswordEncoder().encode("admin"));
            userRepo.save(user);
        }
}

}

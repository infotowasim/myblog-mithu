package com.myblog_mithu.security;

import com.myblog_mithu.entity.Role;
import com.myblog_mithu.entity.User;
import com.myblog_mithu.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


// 1) I order to fetch the data from the database in user table , U should create another layer or class CustomUserDetailsService inside security package.
// 2) U require the class CustomUserDetailsService and make this class implements UserDetailsService.
// 3) This is the class that will help me to fetch the username and password from the database.
// 4) I will be Implemented method loadUserByUsername(String username) - This method will automatically get the username.
// 5) 1st username is coming to [ authenticateUser(@RequestBody LoginDTO loginDTO) ] LoginDTO loginDTO from here the username has to be given [ loadUserByUsername(String username) ] String username then
// 6) Then It will take the username go to the database and fetch the record.
// 7) U cant directly call CustomUserDetailsService create an object and call this (loadUserByUsername) NO its not a audinary method to call like that.
// 8) If U think we ll go to AuthController class and I will write....   CustomUserDetailsService user = new CustomUserDetailsService(); and then u call loadUserByUsername and supply username to it NO it will not the way


// 1) // 8) I will go back to this class CustomUserDetailsService
// 2) 1st I will create the UserRepository. inside this we have create this Optional<User> findByUsernameOrEmail(String username, String email); - it means search the record based on username , email
// 3) How to search - userRepository.findByUsernameOrEmail(username,username)


// 1) Use on building class User which is from spring security user class. I will supply here email,password and the role
// 2) Because based on the User(entity) Details now I can also get the role. Because the user class has setRole

//********************************** SIGN-IN*******************************************

@Service

public class CustomUserDetailsService implements UserDetailsService {


    // u should create
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }




    // UserDetails what does this class - this class responsible for take the username go to the database , get the user object if record found, and that user object record saved
    // it in User object of spring security in the loadByUsername set that into and leave it.

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // USERNAME AND PASSWORD COMING FROM THE DATABASE.
        User user = userRepository.findByUsernameOrEmail(username, username).orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND WITH USERNAME OR EMAIL:" + username));


        // 1) Use on building class User which is from spring security user class. I will supply here email,password and the role
        // 2) Because based on the User(entity) Details now I can also get the role. Because the user class has setRole

        org.springframework.security.core.userdetails.User user1 = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
        return user1;
    }

    // I need to develop the method  mapRolesToAuthorities() - it will iterate the SET and give the role.
    // This code does like - I am simply supply the Set<Role> roles and that will return back the content. which the userdetails.User can take it.
    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        return simpleGrantedAuthorityList;
    }


}



// ************************************************ Project Explanation ********************************************
// I will give the username , it will go to the database, it will get the user object , that user object u further supply to User class constructor.
// So, When I create an object of User supply the email,password to it and the job is done. this is the record from the database.
// I am setting in User(spring) object. So what ever username and password that got from the database now we r setting up on the User(spring) object.

// So, NOW LOOK AT THE FLOW
// UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()) this user name it will give it loadUserByUsername(String username) , loadUserByUsername will go to the database and get the record, and that the record that is getting from the database
// It will set that User object of spring security, so now the data should come from the db ready.
// But now the comparison should happen with the data present in LoginDTO.

// Next Part go to security config file

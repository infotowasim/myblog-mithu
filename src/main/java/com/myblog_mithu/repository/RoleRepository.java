package com.myblog_mithu.repository;


// for Developing Sign Up feature


import com.myblog_mithu.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role,Long> {

    // if u give the Role name then it should return the optional object of role.
    // if I give Admin then it ll return the object for Admin.
    // if u give user then it will return optional object for User.
    Optional<Role> findByName(String  name);
}

package com.assessment.assessment.controller;

import com.assessment.assessment.model.User;
import com.assessment.assessment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * obtener la lista de todos los usuarios
     * @return lista de los usuarios
     */
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUser();
    }

    /**
     * actualizacion de los datos de usuario
     * @param user datos actualizados del usuario
     * @return actualizacion completa del usuario
     */
    @PutMapping
    public User UpdateUser(@RequestBody User user){
        return userService.update(user);
    }

    /**
     * eliminar a un usuario
     * @param id id del usuario aeliminar
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteById(id);
    }

}

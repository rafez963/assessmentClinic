package com.assessment.assessment.service;

import com.assessment.assessment.model.User;
import com.assessment.assessment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @param email email del usuario
     * @return el usuario encontrado por su email,existe.
     */
    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    /**
     * guardar un usuario en la base  de datos,
     * dado el caso que le usuario existe, mandar un excepcion
     * @param user el usuario  aguardar
     * @return el usuario guardado exitosamente
     */
    public User save(User user){
        //Verificacion si existe un usurio con el mismo email
        if (userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("A user with this email alredy exists.");
        }

        //Encriptar la contraseña antes de aguardar
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    /**
     * actualizar un usuario existente
     * @param user el usuario que se va a actualizar
     * @return el usuario actualizado
     */
    public User update(User user){
        // verificacion de la existencia del usuario
        User existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new IllegalArgumentException("User not fount"));

        // actualizar solo los campos permitidos
        existingUser.setUsername(user.getUsername());
        existingUser.setRole(user.getRole());

        return userRepository.save(existingUser);
    }

    /**
     * obtiene todos los usuarios registrados en la aplicacion
     * @return lista de todos los usurios
     */
    public List<User> getAllUser(){
        return userRepository.findAll();
    }


    public void deleteById(Long id){
        //verificacion de usuario existente
        if(!userRepository.existsById(id)){
            throw new IllegalArgumentException("User not found with id : "+ id);
        }
        userRepository.deleteById(id);
    }

}

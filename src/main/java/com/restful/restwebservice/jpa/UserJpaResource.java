package com.restful.restwebservice.jpa;

import com.restful.restwebservice.user.User;
import com.restful.restwebservice.user.UserDaoService;
import com.restful.restwebservice.user.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserRepository repository;
    public UserJpaResource(UserRepository repository) {
        this.repository = repository;
    }

    //GET /users
    @GetMapping("/jpa/users")
    public List<User>  retrieveAllUser() {
        return repository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUserById(@PathVariable int id) {
        Optional<User> user = repository.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("id:" + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user.get());

        // Build link of retrieveAllUsers() method.
        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUser());

        // Adding the link to the User Entity Model.
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    //GET /user/{1}
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }


    //POST /users
    @PostMapping("/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {
        User savedUser = repository.save(user);

        //return ResponseEntity.created(null).build();

        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();

        // returning the URL of the created user
        // location - /users/4
        return ResponseEntity.created(location).build();
    }
}

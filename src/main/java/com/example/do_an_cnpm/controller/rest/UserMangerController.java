package com.example.do_an_cnpm.controller.rest;

import com.example.do_an_cnpm.controller.services.UserServices;
import com.example.do_an_cnpm.model.Account;
import com.example.do_an_cnpm.model.UserSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class UserMangerController {

    private static final Logger log = LoggerFactory.getLogger(UserMangerController.class);
    private UserServices userServices;

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogin(@RequestBody Account account) {
        UserSession session = userServices.login(account);
        if (session != null) {
            return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/loginWithToken",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogin(@RequestBody String token) {
        UserSession session = userServices.login(token);
        if (session != null) {
            return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userLogout(@RequestBody String token) {
        boolean isLogout = userServices.logout(token);
        return isLogout ? new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK) : new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userRegister(@RequestBody Account account) {
        UserSession session = userServices.register(account);
        if (session != null) {
            return new ResponseEntity<>(ResponseEntity.ok(session), HttpStatus.OK);
        }
        return new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }

    @RequestMapping(
            value = "/changePassword",
            method = RequestMethod.POST
    )
    ResponseEntity<?> userChangePassword(@RequestBody Account account) {
        boolean isPasswordChanged = userServices.changePassword(account);
        return isPasswordChanged ? new ResponseEntity<>(ResponseEntity.ok().build(), HttpStatus.OK) : new ResponseEntity<>(ResponseEntity.notFound().build(), HttpStatus.NOT_FOUND);
    }
}

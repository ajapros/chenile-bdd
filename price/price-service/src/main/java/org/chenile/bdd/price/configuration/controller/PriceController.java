package org.chenile.bdd.price.configuration.controller;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;
import org.chenile.base.response.GenericResponse;
import org.chenile.bdd.price.model.Price;
import org.chenile.security.model.SecurityConfig;
import org.chenile.http.annotation.ChenileController;
import org.chenile.http.handler.ControllerSupport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@ChenileController(value = "priceService", serviceName = "_priceService_",
		healthCheckerName = "priceHealthChecker")
public class PriceController extends ControllerSupport{
	
    @PostMapping("/price")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
    public ResponseEntity<GenericResponse<Price>> save(
        HttpServletRequest httpServletRequest,
        @RequestBody Price entity){
        return process(httpServletRequest,entity);
        }

    @GetMapping("/price/{id}")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
    public ResponseEntity<GenericResponse<Price>> retrieve(
    HttpServletRequest httpServletRequest,
    @PathVariable("id") String id){
    return process(httpServletRequest,id);
    }

    @DeleteMapping("/price/{id}")
    @SecurityConfig(authorities = {"some_premium_scope","test.premium"})
    public ResponseEntity<GenericResponse<Price>> delete(
            HttpServletRequest httpServletRequest,
            @PathVariable("id") String id){
        return process(httpServletRequest,id);
    }
}

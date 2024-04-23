package org.softwarephoenix.testzadanie.controllers;

import lombok.AllArgsConstructor;
import org.softwarephoenix.testzadanie.entities.Request;
import org.softwarephoenix.testzadanie.services.RequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class RequestController {
    private RequestService requestService;

    @PostMapping("/add")
    public ResponseEntity<String> addRequest(@RequestBody Request request){

        if (request.getProduct() == null || request.getProduct().isEmpty()) {
            return ResponseEntity.badRequest().body("Заявка пустая");
        }

        if (request.getQuantity() <= 0) {
            return ResponseEntity.badRequest().body("Количество должно быть положительным");
        }

        if (request.getDeliveryAddress() == null || request.getDeliveryAddress().isEmpty()) {
            return ResponseEntity.badRequest().body("Нужно указать адрес");
        }

        if (request.getPhoneNumber() == null || !request.getPhoneNumber().matches("\\d{10}")) {
            return ResponseEntity.badRequest().body("Номер телефона до 10 символов");
        }
        requestService.save(request);
        return new ResponseEntity<>("Успешно добавлен заказ",HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<Request>> getAll(){

        return new ResponseEntity<>(requestService.getAll(),HttpStatus.OK);
    }
}

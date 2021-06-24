package com.gdrc.market.web.controller;

import com.gdrc.market.domain.Purchase;
import com.gdrc.market.domain.service.PurchaseService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/all")
    @ApiOperation(value = "Get all purchases", authorizations = {
            @Authorization(value = "JWT")
    })
    @ApiResponse(code = 200, message = "OK")
    public ResponseEntity<List<Purchase>> getAll() {
        return new ResponseEntity<>(
                purchaseService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/customer/{id}")
    @ApiOperation(value = "Search a purchase by customer id", authorizations = {
            @Authorization(value = "JWT")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "Purchase not found")
    })
    public ResponseEntity<List<Purchase>> getByCustomer(
            @ApiParam(value = "The id of the customer", required = true, example = "4546221")
            @PathVariable("id") String customerId
    ) {
        return purchaseService.getByCustomer(customerId)
                .map(purchases -> new ResponseEntity<>(purchases, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    @ApiOperation(value = "Save a product", authorizations = {
            @Authorization(value = "JWT")
    })
    @ApiResponses({
            @ApiResponse(code = 201, message = "Created")
    })
    public ResponseEntity save(@RequestBody Purchase purchase) {
        return new ResponseEntity(
                purchaseService.save(purchase),
                HttpStatus.CREATED
        );
    }
}

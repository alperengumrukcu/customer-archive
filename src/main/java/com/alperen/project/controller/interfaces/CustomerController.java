package com.alperen.project.controller.interfaces;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.model.dto.CustomerDTO;
import com.alperen.project.model.request.customer.CustomerSaveRequest;
import com.alperen.project.model.request.customer.CustomerUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/customer")
public interface CustomerController {
    @GetMapping("/all")
    ResponseEntity<BaseResponse<List<CustomerDTO>>> getAllCustomer();
    @PostMapping("/save")
    ResponseEntity<BaseResponse<CustomerDTO>> saveCustomer(@RequestBody CustomerSaveRequest request);
    @PostMapping("/delete")
    ResponseEntity<BaseResponse<?>> deleteCustomer(@RequestParam Long id);
    @PostMapping("/update")
    ResponseEntity<BaseResponse<CustomerDTO>> updateCustomer(@RequestBody CustomerUpdateRequest updateRequest);
}

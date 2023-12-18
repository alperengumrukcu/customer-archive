package com.alperen.project.controller.impl;

import com.alperen.project.base.BaseResponse;
import com.alperen.project.controller.interfaces.CustomerController;
import com.alperen.project.model.dto.CustomerDTO;
import com.alperen.project.model.request.customer.CustomerSaveRequest;
import com.alperen.project.model.request.customer.CustomerUpdateRequest;
import com.alperen.project.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@AllArgsConstructor
public class CustomerControllerImpl implements CustomerController {
    CustomerService customerService;
    @Override
    public ResponseEntity<BaseResponse<List<CustomerDTO>>> getAllCustomer() {
        List<CustomerDTO> customerDTOList = customerService.getAllCustomer();
        return BaseResponse.ok(customerDTOList);
    }

    @Override
    public ResponseEntity<BaseResponse<CustomerDTO>> saveCustomer(CustomerSaveRequest request) {
            CustomerDTO customerDTO = customerService.saveCustomer(request);
            return BaseResponse.ok(customerDTO,HttpStatus.CREATED,"Müşteri başarıyla kaydedildi.");
    }

    @Override
    public ResponseEntity<BaseResponse<?>> deleteCustomer(Long id) {
        customerService.deleteById(id);
        return BaseResponse.ok(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<BaseResponse<CustomerDTO>> updateCustomer(CustomerUpdateRequest updateRequest) {
        CustomerDTO response = customerService.updateCustomer(updateRequest);
        return BaseResponse.ok(response,HttpStatus.OK,"Müşteri güncellendi.");
    }
}

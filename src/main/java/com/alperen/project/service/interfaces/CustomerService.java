package com.alperen.project.service.interfaces;

import com.alperen.project.base.BaseService;
import com.alperen.project.model.dto.CustomerDTO;
import com.alperen.project.model.entity.Customer;
import com.alperen.project.model.request.customer.CustomerSaveRequest;
import com.alperen.project.model.request.customer.CustomerUpdateRequest;

import java.util.List;

public interface CustomerService extends BaseService<Customer,CustomerDTO> {
    List<CustomerDTO> getAllCustomer();
    CustomerDTO saveCustomer(CustomerSaveRequest request);
    CustomerDTO updateCustomer(CustomerUpdateRequest updateRequest);
}

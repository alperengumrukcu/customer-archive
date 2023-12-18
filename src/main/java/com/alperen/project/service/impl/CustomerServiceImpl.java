package com.alperen.project.service.impl;

import com.alperen.project.model.dto.CustomerDTO;
import com.alperen.project.model.entity.Customer;
import com.alperen.project.model.request.customer.CustomerSaveRequest;
import com.alperen.project.model.request.customer.CustomerUpdateRequest;
import com.alperen.project.repository.CustomerRepository;
import com.alperen.project.service.interfaces.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private CustomerRepository customerRepository;
    private ModelMapper modelMapper;
    @Override
    public Customer getById(Long id) {
        return customerRepository.getReferenceById(id);
    }

    @Override
    public void deleteById(Long id) {
        boolean isPresent = customerRepository.findById(id).isPresent();
        if(!isPresent){
            throw new RuntimeException("Müşteri bulunamadı");
        }
        customerRepository.deleteById(id);
    }

    @Override
    public Customer dto2entity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setId(customerDTO.getId());
        customer.setName(customerDTO.getName());
        customer.setSurname(customerDTO.getSurname());
        customer.setEmail(customerDTO.getEmail());
        customer.setAddress(customerDTO.getAddress());
        customer.setCreatedTime(customerDTO.getCreatedAt());
        return customer;
    }

    @Override
    public CustomerDTO entity2dto(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customer.getName());
        customerDTO.setSurname(customer.getSurname());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setAddress(customer.getAddress());
        customerDTO.setId(customer.getId());
        customerDTO.setCreatedAt(customer.getCreatedTime());
        return customerDTO;
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customerList;
        List<CustomerDTO> customerDTOList = new ArrayList<>();
       customerList = customerRepository.findAll();
       if(customerList.isEmpty()) {
           throw new RuntimeException("Müşteri bulunmamaktadır.");
       }
       for (Customer customer : customerList){
           customerDTOList.add(entity2dto(customer));
       }
       return customerDTOList;
    }

    @Override
    public CustomerDTO saveCustomer(CustomerSaveRequest request) {
        boolean isPresent = customerRepository.findByEmail(request.getEmail()).isPresent();
        if(isPresent) throw new RuntimeException("Müşteri zaten mevcut.");
        Customer customer = modelMapper.map(request,Customer.class);
        customerRepository.save(customer);
        return entity2dto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerUpdateRequest updateRequest) {
        Customer customer = getById(updateRequest.getId());
        customer.setName(updateRequest.getName());
        customer.setSurname(updateRequest.getSurname());
        customer.setEmail(updateRequest.getEmail());
        customer.setAddress(updateRequest.getAddress());
        customer = customerRepository.save(customer);
        return entity2dto(customer);
    }
}

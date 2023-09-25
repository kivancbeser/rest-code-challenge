package com.practise.restcodechallenge.service;

import java.util.List;
import com.practise.restcodechallenge.converter.ModelEntityConverter;
import com.practise.restcodechallenge.entity.CustomerEntity;
import com.practise.restcodechallenge.exception.ResourceNotFoundException;
import com.practise.restcodechallenge.model.CustomerModel;
import com.practise.restcodechallenge.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ModelEntityConverter modelEntityConverter;

    /**
     * @return
     */
    public List<CustomerModel> getAllCustomers() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return modelEntityConverter.mapList(customerEntityList, CustomerModel.class);
    }

    /**
     *
     * @param id
     * @return
     */
    public CustomerModel getCustomerById(Integer id) {
        CustomerEntity customerEntity = getCustomerEntity(id);
        return modelEntityConverter.convert(customerEntity, CustomerModel.class);
    }

    /**
     *
     * @param id
     * @return
     */
    public CustomerEntity getCustomerEntity(Integer id) {
        return customerRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User is not found with given id."));
    }

    /**
     * @param customerModel
     * @return
     */
    public CustomerModel createNewCustomer(CustomerModel customerModel) {
        CustomerEntity customerEntity = modelEntityConverter.convert(customerModel,
            CustomerEntity.class);
        customerEntity.getWallet().setCustomer(customerEntity);
        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return modelEntityConverter.convert(savedEntity, CustomerModel.class);
    }

    /**
     * @param id
     * @param customerModel
     * @return
     */
    public CustomerModel updateCustomer(Integer id, CustomerModel customerModel) {
        CustomerEntity customerEntity = getCustomerEntity(id);
        customerEntity.setMobileNumber(customerModel.getMobileNumber());
        customerEntity.setEmail(customerModel.getEmail());
        customerEntity.setName(customerModel.getName());

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return modelEntityConverter.convert(savedEntity, CustomerModel.class);
    }
}

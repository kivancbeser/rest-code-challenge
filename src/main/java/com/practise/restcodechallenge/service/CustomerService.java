package com.practise.restcodechallenge.service;

import java.util.List;
import com.practise.restcodechallenge.conventer.ModelEntityConverter;
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
    public List<CustomerModel> getAllUsers() {
        List<CustomerEntity> customerEntityList = customerRepository.findAll();
        return modelEntityConverter.mapList(customerEntityList, CustomerModel.class);
    }

    /**
     * @param userId
     * @return
     */
    public CustomerModel getUserById(Integer userId) {
        CustomerEntity customerEntity = getCustomerEntity(userId);
        return modelEntityConverter.convert(customerEntity, CustomerModel.class);
    }

    /**
     * @param userId
     * @return
     */
    public CustomerEntity getCustomerEntity(Integer userId) {
        return customerRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User is not found with given id."));
    }

    /**
     * @param customerModel
     * @return
     */
    public CustomerModel createNewUser(CustomerModel customerModel) {
        CustomerEntity customerEntity = modelEntityConverter.convert(customerModel,
            CustomerEntity.class);
        customerEntity.getWallet().setCustomer(customerEntity);
        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return modelEntityConverter.convert(savedEntity, CustomerModel.class);
    }

    /**
     * @param userId
     * @param customerModel
     * @return
     */
    public CustomerModel updateUser(Integer userId, CustomerModel customerModel) {
        CustomerEntity customerEntity = getCustomerEntity(userId);
        customerEntity.setMobileNumber(customerModel.getMobileNumber());
        customerEntity.setEmail(customerModel.getEmail());
        customerEntity.setName(customerModel.getName());

        CustomerEntity savedEntity = customerRepository.save(customerEntity);
        return modelEntityConverter.convert(savedEntity, CustomerModel.class);
    }
}

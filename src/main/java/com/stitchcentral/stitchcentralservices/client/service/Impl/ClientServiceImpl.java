package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.client.service.ClientService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {
    @Autowired
    CustomerRepo customerRepo;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        System.out.println("saveCustomer method is called-- "+customerDTO.toString());


        try{
            Optional<Customer> ss=customerRepo.findByEmail(customerDTO.getEmail());
           List<Customer> list= customerRepo.findByEmailAndId(customerDTO.getEmail(),7);
            if (ss.isPresent()) {

                for (Customer customer : list) {
                    System.out.println("22222 "+customer.getFirst_name());
                }

                return new CommonResponse(false, "Customer already exists " + ss.get().getEmail()).toString();
            }else {
                customerRepo.save(modelMapper.map(customerDTO, Customer.class));
//                return new CommonResponse(true, "Customer saved successfully");
                return new CommonResponse(true, "Customer saved successfully").toString();
            }
        }catch (Exception e){
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }




    }

//    @Override
//    public List<CustomerDTO> getCustomer(String email) {
//        System.out.println("getCustomer method is called -- " + email);
//        try {
//            List<Customer> customerList = customerRepo.findByEmail(email);
//            List<CustomerDTO> customerDTOList = customerList
//                    .stream()
//                    .map(customer -> modelMapper.map(customer, CustomerDTO.class))
//                    .collect(Collectors.toList());
//
//            return customerDTOList;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null; // You might want to handle exceptions more gracefully
//        }
//    }

    @Override
    public List<CustomerDTO> getCustomer(String email) {
        System.out.println("getCustomer method is called -- " + email);
        try {
            Optional<Customer> customerOptional = customerRepo.findByEmail(email);

            if (customerOptional.isPresent()) {
                Customer customer = customerOptional.get();
                List<CustomerDTO> customerDTOList = new ArrayList<>();
                customerDTOList.add(modelMapper.map(customer, CustomerDTO.class));
                return customerDTOList;
            } else {
                return new ArrayList<>(); // Return an empty list if no customer is found
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null; // You might want to handle exceptions more gracefully
        }
    }

    @Override
    public List<CustomerDTO> getAllCustomer() {
        System.out.println("getAllCustomer method is called");
        try {
            List<Customer> customerList = customerRepo.findAll();
            List<CustomerDTO> customerDTOList = customerList
                    .stream()
                    .map(customer -> modelMapper.map(customer, CustomerDTO.class))
                    .collect(Collectors.toList());

            return customerDTOList;
        } catch (Exception e) {
            e.printStackTrace();
            return null; // You might want to handle exceptions more gracefully
        }
    }


}

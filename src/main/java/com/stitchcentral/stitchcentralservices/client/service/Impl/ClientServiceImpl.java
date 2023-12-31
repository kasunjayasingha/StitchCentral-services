package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.client.dto.CustomerDTO;
import com.stitchcentral.stitchcentralservices.client.entity.ContactUs;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.ContactUsRepo;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.client.service.ClientService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.CustomerTypes;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    ContactUsRepo contactUsRepo;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        System.out.println("saveCustomer method is called-- " + customerDTO.toString());


        try {
            Optional<Customer> ss = customerRepo.findByEmail(customerDTO.getEmail());
//           List<Customer> list= customerRepo.findByEmailAndId(customerDTO.getEmail(),7);
            if (ss.isPresent()) {

                if (customerDTO.getCustomer_type().equals(CustomerTypes.GUEST) || ss.get().getCustomer_type().equals(CustomerTypes.GUEST)) {
                    ss.get().setFirst_name(customerDTO.getFirst_name());
                    ss.get().setLast_name(customerDTO.getLast_name());
                    ss.get().setAddress(customerDTO.getAddress());
                    ss.get().setCompany(customerDTO.getCompany());
                    ss.get().setPhone_no(customerDTO.getPhone_no());
                    ss.get().setCity(customerDTO.getCity());
                    ss.get().setClub(customerDTO.getClub());
                    ss.get().setCustomer_type(customerDTO.getCustomer_type());
                    ss.get().setUniversity(customerDTO.getUniversity());
                    if (customerDTO.getCustomer_type().equals(CustomerTypes.REGULAR)) {
                        ss.get().setPassword(customerDTO.getPassword());
                    }
                    ss.get().setUpdate_date(new java.sql.Date(System.currentTimeMillis()));
                    return new CommonResponse(false, "Customer already exists " + ss.get().getEmail()).toString();
                }
                return new CommonResponse(false, "Customer already exists " + ss.get().getEmail()).toString();
            } else {
                customerRepo.save(modelMapper.map(customerDTO, Customer.class));
//                return new CommonResponse(true, "Customer saved successfully");
                return new CommonResponse(true, "Customer saved successfully").toString();
            }
        } catch (Exception e) {
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

    @Override
    public String updateCustomer(CustomerDTO customerDTO) {
        System.out.println("updateCustomer method is called -- " + customerDTO.toString());
        try {
            if (customerDTO.getId() > 0) {
                Optional<Customer> customerOptional = customerRepo.findById(customerDTO.getId());
                if (customerOptional.isPresent()) {
                    Customer customer = customerOptional.get();
                    customer.setFirst_name(customerDTO.getFirst_name());
                    customer.setLast_name(customerDTO.getLast_name());
                    customer.setEmail(customerDTO.getEmail());
                    customer.setAddress(customerDTO.getAddress());
                    customer.setCompany(customerOptional.get().getCompany());
                    customer.setPhone_no(customerDTO.getPhone_no());
                    customer.setCity(customerOptional.get().getCity());
                    customer.setClub(customerOptional.get().getClub());
                    customer.setCustomer_type(customerOptional.get().getCustomer_type());
                    customer.setUniversity(customerOptional.get().getUniversity());
                    if (!(customerDTO.getPassword().equals(""))) {
                        customer.setPassword(customerDTO.getPassword());
                    }
                    customer.setPassword(customerOptional.get().getPassword());
                    customer.setUpdate_date(new java.sql.Date(System.currentTimeMillis()));
                    customerRepo.save(customer);
                    return new CommonResponse(true, "Customer updated successfully").toString();
                } else {
                    return new CommonResponse(false, "Customer not found").toString();
                }
            }
            return new CommonResponse(false, "Customer id is empty").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }

    }

    @Override
    public String saveContactUs(String name, String email, String message) {
        System.out.println("saveContactUs method is called -- " + name + " " + email + " " + message);
        try {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name", name);
//            jsonObject.put("email", email);
//            jsonObject.put("message", message);
            ContactUs contactUs = new ContactUs();
            contactUs.setName(name);
            contactUs.setEmail(email);
            contactUs.setMessage(message);
            contactUs.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
            contactUsRepo.save(contactUs);
            return new CommonResponse(true, "Contact us saved successfully").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }


}

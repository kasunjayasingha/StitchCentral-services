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

import java.util.List;
import java.util.Optional;

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

                return new CommonResponse(false, "Customer already exists").toString();
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


}

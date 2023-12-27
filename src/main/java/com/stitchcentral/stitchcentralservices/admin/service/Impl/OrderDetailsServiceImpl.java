package com.stitchcentral.stitchcentralservices.admin.service.Impl;

import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import com.stitchcentral.stitchcentralservices.admin.repository.OrderDetailsRepo;
import com.stitchcentral.stitchcentralservices.admin.service.OrderDetailsService;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.controller.clientController;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service("orderDetailsService")
@Transactional
public class OrderDetailsServiceImpl implements OrderDetailsService {

    private static final Logger LOGGER = Logger.getLogger(clientController.class.getName());

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AppoinmentsRepo appoinmentsRepo;

    @Autowired
    OrderDetailsRepo orderDetailsRepo;

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public String saveOrderDetails(AppointmentsDTO appointmentsDTO) {
        LOGGER.info("saveOrderDetails method is called");

        try {
            if (appointmentsDTO.getOrderDetails() == null || appointmentsDTO.getOrderDetails().isEmpty()) {
                return new CommonResponse(false, "Order Details is null").toString();
            } else {
                List<Appointments> appointments = appoinmentsRepo.findById(appointmentsDTO.getId());
                if (!appointments.isEmpty()) {
                    if (appointments.get(0).getStatus().equals(AppoinmentStatus.valueOf("ACCEPTED"))) {
                        LOGGER.info("saveOrderDetails ACCEPTED method is called");
                        OrderDetailsDTO orderDetailsDTO = appointmentsDTO.getOrderDetails().get(0);
                        OrderDetails od = new OrderDetails();
                        od.setAppointments(appointments.get(0));
                        od.setOrderType(orderDetailsDTO.getOrderType());
                        od.setQuantity(orderDetailsDTO.getQuantity());
                        od.setMaterial(orderDetailsDTO.getMaterial());
                        od.setDescription(orderDetailsDTO.getDescription());
                        od.setDispatchDate(orderDetailsDTO.getDispatchDate());
                        od.setSwingPlace(orderDetailsDTO.getSwingPlace());
                        od.setPayment(orderDetailsDTO.getPayment());
                        od.setAdvance(orderDetailsDTO.getAdvance());
                        od.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
                        od.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
                        if (appointments.get(0).getClient_sample() != null) {
                            od.setClientSample(appointments.get(0).getClient_sample());
                        }
                        od.setOrderStatus("PENDING");

                        od = orderDetailsRepo.save(od);
                        System.out.println("Year: " + od.getCreateDate());
                        System.out.println("Year: " + (od.getCreateDate().getYear() + 1900));
                        od.setInvoiceNo("INV-" + (od.getCreateDate().getYear() + 1900) + "-" + (od.getCreateDate().getMonth() + 1) + "-" + od.getId());
                        orderDetailsRepo.save(od);

                        appointments.get(0).setStatus(AppoinmentStatus.COMPLETED);
                        appoinmentsRepo.save(appointments.get(0));
                    } else {
                        for (OrderDetailsDTO orderDetailsDTO : appointmentsDTO.getOrderDetails()) {
                            OrderDetails od = new OrderDetails();
                            od.setAppointments(appointments.get(0));
                            od.setOrderType(orderDetailsDTO.getOrderType());
                            od.setQuantity(orderDetailsDTO.getQuantity());
                            od.setMaterial(orderDetailsDTO.getMaterial());
                            od.setDescription(orderDetailsDTO.getDescription());
                            od.setDispatchDate(orderDetailsDTO.getDispatchDate());
                            od.setSwingPlace(orderDetailsDTO.getSwingPlace());
                            od.setPayment(orderDetailsDTO.getPayment());
                            od.setAdvance(orderDetailsDTO.getAdvance());
                            od.setCreateDate(new java.sql.Date(System.currentTimeMillis()));
                            od.setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
                            od.setOrderStatus("PENDING");

                            od = orderDetailsRepo.save(od);
                            System.out.println("Year: " + od.getCreateDate());
                            System.out.println("Year: " + (od.getCreateDate().getYear() + 1900));
                            od.setInvoiceNo("INV-" + (od.getCreateDate().getYear() + 1900) + "-" + (od.getCreateDate().getMonth() + 1) + "-" + od.getId());
                            orderDetailsRepo.save(od);
                        }
                        appointments.get(0).setStatus(appointmentsDTO.getStatus());
                        appoinmentsRepo.save(appointments.get(0));
                    }
                    return new CommonResponse(true, "Order Details saved successfully").toString();
                } else {
                    return new CommonResponse(false, "Appointment not found").toString();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("saveOrderDetails method is called");
            return new CommonResponse(false, e.getMessage()).toString();
        }

    }

    @Override
    public String updateOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        LOGGER.info("updateOrderDetails method is called");

        try {
            if (orderDetailsDTO.getId() == 0) {
                return new CommonResponse(false, "Order Details is null").toString();
            } else {

                Optional<OrderDetails> od = orderDetailsRepo.findById(orderDetailsDTO.getId());
                if (od.isPresent()) {
                    od.get().setOrderType(orderDetailsDTO.getOrderType());
                    od.get().setQuantity(orderDetailsDTO.getQuantity());
                    od.get().setMaterial(orderDetailsDTO.getMaterial());
                    od.get().setDescription(orderDetailsDTO.getDescription());
                    od.get().setDispatchDate(orderDetailsDTO.getDispatchDate());
                    od.get().setSwingPlace(orderDetailsDTO.getSwingPlace());
                    od.get().setPayment(orderDetailsDTO.getPayment());
                    od.get().setAdvance(orderDetailsDTO.getAdvance());
                    od.get().setUpdateDate(new java.sql.Date(System.currentTimeMillis()));
                    od.get().setOrderStatus(orderDetailsDTO.getOrderStatus());

                    od.get().setPayment(orderDetailsDTO.getPayment());

                    if (od.get().getPayment().equals("HALF") && orderDetailsDTO.getOrderStatus().equals("COMPLETED")) {
                        od.get().setPayment("FULL");
                        od.get().setAdvance(od.get().getAdvance() * 2);
                    }

                    if (od.get().getPayment().equals("FULL") && orderDetailsDTO.getOrderStatus().equals("COMPLETED")) {
                        SimpleMailMessage msg = new SimpleMailMessage();
                        msg.setTo(od.get().getAppointments().getCustomer().getEmail());
                        msg.setFrom("stichcentral@gmail.com");
                        msg.setSubject("Your Order is ready");
                        msg.setText("Hi " + od.get().getAppointments().getCustomer().getFirst_name() + "," +
                                "\n\nYour order is ready. Please visit our shop to collect your order." +
                                "\n\nThank you,\nStitch Central");
                        javaMailSender.send(msg);

                        System.out.println("Email send to: " + od.get().getAppointments().getCustomer().getEmail());
                        orderDetailsRepo.save(od.get());
                        return new CommonResponse(true, "mail send").toString();
                    } else {
                        orderDetailsRepo.save(od.get());
                        return new CommonResponse(true, "Order Details updated successfully").toString();
                    }


                } else {
                    return new CommonResponse(false, "Order Details not found").toString();
                }


            }

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("updateOrderDetails method is called");
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public List<OrderDetailsDTO> getOrderDetails() {
        LOGGER.info("getOrderDetails method is called");

        try {
            List<OrderDetails> orderDetails = orderDetailsRepo.findAll();
            return orderDetails.stream().map(orderDetail -> modelMapper.map(orderDetail, OrderDetailsDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("getOrderDetails method is called");
            return null;
        }
    }

    @Override
    public Integer getOrderCount() {
        LOGGER.info("getOrderCount method is called");

        try {
            List<OrderDetails> orderDetails = orderDetailsRepo.findAll();
            return orderDetails.size();
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("getOrderCount method is called");
            return null;
        }
    }
}

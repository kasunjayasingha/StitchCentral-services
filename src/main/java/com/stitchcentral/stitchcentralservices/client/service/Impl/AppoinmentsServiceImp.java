package com.stitchcentral.stitchcentralservices.client.service.Impl;

import com.stitchcentral.stitchcentralservices.admin.dto.DashBoardDTO;
import com.stitchcentral.stitchcentralservices.admin.dto.OrderDetailsDTO;
import com.stitchcentral.stitchcentralservices.admin.entity.OrderDetails;
import com.stitchcentral.stitchcentralservices.admin.repository.OrderDetailsRepo;
import com.stitchcentral.stitchcentralservices.client.dto.AppointmentsDTO;
import com.stitchcentral.stitchcentralservices.client.dto.ClientSampleDTO;
import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
import com.stitchcentral.stitchcentralservices.client.entity.Customer;
import com.stitchcentral.stitchcentralservices.client.repository.AppoinmentsRepo;
import com.stitchcentral.stitchcentralservices.client.repository.Client_SampleRepo;
import com.stitchcentral.stitchcentralservices.client.repository.CustomerRepo;
import com.stitchcentral.stitchcentralservices.client.service.AppointmentsService;
import com.stitchcentral.stitchcentralservices.fileService.service.FileUploadService;
import com.stitchcentral.stitchcentralservices.util.CommonResponse;
import com.stitchcentral.stitchcentralservices.util.FileCompress;
import com.stitchcentral.stitchcentralservices.util.enums.AppoinmentStatus;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service("appointmentsService")
@Transactional
public class AppoinmentsServiceImp implements AppointmentsService {

    private static final String CUSTOMER_NOT_FOUND = "Customer not found";
    private final String FOLDER_PATH = "D:/CINEC EDU/Group_Project/File_Data/";
    @Autowired
    AppoinmentsRepo appoinmentsRepo;
    @Autowired
    CustomerRepo customerRepo;
    @Autowired
    Client_SampleRepo clientSampleRepo;
    @Autowired
    OrderDetailsRepo orderDetailsRepo;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private FileUploadService fileUploadService;

    @Override
    public String saveAppointment(AppointmentsDTO appointmentsDTO) {
        System.out.println("saveAppointment method is called-- " + appointmentsDTO.toString());
        try {
//            Get existing customer
            Optional<Customer> customerFind = customerRepo.findByEmail(appointmentsDTO.getCustomer().getEmail());

//            System.out.println("customerFind "+customerFind);
            if (customerFind.isPresent()) {

//                Check if appointment already exists
                Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_IdAndStatus(customerFind.get().getId(), AppoinmentStatus.PENDING);

//              Check if appointment already exists with status PENDING
                if (appointmentsFind.isPresent()) {
                    return new CommonResponse(false, "Pending appointment already exists").toString();
                } else {
//                    Save appointment
                    Appointments appointments = new Appointments();
                    appointments.setCustomer(customerFind.get());
                    appointments.setStatus(AppoinmentStatus.PENDING);
                    appointments.setAppointment_date(appointmentsDTO.getAppointment_date());
                    appointments.setDescription(appointmentsDTO.getDescription());
                    appointments.setType(appointmentsDTO.getType());

//                  appoinment save and assign again save appointment to appointments object
                    appointments = appoinmentsRepo.save(appointments);
//                    System.out.println("appointments----- "+appointments);

//                    if (!appointmentsDTO.getFile().isEmpty()) {
//                        Client_Sample clientSample = new Client_Sample();
//                        clientSample.setFile_name(appointmentsDTO.getFile().getOriginalFilename());
//                        clientSample.setFile_type(appointmentsDTO.getFile().getContentType());
//                        clientSample.setFileData(FileCompress.compressBytes(appointmentsDTO.getFile().getBytes()));
//                        clientSample.setRelative_path(appointmentsDTO.getFile().getOriginalFilename());
//                        clientSample.setCreate_date(appointmentsDTO.getClient_sample().getCreate_date());
//                        clientSample.setUpdate_date(appointmentsDTO.getClient_sample().getUpdate_date());
//
//                        if(clientSample.getFileData().length > 100000){
//                            return new CommonResponse(false, "File size is too large").toString();
//                        }
//
//                        clientSample.setAppointments(appointments);
//                        clientSampleRepo.save(modelMapper.map(clientSample, Client_Sample.class));
//                    }

//                    if(appointmentsDTO.getClient_sample() != null){
//                        Client_Sample clientSample = new Client_Sample();
//                        clientSample.setFile_name(appointmentsDTO.getClient_sample().getFile_name());
//                        clientSample.setFile_type(appointmentsDTO.getClient_sample().getFile_type());
//                        clientSample.setFileData(appointmentsDTO.getClient_sample().getFileData());
//                        clientSample.setRelative_path(appointmentsDTO.getClient_sample().getRelative_path());
//                        clientSample.setCreate_date(appointmentsDTO.getClient_sample().getCreate_date());
//                        clientSample.setUpdate_date(appointmentsDTO.getClient_sample().getUpdate_date());
//                        clientSample.setAppointments(appointments);
//                        clientSampleRepo.save(modelMapper.map(clientSample, Client_Sample.class));
//
//                        return new CommonResponse(true, "Appointment saved successfully").toString();
//                    }
//                    Client_Sample clientSample = new Client_Sample();
////                    System.out.println("appointmentsDTO.getSample() "+appointmentsDTO.getClient_sample());
//
//                    clientSample.setFile_name(appointmentsDTO.getClient_sample().getFile_name());
//                    clientSample.setFile_type(appointmentsDTO.getClient_sample().getFile_type());
//                    clientSample.setFileData(appointmentsDTO.getClient_sample().getFileData());
//                    clientSample.setRelative_path(appointmentsDTO.getClient_sample().getRelative_path());
//                    clientSample.setCreate_date(appointmentsDTO.getClient_sample().getCreate_date());
//                    clientSample.setUpdate_date(appointmentsDTO.getClient_sample().getUpdate_date());
//                    clientSample.setAppointments(appointments);
//                    clientSampleRepo.save(modelMapper.map(clientSample, Client_Sample.class));
//                    appointments.setClient_sample(appointmentsDTO.getSample());
//                    appoinmentsRepo.save(modelMapper.map(appointmentsDTO, Appointments.class));

                    return new CommonResponse(true, "" + appointments.getId()).toString();
                }

//                appoinmentsRepo.save(modelMapper.map(appointmentsDTO, Appointments.class));
//                return "Appointment saved successfully";
            } else {
                return new CommonResponse(false, CUSTOMER_NOT_FOUND).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public List<AppointmentsDTO> getAppoinment(String email) {
        System.out.println("getAppoinment method is called-- " + email);
        try {
//            Optional<Customer> customerFind = customerRepo.findByEmail(email);
            List<Appointments> appointmentsList = appoinmentsRepo.findByCustomer_Email(email);
            if (!appointmentsList.isEmpty()) {
                List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();
                appointmentsList.forEach(appointments -> {
                    AppointmentsDTO appointmentsDTO = modelMapper.map(appointments, AppointmentsDTO.class);
                    appointmentsDTOList.add(appointmentsDTO);
                });
                return appointmentsDTOList;


                //                Optional<Appointments> appointmentsList = appoinmentsRepo.findByCustomer_Id(customerFind.get().getId());
//                if(appointmentsList.isPresent()){
//                    List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();
//                    List<Appointments> appointmentsList1 = appoinmentsRepo.findById(appointmentsList.get().getId());
//                    List<Client_Sample> clientSampleList = clientSampleRepo.findByAppointments_Id(appointmentsList.get().getId());
////
//                    appointmentsList1.forEach(appointments -> {
//                        AppointmentsDTO appointmentsDTO = modelMapper.map(appointments, AppointmentsDTO.class);
//                        appointmentsDTO.setSample(modelMapper.map(clientSampleList.get(0), ClientSampleDTO.class));
//                        appointmentsDTOList.add(appointmentsDTO);
//                    });
//                    return appointmentsDTOList;
//                }else {
//                    return null;
//                }
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String updateAppoinment(AppointmentsDTO appointmentsDTO) {
        System.out.println("updateAppoinment method is called-- " + appointmentsDTO.toString());
        try {
            Optional<Customer> customerFind = customerRepo.existsByEmail(appointmentsDTO.getCustomer().getEmail());
            System.out.println("customerFind--------- " + customerFind);
            if (customerFind.isPresent()) {
                Optional<Customer> customerFind1 = customerRepo.findByEmail(appointmentsDTO.getCustomer().getEmail());
                Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_Id(customerFind1.get().getId());
                if (appointmentsFind.isPresent()) {
                    Appointments appointments = appointmentsFind.get();
                    appointments.setStatus(AppoinmentStatus.valueOf(appointmentsDTO.getStatus().toString()));
                    appointments.setAppointment_date(appointmentsDTO.getAppointment_date());
                    appointments.setDescription(appointmentsDTO.getDescription());
                    appointments.setType(appointmentsDTO.getType());
                    appointments = appoinmentsRepo.save(appointments);

//                    Optional<Client_Sample> clientSampleFind = clientSampleRepo.findByAppointments_IdOrAppointments_Customer_Email(appointments.getId(), appointmentsDTO.getCustomer().getEmail());
//                    if(clientSampleFind.isPresent()){
//                        Client_Sample clientSample = clientSampleFind.get();
//                        clientSample.setFile_name(appointmentsDTO.getClient_sample().getFile_name());
//                        clientSample.setFile_type(appointmentsDTO.getClient_sample().getFile_type());
////                        clientSample.setPath(appointmentsDTO.getClient_sample().getPath());
//                        clientSample.setRelative_path(appointmentsDTO.getClient_sample().getRelative_path());
//                        clientSample.setCreate_date(appointmentsDTO.getClient_sample().getCreate_date());
//                        clientSample.setUpdate_date(appointmentsDTO.getClient_sample().getUpdate_date());
//                        clientSample.setAppointments(appointments);
//                        clientSampleRepo.save(clientSample);
//                    }

                    return new CommonResponse(true, "Appointment updated successfully").toString();
                } else {
                    return new CommonResponse(false, "Appointment not found").toString();
                }

            } else {
                return new CommonResponse(false, CUSTOMER_NOT_FOUND).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public String deleteAppoinment(String email) {
        System.out.println("deleteAppoinment method is called-- " + email);
        try {
            Optional<Customer> customerFind = customerRepo.findByEmail(email);
            if (customerFind.isPresent()) {
                Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_IdAndStatus(customerFind.get().getId(), AppoinmentStatus.PENDING);
                if (appointmentsFind.isPresent()) {
                    clientSampleRepo.deleteByAppointments_Id(appointmentsFind.get().getId());
                    appoinmentsRepo.deleteById(appointmentsFind.get().getId());
                    return new CommonResponse(true, "Appointment deleted successfully").toString();
                } else {
                    return new CommonResponse(false, "Appointment not found").toString();
                }

            } else {
                return new CommonResponse(false, CUSTOMER_NOT_FOUND).toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }
    }

    @Override
    public List<AppointmentsDTO> getAllAppoinment(AppoinmentStatus status) {
        List<Appointments> appointmentsList = appoinmentsRepo.findByStatus(status);
        if (!appointmentsList.isEmpty()) {
            List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();

            for (Appointments appointments : appointmentsList) {
                AppointmentsDTO appointmentsDTO = modelMapper.map(appointments, AppointmentsDTO.class);

                Optional<Client_Sample> clientSampleList = clientSampleRepo.findByAppointments_Id(appointments.getId());
                if (clientSampleList.isPresent()) {
                    ClientSampleDTO clientSampleDTO = new ClientSampleDTO();
                    clientSampleDTO.setId(clientSampleList.get().getId());
                    clientSampleDTO.setFile_name(clientSampleList.get().getFile_name());
                    clientSampleDTO.setFile_type(clientSampleList.get().getFile_type());
//                    clientSampleDTO.setFileData(FileCompress.decompressBytes(clientSampleList.get().getFileData()));
                    clientSampleDTO.setRelative_path(clientSampleList.get().getRelative_path());
                    clientSampleDTO.setCreate_date(clientSampleList.get().getCreate_date());
                    clientSampleDTO.setUpdate_date(clientSampleList.get().getUpdate_date());
                    appointmentsDTO.setClient_sample(clientSampleDTO);
                }

                List<OrderDetails> orderDetailsList = orderDetailsRepo.getAllByAppointments_Id(appointments.getId());
                if (!orderDetailsList.isEmpty()) {
                    List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
                    for (OrderDetails orderDetails : orderDetailsList) {
                        OrderDetailsDTO orderDetailsDTO = modelMapper.map(orderDetails, OrderDetailsDTO.class);
                        orderDetailsDTOList.add(orderDetailsDTO);

                    }
                    appointmentsDTO.setOrderDetails(orderDetailsDTOList);

                } else {
                    appointmentsDTO.setOrderDetails(null);
                }

                appointmentsDTOList.add(appointmentsDTO);
            }

            return appointmentsDTOList;

//            appointmentsList.forEach(appointments -> {
//                AppointmentsDTO appointmentsDTO = modelMapper.map(appointments, AppointmentsDTO.class);
//                appointmentsDTOList.add(appointmentsDTO);
//            });
//            List<AppointmentsDTO> appointmentsDTOList = appointmentsList
//                    .stream()
//                    .map(appointments -> modelMapper.map(appointments, AppointmentsDTO.class))
//                    .collect(java.util.stream.Collectors.toList());
//            return appointmentsDTOList;
        } else {
            return null;
        }
    }

    @Override
    public String cancelAppoinment(AppointmentsDTO appointmentsDTO) {
        System.out.println("cancelAppoinment method is called-- " + appointmentsDTO.getId());
        System.out.println("cancelAppoinment method is called-- " + appointmentsDTO.getStatus());

        try {
            List<Appointments> appointmentsFind = appoinmentsRepo.findById(appointmentsDTO.getId());
            if (!appointmentsFind.isEmpty()) {
                for (Appointments appointments : appointmentsFind) {
                    SimpleMailMessage msg = new SimpleMailMessage();
                    if (appointmentsDTO.getStatus().toString().equals("ACCEPTED")) {
                        appointments.setStatus(AppoinmentStatus.ACCEPTED);
                        appoinmentsRepo.save(appointments);

                        msg.setFrom("stichcentral@gmail.com");
                        msg.setTo(appointments.getCustomer().getEmail());
                        msg.setSubject("Appointment Accepted!");
                        msg.setText("Hello " + appointments.getCustomer().getFirst_name() + "" +
                                ",\n\nYour appointment has been accepted. We will contact you on " + appointments.getAppointment_date() +
                                "\n\nThank you,\nStitchCentral");
                        javaMailSender.send(msg);
                        System.out.println("Email sent successfully");

                    } else {
                        appointments.setStatus(AppoinmentStatus.CANCELLED);
                        appointments.setCancellationReason(appointmentsDTO.getCancellationReason());
                        appoinmentsRepo.save(appointments);

                        msg.setFrom("stichcentral@gmail.com");
                        msg.setTo(appointments.getCustomer().getEmail());
                        msg.setSubject("Appointment Cancelled!");
                        msg.setText("Hello " + appointments.getCustomer().getFirst_name() + "" +
                                ",\n\nYour appointment has been cancelled. Because of " + appointments.getCancellationReason() + "" +
                                "\n\nThank you,\nStitchCentral");
                        javaMailSender.send(msg);
                        System.out.println("Email sent successfully");
                    }

                }
                return new CommonResponse(true, "Appointment cancelled successfully").toString();
            } else {
                return new CommonResponse(false, "Appointment not found").toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }


    }

    @Override
    public String uploadFile(MultipartFile file, String email) {
        System.out.println("uploadFile method is called-- " + email);
        System.out.println("uploadFile method is called-- " + file.getOriginalFilename());
        try {
            Optional<Customer> customerFind = customerRepo.findByEmail(email);
            Optional<Appointments> appointmentsFind = appoinmentsRepo.findByCustomer_IdAndStatus(customerFind.get().getId(), AppoinmentStatus.PENDING);
            if (appointmentsFind.isPresent()) {

//                Client_Sample clientSample = new Client_Sample();
//                clientSample.setFile_name(file.getOriginalFilename());
//                clientSample.setFile_type(file.getContentType());
                if (file.getBytes().length > 10000000) {
                    return new CommonResponse(false, "File size is too large").toString();
                }
//                clientSample.setFileData(FileCompress.compressBytes(file.getBytes()));
//                clientSample.setRelative_path(file.getOriginalFilename());
//                clientSample.setCreate_date(new java.sql.Date(System.currentTimeMillis()));
//                clientSample.setUpdate_date(new java.sql.Date(System.currentTimeMillis()));
//                clientSample.setAppointments(appointmentsFind.get());
//                clientSampleRepo.save(clientSample);

                String filePath = FOLDER_PATH + file.getOriginalFilename();
                Client_Sample clientSample = clientSampleRepo.save(Client_Sample.builder()
                        .file_name(file.getOriginalFilename())
                        .file_type(file.getContentType())
                        .fileData(FileCompress.compressBytes(file.getBytes()))
                        .relative_path(file.getOriginalFilename())
                        .create_date(new java.sql.Date(System.currentTimeMillis()))
                        .update_date(new java.sql.Date(System.currentTimeMillis()))
                        .appointments(appointmentsFind.get())
                        .build());
//
//                file.transferTo(new java.io.File(filePath));

//                boolean isFileSaved = fileUploadService.uploadFile(file, appointmentsFind.get());

                if (clientSample != null) {
                    return new CommonResponse(true, "File uploaded successfully").toString();
                } else {
                    return new CommonResponse(false, "File upload failed").toString();
                }

//                return new CommonResponse(true, "File uploaded successfully").toString();
            }
            return new CommonResponse(false, "Appointment not found").toString();
        } catch (Exception e) {
            e.printStackTrace();
            return new CommonResponse(false, e.getMessage()).toString();
        }

    }

    @Override
    public byte[] downloadFile(Integer sampleId) {
        System.out.println("downloadFile method is called-- " + sampleId);
        try {
            Optional<Client_Sample> clientSampleFind = clientSampleRepo.findById(sampleId);
            if (clientSampleFind.isPresent()) {
                ClientSampleDTO clientSampleDTO = new ClientSampleDTO();
                clientSampleDTO.setFileData(FileCompress.decompressBytes(clientSampleFind.get().getFileData()));
                return clientSampleDTO.getFileData();
            } else {
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public List<DashBoardDTO> getDashboardDetails(Integer year) {
        System.out.println("getDashboardDetails method is called-- " + year);

        try {
            List<DashBoardDTO> dashBoardDTOList = new ArrayList<>();
            DashBoardDTO dashBoardDTO = new DashBoardDTO();

            List<Customer> customerList = customerRepo.findAll();
            if (!customerList.isEmpty()) {
                dashBoardDTO.setTotalCustomers(customerList.size());
            }

            List<Appointments> appointmentsList = appoinmentsRepo.findByStatus(AppoinmentStatus.PENDING);
            if (!appointmentsList.isEmpty()) {
                dashBoardDTO.setTotalPendingAppointments(appointmentsList.size());
            }
            List<OrderDetails> orderDetailsDTOList = orderDetailsRepo.findAllByOrderStatus("PENDING");
            if (!orderDetailsDTOList.isEmpty()) {
                dashBoardDTO.setTotalPendingOrders(orderDetailsDTOList.size());
            }

            Date startDate = new Date(year - 1900, 0, 1);
            Date endDate = new Date(year - 1900, 11, 31);

            List<OrderDetails> orderDetailsList2 = orderDetailsRepo.findAllByUpdateDateBetween(startDate, endDate);
            System.out.println("orderDetailsList2 " + orderDetailsList2.size());
            if (!orderDetailsList2.isEmpty()) {
                int totalRevenue = 0;
                for (OrderDetails orderDetails : orderDetailsList2) {
                    totalRevenue += orderDetails.getAdvance();
                }
                dashBoardDTO.setTotalRevenue(totalRevenue);
            }


            // Calculate monthly details
//            montlyRevenue

            Map<Integer, Integer> monthlyRevenueMap = new HashMap<>();
            for (OrderDetails orderDetails : orderDetailsList2) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(orderDetails.getUpdateDate());
                int month = calendar.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
                int revenue = orderDetails.getAdvance();

                monthlyRevenueMap.merge(month, revenue, Integer::sum);
            }

            //            MothlyOrders
            Map<Integer, Integer> monthlyOrdersMap = new HashMap<>();
            for (OrderDetails orderDetails : orderDetailsList2) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(orderDetails.getUpdateDate());
                int month = calendar.get(Calendar.MONTH) + 1; // Adding 1 because Calendar.MONTH is zero-based
                int orders = 1;

                monthlyOrdersMap.merge(month, orders, Integer::sum);
            }


            // Set monthly details in DashBoardDTO
            dashBoardDTO.setDashBoardDTOList(new ArrayList<>());
            for (Map.Entry<Integer, Integer> entry : monthlyRevenueMap.entrySet()) {

                DashBoardDTO monthlyDashBoardDTO = new DashBoardDTO();
                monthlyDashBoardDTO.setYear(year);
                monthlyDashBoardDTO.setMonth(entry.getKey());
                monthlyDashBoardDTO.setMontlyRevenue(entry.getValue());
                monthlyDashBoardDTO.setMontlyOrders(monthlyOrdersMap.get(entry.getKey()));
                dashBoardDTO.getDashBoardDTOList().add(monthlyDashBoardDTO);
            }

            dashBoardDTOList.add(dashBoardDTO);
            return dashBoardDTOList;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public List<OrderDetailsDTO> getAllPendingOrders() {
        System.out.println("getAllAppoinmentsWithOrder method is called-- ");
        try {
            List<OrderDetails> orderDetailsList = orderDetailsRepo.findAllByOrderStatus("PENDING");

            if (!orderDetailsList.isEmpty()) {
                List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
                for (OrderDetails orderDetails : orderDetailsList) {
                    OrderDetailsDTO orderDetailsDTO = modelMapper.map(orderDetails, OrderDetailsDTO.class);
                    orderDetailsDTOList.add(orderDetailsDTO);
                }
                return orderDetailsDTOList;
            } else {
                return null;
            }


//            List<Integer> appointmentIdList = new ArrayList<>();
//            for (OrderDetails orderDetails : orderDetailsList) {
//                appointmentIdList.add(orderDetails.getAppointments().getId());
//            }
//            System.out.println("appointmentIdList " + appointmentIdList);
//            List<Integer> distinctAppointmentIdList = appointmentIdList.stream().distinct().collect(java.util.stream.Collectors.toList());
//            System.out.println("distinctAppointmentIdList " + distinctAppointmentIdList);
//
//            List<AppointmentsDTO> appointmentsDTOList = new ArrayList<>();
//            for (Integer appointmentId : distinctAppointmentIdList) {
//                Optional<Appointments> appointmentsFind = appoinmentsRepo.findById(appointmentId);
//                if (appointmentsFind.isPresent()) {
//                    AppointmentsDTO appointmentsDTO = modelMapper.map(appointmentsFind.get(), AppointmentsDTO.class);
//                    appointmentsDTOList.add(appointmentsDTO);
//                }
//            }
//            return appointmentsDTOList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getMonthName(int monthNumber) {
        String[] monthNames = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };

        // Check if the month number is within a valid range
        if (monthNumber >= 1 && monthNumber <= 12) {
            return monthNames[monthNumber - 1]; // Adjusting for zero-based index
        } else {
            return "Invalid Month";
        }
    }

}

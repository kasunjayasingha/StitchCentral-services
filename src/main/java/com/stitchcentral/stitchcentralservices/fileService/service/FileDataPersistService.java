package com.stitchcentral.stitchcentralservices.fileService.service;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import com.stitchcentral.stitchcentralservices.client.entity.Client_Sample;
import com.stitchcentral.stitchcentralservices.client.repository.Client_SampleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.logging.Logger;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileDataPersistService {

    private static final Logger LOGGER = Logger.getLogger(FileDataPersistService.class.getName());
    @Autowired
    private Client_SampleRepo clientSampleRepo;

    boolean persistToDB(String relativePath, String fileType, String fileName, String fileID, Appointments appointmentId) {

        LOGGER.info("persistToDB method is called");
        try {
            Client_Sample clientSample = new Client_Sample();
            clientSample.setRelative_path(relativePath);
            clientSample.setFile_type(fileType);
            clientSample.setFile_name(fileName);
            clientSample.setFileId(fileID);
            clientSample.setAppointments(appointmentId);
            clientSample.setCreate_date(new Date());
            clientSample.setUpdate_date(new Date());

            clientSampleRepo.save(clientSample);

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("Exception in persistToDB method");
            return false;
        }


    }
}

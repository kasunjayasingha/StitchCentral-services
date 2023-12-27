package com.stitchcentral.stitchcentralservices.fileService.service;

import com.stitchcentral.stitchcentralservices.client.entity.Appointments;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Service("fileUploadService")
@Transactional(rollbackFor = Exception.class)
public class FileUploadService {

    private static final Logger LOGGER = Logger.getLogger(FileUploadService.class.getName());
    private final Path fileStorageLocation;
    private FileDataPersistService fileDataPersistService;
    private String uploadDirectory;

    public FileUploadService(@Value("${fileUploadPath}") String fileUploadLocation,
                             FileDataPersistService fileDataPersistService) {

        this.fileDataPersistService = fileDataPersistService;
        this.uploadDirectory = fileUploadLocation;
        this.fileStorageLocation = Paths.get(fileUploadLocation)
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            LOGGER.info("FAILED TO CREATE FILE UPLOAD LOCATION. CHECK PERMISSIONS & PATH IN BOOT PROPERTIES!");
        }

    }

    public boolean uploadFile(MultipartFile file, Appointments appointmentId) {

        LOGGER.info("file size..." + file.getSize());
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        if (fileName.contains("..")) {
            LOGGER.info("Sorry! Filename contains invalid path sequence!");
            return false;
        }

        try {
            LOGGER.info("UPLOADING.... " + fileName);

            UUID uuid = UUID.randomUUID();
            String fileID = uuid.toString();
            String fileExt = FilenameUtils.getExtension(fileName);
            String fileStoreAlias = fileID.concat(".").concat(fileExt);

            Path targetLocation = this.fileStorageLocation.resolve(fileStoreAlias);

            File fileToUpload = targetLocation.toFile();

            FileUtils.copyInputStreamToFile(file.getInputStream(), fileToUpload);

            boolean isPersisted = fileDataPersistService.persistToDB(targetLocation.toString(), resolveMediaTypeByExtension(fileExt), fileName, fileID, appointmentId);

            if (isPersisted) {
                LOGGER.info("File persisted to DB");
                return true;
            } else {
                LOGGER.info("Error in persisting file to DB");
                return false;
            }

        } catch (IOException e) {
            LOGGER.info("Error in uploading file");
            e.printStackTrace();
            return false;

        } catch (Exception e) {
            LOGGER.info("Error in uploading file");
            e.printStackTrace();
            return false;

        }

    }

    private String resolveMediaTypeByExtension(String ext) {
        switch (ext) {
            case "png":
                return "image/png";
            case "jpeg":
            case "jpg":
                return "image/jpeg";
            case "pdf":
                return "application/pdf";
            case "txt":
                return "text/plain";
            default:
                return "application/octet-stream";
        }
    }

    public Resource loadFileAsResource(String absolutePath) {
        try {
            Path filePath = this.fileStorageLocation.resolve(absolutePath).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                LOGGER.info("FILE NOT FOUND!");
                return null;
            }
        } catch (MalformedURLException ex) {
            LOGGER.info("FILE PATH IS INVALID!");
            return null;
        }
    }
}

package com.SpringTask.SpringTask.FilesSystem;

import com.SpringTask.SpringTask.Data.EmployeeController;
import com.SpringTask.SpringTask.Models.ImageData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class FileService {


    @Autowired
    private FilesRepo filesRepo;

   public String uploadImage(MultipartFile file) throws IOException {
       ImageData imageData= filesRepo.save(ImageData.builder()
                       .name(file.getOriginalFilename())
                       .type(file.getContentType())
                       .id(5)
               .image_filedata(ImageUtils.compressImage(file.getBytes())).build());
       /*if (imageData!=null){
           return String.valueOf(imageData.getId());
       }
       throw new EmployeeController.CustomException("error while saving file");*/
       return String.valueOf(imageData.getId());
   }
    public int uploadImageFile(MultipartFile file) throws IOException {
        ImageData imageData= filesRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .id(filesRepo.findAll().size()+1)
                .image_filedata(ImageUtils.compressImage(file.getBytes())).build());
        /*if (imageData!=null){
            return "file uploaded "+ file.getOriginalFilename();
        }
        return "error while uploading";*/
        //return String.valueOf(imageData.getId());
        //return String.valueOf(imageData.getId());
        return imageData.getId();

    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = filesRepo.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImage_filedata());
        return images;
    }

}
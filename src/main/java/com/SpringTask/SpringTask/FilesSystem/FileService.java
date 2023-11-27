package com.SpringTask.SpringTask.FilesSystem;

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
               .imageData(ImageUtils.compressImage(file.getBytes())).build());
       if (imageData!=null){
           return "file uploaded "+ file.getOriginalFilename();
       }
       return "error while uploading";

   }
    public String uploadImageFile(MultipartFile file) throws IOException {
        ImageData imageData= filesRepo.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .id(filesRepo.findAll().size()+1)
                .imageData(ImageUtils.compressImage(file.getBytes())).build());
        if (imageData!=null){
            return "file uploaded "+ file.getOriginalFilename();
        }
        return "error while uploading";

    }

    public byte[] downloadImage(String fileName) {
        Optional<ImageData> dbImageData = filesRepo.findByName(fileName);
        byte[] images = ImageUtils.decompressImage(dbImageData.get().getImageData());
        return images;
    }

}
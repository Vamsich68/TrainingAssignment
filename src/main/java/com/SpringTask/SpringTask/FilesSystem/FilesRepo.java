package com.SpringTask.SpringTask.FilesSystem;

import com.SpringTask.SpringTask.Models.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilesRepo extends JpaRepository<ImageData,Integer> {
    Optional<ImageData> findByName(String fileName);
    //Optional<ImageData> findByName(String fileName);
}

package com.example.RottenTangerines.Service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
public class FileImageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    // 파일 등록
    public String store(MultipartFile mf) {

        if(mf == null || mf.isEmpty()) return null;

        try{
            //운영체제에 맞는 경로 객체 생성
            Path uploadPath = Paths.get(uploadDir);

            String originalName = mf.getOriginalFilename();
            String newName = UUID.randomUUID() + "-" + originalName;
            Path target = uploadPath.resolve(newName);

            mf.transferTo(target.toFile());

            return "/uploads/" + newName;

        } catch (Exception e){
            log.error("오류: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 저장 실패");
        }
    }

    // 파일 삭제
    public void deleteposter (String posterPath){

        if(posterPath == null || posterPath.isBlank()) return;

        String fileName = posterPath.replace("/uploads/","");

        File file = new File(uploadDir + "/" + fileName);

        if(file.exists()){
            boolean result = file.delete();
        }

    }

}

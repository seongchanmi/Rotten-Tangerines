package com.example.RottenTangerines.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.nio.file.Files;
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
            // static 폴더 아래 uploads 경로 설정
            Path uploadPath = Paths.get("src/main/resources/static/" + uploadDir);

            // 폴더가 없으면 생성
            if(!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String originalName = mf.getOriginalFilename();
            String newName = UUID.randomUUID() + "-" + originalName;
            Path target = uploadPath.resolve(newName);

            mf.transferTo(target.toFile());

            return "/" + uploadDir + "/" + newName;

        } catch (Exception e){
            log.error("오류: " + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 저장 실패");
        }
    }

    // 파일 삭제
    public void deleteposter (String posterPath){

        if(posterPath == null || posterPath.isBlank()) return;

        // posterPath 예: /uploads/uuid-filename.jpg
        String fileName = posterPath.replace("/" + uploadDir + "/","");

        // static 폴더 경로로 변경
        File file = new File("src/main/resources/static/" + uploadDir + "/" + fileName);

        if(file.exists()){
            boolean result = file.delete();
            if(result) {
                log.info("파일 삭제 완료: {}", fileName);
            } else {
                log.error("파일 삭제 실패: {}", fileName);
            }
        }
    }
}

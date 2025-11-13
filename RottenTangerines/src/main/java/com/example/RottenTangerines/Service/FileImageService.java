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
        try {
            // 1. Path 객체로 변환 (절대 경로 + normalize)
            Path uploadPath = Paths.get(uploadDir)
                    .toAbsolutePath()
                    .normalize();

            // 2. 디렉터리가 없으면 생성
            if (Files.notExists(uploadPath)) {
                Files.createDirectories(uploadPath);
                log.info("업로드 디렉터리 생성: {}", uploadPath);
            }

            //운영체제에 맞는 경로 객체 생성
            String originalName = mf.getOriginalFilename(); //원래 파일명
            String newName = UUID.randomUUID() + "-" + originalName; //uuid-원래이름 76..what?
            Path target = uploadPath.resolve(newName); // 경로 객체에 새로운 이름으로 연결
            //.transferTo() 저장. 한 번 호출 뒤 재호출 안됨
            mf.transferTo(target.toFile()); //원래 파일 경로는 path형태이나 로컬 저장을 위해 file 형태로 변환
            //바로 접근 가능한 URL로 반환
            return "/uploads/" + newName;
        } catch (Exception e) {
            log.error("오류 :" + e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "파일 저장 실패");
        }
    }

    // 파일 삭제
    public void deleteposter (String posterPath){

        if(posterPath == null || posterPath.isBlank()) return;

        // posterPath 예: /uploads/uuid-filename.jpg
        String fileName = posterPath.replace("/" + uploadDir + "/","");

        // static 폴더 경로로 변경
        File file = new File(uploadDir + "/" + fileName);



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

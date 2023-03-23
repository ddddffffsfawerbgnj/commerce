package com.example.commerce.serviece;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Log
public class FileService {

    /**
     * 이미지 파일 업로드
     *
     * @param uploadPath
     * @param originalFileName
     * @param fileData
     * @return
     * @throws Exception
     */
    public String uploadFile(String uploadPath,
                             String originalFileName, byte[] fileData) throws Exception {

        UUID uuid = UUID.randomUUID();
        String extension =
                originalFileName.substring(originalFileName.lastIndexOf("."));
        String savedFileName = uuid.toString() + extension; // 파일명

        String fileUploadFullUrl = uploadPath + "/" + savedFileName;

        FileOutputStream fileOutputStream =
                new FileOutputStream((fileUploadFullUrl));
        fileOutputStream.write(fileData);
        fileOutputStream.close();

        return savedFileName;
    }

    public void deleteFile(String filePath) throws Exception {

        File deleteFile = new File(filePath);

        if (deleteFile.exists()) {
            deleteFile.delete();
            log.info("이미지 파일 삭제 완료");
        } else {
            log.info("이미지 파일 없음");
        }
    }
}

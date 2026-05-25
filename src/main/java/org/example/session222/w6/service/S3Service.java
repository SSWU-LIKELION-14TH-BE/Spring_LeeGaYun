package org.example.session222.w6.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {

    private final S3Client s3Client;

    @Value("${cloud.aws.s3.bucket-name}")
    private String bucket;

    public String upload(MultipartFile multipartFile) throws IOException {

        String randomName = UUID.randomUUID().toString();
        String dirName = "images";
        String fileName = dirName + "/" + randomName;

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucket)
                .key(fileName)
                .contentType(multipartFile.getContentType())
                .build();

        s3Client.putObject(putObjectRequest, RequestBody.fromBytes(multipartFile.getBytes()));
        GetUrlRequest request = GetUrlRequest.builder().bucket(bucket).key(fileName).build();
        return s3Client.utilities().getUrl(request).toString();

    }
}

package org.com.allowlist.controller;

import com.opencsv.exceptions.CsvException;
import org.com.allowlist.CSVHelper;
import org.com.allowlist.ResponseMessage;
import org.com.allowlist.entity.ClienteAllowList;
import org.com.allowlist.service.CSVService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Controller
@RequestMapping("/api/csv")
public class CSVController {


    @Autowired
    private CSVService fileService;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) throws IOException, CsvException {
        String message = "";

            try {
                fileService.salvar(file);

                message = "Uploaded the file successfully: " + file.getOriginalFilename();

                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/csv/download/")
                        .path(file.getOriginalFilename())
                        .toUriString();

                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message,fileDownloadUri));
            } catch (Exception e) {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message,""));
            }
        }

}

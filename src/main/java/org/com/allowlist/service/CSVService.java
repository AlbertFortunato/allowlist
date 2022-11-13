package org.com.allowlist.service;

import com.opencsv.exceptions.CsvException;
import org.com.allowlist.CSVHelper;
import org.com.allowlist.entity.ClienteAllowList;
import org.com.allowlist.repository.EntidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@Service
public class CSVService {

    @Autowired
    private CSVHelper csvHelper;
    @Autowired
    EntidadeRepository repository;

    public void salvar(MultipartFile file) {
        try {
            List<ClienteAllowList> tutorials = csvHelper.clientAllowList(file.getInputStream());
            repository.saveAll(tutorials);
        } catch (IOException | CsvException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}

package org.com.allowlist;


import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.com.allowlist.entity.ClienteAllowList;
import org.com.allowlist.utils.ValidaCPF;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class CSVHelper {

    public List<ClienteAllowList> clientAllowList(InputStream inputStream) throws CsvException, IOException {

        Reader input = new InputStreamReader(inputStream);

        CSVReader reader = new CSVReaderBuilder(input).withSkipLines(1).build();

        readAllValidate(reader);

        List<String> exibir = new ArrayList<>();


        reader.close();
        input.close();
        return null;
    }
    public List<String> readAllValidate(CSVReader reader) throws IOException, CsvException {
        List<String []> strings = reader.readAll();
        Iterator<String[]> iterator = strings.iterator();

        List<String> erros = new ArrayList<>();
        List<String> validos = new ArrayList<>();

        int cont = 1;

        while(iterator.hasNext()) {

            String[] next = iterator.next();
            boolean valido = ValidaCPF.validar(next[1]);

            cont++;
            if (!valido) {
                erros.add(String.valueOf(valido));
                System.out.println("CPF's com erro : " + next[1] + " Linha :" + cont);
            } else
                validos.add(String.valueOf(valido));
        }
        return validos;
    }
}

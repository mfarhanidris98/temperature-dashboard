package com.mfbi.services;

import com.mfbi.entities.CsvEntity;
import com.mfbi.repositories.CsvRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@RequestScoped
public class CsvService {

    @Inject
    CsvRepository csvRepository;


    @Transactional
    public boolean save(InputStream inputStream){

        CsvEntity csvEntity = new CsvEntity();

        try (Scanner scanner = new Scanner(inputStream)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!checkInput(line)) scanner.nextLine();
                else {
                    List<String> rowList = new ArrayList<>(Arrays.asList(line.split(",")));

                    if ( rowList.size() == 7 ) {
//                        System.out.println("'" + rowList.get(2) + "' : '" + rowList.get(6) + "'");
                        csvEntity.addProperty(rowList.get(2),rowList.get(6));
                    } else if ( rowList.size() == 6 ) {
//                        System.out.println("'" + rowList.get(2) + "' : '" + rowList.get(5) + "'");
                        csvEntity.addProperty(rowList.get(2),rowList.get(5));
                    }


                }
                csvRepository.persist(csvEntity);

            }
        }
        return true;

    }

    public boolean checkInput(String str){
        return !str.matches("-+.+")
                && !str.matches("(Content-).+")
                && !str.matches("\"s")
                && !str.matches("(.+)(.csv)");
    }
}

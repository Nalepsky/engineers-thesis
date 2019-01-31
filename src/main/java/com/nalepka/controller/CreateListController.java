package com.nalepka.controller;

import com.itextpdf.text.*;
import com.nalepka.service.ArmyListService;
import com.nalepka.service.GeneratePdfService;
import com.nalepka.service.impl.ArmyListServiceImpl;
import com.nalepka.service.impl.GeneratePdfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/create")
public class CreateListController {
    @Autowired
    GeneratePdfServiceImpl createListService;

    @Autowired
    ArmyListServiceImpl armyListService;

    @RequestMapping(value = "/getpdf/{userId}", method = RequestMethod.POST)
    public ResponseEntity<byte[]> getPDF(@PathVariable("userId") Long userId, @RequestBody String json){
        byte[] content = new byte[0];

        try {
            content = createListService.createPdfFromJson(json).toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        armyListService.update(userId, json);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(content, headers, HttpStatus.ACCEPTED);
    }
}

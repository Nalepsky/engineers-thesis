package com.nalepka.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface GeneratePdfService {
    ByteArrayOutputStream createPdfFromJson(String json) throws IOException, DocumentException;
}

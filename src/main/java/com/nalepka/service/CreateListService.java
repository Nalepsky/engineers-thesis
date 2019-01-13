package com.nalepka.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

import java.io.IOException;

public interface CreateListService {
    Document createPdfFromJson(String json) throws IOException, DocumentException;
}

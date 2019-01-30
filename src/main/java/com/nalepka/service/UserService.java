package com.nalepka.service;

import com.itextpdf.text.DocumentException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface UserService {
   Long addUser(String email, String password);
   Long validateCredentials(String email, String password);
}

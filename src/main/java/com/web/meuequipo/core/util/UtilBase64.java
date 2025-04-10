package com.web.meuequipo.core.util;

import org.apache.tika.Tika;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class UtilBase64 {

    public static String getURIfromFile(String pathFile) throws IOException {
        File file = new File(pathFile);
        if (!file.exists()) {
            throw new IOException("El archivo no existe en la ruta especificada: " + pathFile);
        }

        String mimeType = getMimeType(file);
        if (mimeType == null) {
            throw new IOException("No se pudo determinar el mimeType del archivo: " + pathFile);
        }

        byte[] fileBytes = readFileBytes(file);
        String base64Data = Base64.getEncoder().encodeToString(fileBytes);

        return "data:" + mimeType + ";base64," + base64Data;
    }

    private static byte[] readFileBytes(File file) throws IOException {
        byte[] fileBytes = new byte[(int) file.length()];
        try (FileInputStream fis = new FileInputStream(file)) {
            fis.read(fileBytes);
        }
        return fileBytes;
    }

    private static String getMimeType(File file) throws IOException {
        Tika tika = new Tika();
        return tika.detect(file);
    }
}

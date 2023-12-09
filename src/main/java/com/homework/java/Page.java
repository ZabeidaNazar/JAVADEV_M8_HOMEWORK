package com.homework.java;

import java.io.FileReader;
import java.io.IOException;

public class Page {
    private String file;

    public Page(String path) {
        try (FileReader fileReader = new FileReader(path)) {
            char[] buf = new char[256];
            StringBuilder stringBuilder = new StringBuilder();
            int c;
            while ((c = fileReader.read(buf)) > 0) {
                stringBuilder.append(buf, 0, c);
            }
            file = stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFile() {
        return file;
    }
}

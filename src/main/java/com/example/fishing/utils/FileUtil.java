package com.example.fishing.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {
    // статический метод: три параметра: двоичный файл, путь к файлу, имя файла
    // Этот метод добавит указанный файл в указанный каталог
    public static void fileupload(/*byte[] file,*/ String filePath/*, String fileName*/) throws IOException {
        // целевой каталог
        File targetfile = new File(filePath);
        if (targetfile.exists()) {
            targetfile.mkdir();
        }

        // двоичный поток записи
        FileOutputStream out = new FileOutputStream(filePath);
        //out.write(file);
        out.flush();
        out.close();

    }
}

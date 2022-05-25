package com.example.project;

import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.util.List;


public class File_Handler {
    public static File getOrderDirectory(Order order) {
        return new File("Orders\\" + String.valueOf(order.getOrderId()));
    }

    public static void deleteOrder(Order order ) {
        File file = new File("Orders\\" + String.valueOf(order.getOrderId()));
        File files[] = file.listFiles();
        if(files.length > 0) {
            for (File f : files) {
                f.delete();
            }
        }
        file.delete();
    }

    public static void createOrderFile(List<File> files, Order order) throws SQLException, IOException {
        File newFile = new File("Orders\\" + String.valueOf(order.getOrderId()));
        newFile.mkdir();

        String path = newFile.getAbsolutePath() + "\\";
        System.out.println(path);
        if(files != null) {
            for(File file : files) {
                Files.copy(file.toPath(),
                        (new File(path + file.getName())).toPath(),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}

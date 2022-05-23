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
        new File("Orders\\" + String.valueOf(order.getOrderId())).delete();
    }
    public static void createOrderFile(List<File> files) throws SQLException, IOException {
        Singleton_Connector instance = Singleton_Connector.getInstance();
        int last = instance.getLastOrderID();
        File newFile = new File("Orders\\" + String.valueOf(last));
        newFile.mkdir();

        String path = newFile.getAbsolutePath() + "\\";
        System.out.println(path);
        for(File file : files) {
            Files.copy(file.toPath(),
                    (new File(path + file.getName())).toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }

    }
}

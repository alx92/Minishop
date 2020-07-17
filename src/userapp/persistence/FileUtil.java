package userapp.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil<T>{

    private File file;

    public FileUtil(String fileName) {
        file = new File(fileName);
    }

    public void write(List<T> users) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(users);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("IO exception!" + e.getMessage());
        }
    }

    public List<T> read() {
        List<T> users =  new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            users = (List<T>) objectInputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            System.out.println("Io exception occurred" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println("class not found" + e.getMessage());
        }

        return users;
    }
}
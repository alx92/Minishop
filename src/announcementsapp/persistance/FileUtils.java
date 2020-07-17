package announcementsapp.persistance;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils<T>
{
    private File file; // used both in write and read methods so we made it an instance field

    public FileUtils(String fileName)
    {
        file = new File(fileName);
    }

    public void write(List<T> announcementModels)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(announcementModels);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found!");
        }
        catch (IOException e)
        {
            System.out.println("IOException" + e.getMessage());
        }
    }

    public List<T> read()
    {
        List<T> announcementModels = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            announcementModels = (List<T>) objectInputStream.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch (IOException e)
        {
            System.out.println("IOException" + e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found" + e.getMessage());
        }

        return announcementModels;
    }
}

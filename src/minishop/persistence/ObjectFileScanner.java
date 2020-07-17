package minishop.persistence;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectFileScanner<T>
{
    private File file;

    public ObjectFileScanner(String fileName)
    {
        file = new File(fileName);
    }

    public void writeToFile(List<T> products)
    {
        try
        {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(products);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch (IOException e)
        {
            System.out.println("IO exception " + e.getMessage());
        }
    }

    public List<T> readFromFile()
    {
        List<T> products = new ArrayList<>();

        try
        {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            products = (List<T>)objectInputStream.readObject();
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (IOException e)
        {
            System.out.println("IO exception " + e.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Class not found! " + e.getMessage());
        }

        return products;
    }
}

package minishop.persistence.dao;

import minishop.persistence.ObjectFileScanner;
import minishop.persistence.model.Model;
import java.util.List;
import java.util.Optional;

public abstract class ModelDao<T extends Model> implements Dao<T>
{
    private String fileName;
    private ObjectFileScanner<T> genericFileReader;

    public ModelDao(String fileName)
    {
        this.fileName = fileName;
        genericFileReader = new ObjectFileScanner<T>(fileName);
    }

    @Override
    public List<T> getAll()
    {
        return genericFileReader.readFromFile();
    }

    @Override
    public void add(T object)
    {
        List<T> models = genericFileReader.readFromFile();

        models.add(object);

        genericFileReader.writeToFile(models);
    }

    @Override
    public Optional<T> findById(String id)
    {
        List<T> models = genericFileReader.readFromFile();

        return models.stream().filter(model -> model.getId().equals(id))
                .findFirst();
    }

    @Override
    public void remove(String id)
    {
        List<T> models = genericFileReader.readFromFile();
        models.removeIf(model -> model.getId().equals(id));
        genericFileReader.writeToFile(models);
    }

    @Override
    public void update(T updatedObject)
    {
        List<T> models = genericFileReader.readFromFile();

        for (int i = 0; i < models.size(); i++)
        {
            T oldObject = models.get(i);

            if (oldObject.getId().equals(updatedObject.getId()))
                models.remove(oldObject);
                models.add(updatedObject);
        }

        genericFileReader.writeToFile(models);
    }
}

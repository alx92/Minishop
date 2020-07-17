package announcementsapp.persistance.dao;

import announcementsapp.persistance.FileUtils;
import announcementsapp.persistance.model.AnnouncementModel;

import java.util.List;
import java.util.Optional;

// CRUD methods implementation
public class AnnouncementDao
{
    private FileUtils<AnnouncementModel> fileUtils = new FileUtils("announcements.txt");

    // Create
    public void addAnnouncement(AnnouncementModel newAnnouncement)
    {
        List<AnnouncementModel> announcementModels = fileUtils.read();
        announcementModels.add(newAnnouncement);

        fileUtils.write(announcementModels);
    }

    // Read for all elements
    public List<AnnouncementModel> getAll()
    {
        return fileUtils.read();
    }

    // Read for one single element
    public Optional<AnnouncementModel> findById(long id)
    {
        List<AnnouncementModel> announcementsModel = fileUtils.read();

        return announcementsModel.stream().filter(announcement -> announcement.getId() == id)
                .findFirst();
    }

    // Update
    public void update(AnnouncementModel updatedAnnouncement)
    {
        List<AnnouncementModel> announcementModels = fileUtils.read();
        Optional<AnnouncementModel> announcementModelOptional = announcementModels.stream()
                .filter(announcement -> announcement.getId() == updatedAnnouncement.getId())
                .findFirst();

        if (announcementModelOptional.isPresent())
        {
            AnnouncementModel announcementsModels = announcementModelOptional.get();
            announcementModels.remove(announcementsModels);
            announcementModels.add(updatedAnnouncement);
        }
        fileUtils.write(announcementModels);
    }

    // Delete
    public void delete(int id)
    {
        List<AnnouncementModel> announcementsModel = fileUtils.read();
        announcementsModel.removeIf(announcementModel -> announcementModel.getId() == id);
        fileUtils.write(announcementsModel);
    }
}

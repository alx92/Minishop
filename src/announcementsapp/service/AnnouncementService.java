package announcementsapp.service;

import announcementsapp.persistance.dao.AnnouncementDao;
import announcementsapp.persistance.model.AnnouncementModel;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class AnnouncementService
{
    private AnnouncementDao announcementDao = new AnnouncementDao();

    public void addAnnouncement(AnnouncementModel newAnnouncement)
    {
        newAnnouncement.setId(System.currentTimeMillis());
        announcementDao.addAnnouncement(newAnnouncement);
    }

    public List<AnnouncementModel> getAnnouncement()
    {
        List<AnnouncementModel> announcementModels = announcementDao.getAll();
        // this is easier but there is a more elegant way
//        announcementModels.sort((announcement1, announcement2) -> ((Double) announcement1.getPrice())
//                .compareTo((Double) announcement2.getPrice()));

        // a better way is using Comparator
        // announcementModels.sort(Comparator.comparing(announcement -> ((Double) announcement.getPrice())));

        // or, even better, with a method reference
        announcementModels.sort(Comparator.comparing(AnnouncementModel::getPrice));

        return announcementModels;
    }

    public void changePrice(double newPrice, long id)
    {
        Optional<AnnouncementModel> announcementModelOptional = announcementDao.findById(id);

        if (announcementModelOptional.isPresent())
        {
            AnnouncementModel announcementModel = announcementModelOptional.get();
            announcementModel.setPrice(newPrice);
            announcementDao.update(announcementModel);
        }
    }
}

package announcementsapp.persistance.model;

public class RealEstateAnnouncementModel extends AnnouncementModel
{
    private RealEstateType estateType;
    private double area;

    public RealEstateType getEstateType()
    {
        return estateType;
    }

    public void setEstateType(RealEstateType estateType)
    {
        this.estateType = estateType;
    }

    public double getArea()
    {
        return area;
    }

    public void setArea(double area)
    {
        this.area = area;
    }
}

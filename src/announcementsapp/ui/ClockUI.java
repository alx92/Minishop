package announcementsapp.ui;

import java.util.Date;

public class ClockUI extends Thread
{
    @Override
    public void run()
    {
        while (true)
        {
            System.out.println(new Date());

            try
            {
                sleep(3000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}

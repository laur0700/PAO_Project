import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Audit
{
    public void WriteToAudit(String[] Content)
    {
        File csvFile = new File("C:\\Users\\LAUR\\Desktop\\Java Stuff\\ProiectPao\\audit.csv");
        try
        {
            FileWriter outputfile = new FileWriter(csvFile, true);

            CSVWriter writer = new CSVWriter((outputfile));

            writer.writeNext(Content);

            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void WriteToDB(String Content)
    {
        Driver driver = new Driver();
        driver.WriteToAudit(Content);
    }
}

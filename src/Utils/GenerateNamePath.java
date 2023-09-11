package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class GenerateNamePath {
    public String generatePathToSaveInfo(String type_of_name, String desktop_path ){
        Random random = new Random();
        LocalDateTime myDateObj = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = myDateObj.format(myFormatObj);
        String uniqueId = String.format("%04d", random.nextInt(1000));
        return  desktop_path + "/" + type_of_name +"-"+formattedDate.replace("/","-") + "-" + uniqueId+ ".pdf";
    }
}

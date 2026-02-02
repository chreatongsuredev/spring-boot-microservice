package Authentication.example.Authentication.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class UniqueKey {

    public static String UniKey(){
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Format the date and time as 'yyyyMMddHHmmss'
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDate = now.format(formatter);

        // Generate a random UUID
        String uuid = UUID.randomUUID().toString();

        return formattedDate + "-" + uuid;
    }

}

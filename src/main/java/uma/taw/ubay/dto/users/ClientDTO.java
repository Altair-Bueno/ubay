package uma.taw.ubay.dto.users;

import lombok.Value;
import uma.taw.ubay.entity.GenderEnum;

import java.sql.Date;

/**
 * @author José Luis Bueno Pachón
 */

@Value
public class ClientDTO {
    int id;
    String name;
    String lastName;
    GenderEnum gender;
    String address;
    String city;
    Date birthDate;
}

package uma.taw.ubay.dto;

import lombok.Value;
import uma.taw.ubay.entity.KindEnum;
/**
 * @author Altair Bueno
 */

@Value
public class LoginDTO {
    String username;
    KindEnum kind;
}

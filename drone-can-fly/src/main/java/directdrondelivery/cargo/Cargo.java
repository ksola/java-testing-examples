package directdrondelivery.cargo;

import lombok.Getter;
import lombok.Setter;

public class Cargo {
    @Getter @Setter private Integer cargoID;
    @Getter @Setter private String cargoName;
    @Getter @Setter private String recipientName;
    @Getter @Setter private String recipientPhone;
    @Getter @Setter private String recipientEmail;
}

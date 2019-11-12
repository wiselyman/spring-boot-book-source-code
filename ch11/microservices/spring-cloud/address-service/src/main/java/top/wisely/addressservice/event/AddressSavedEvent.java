package top.wisely.addressservice.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressSavedEvent {
    private Long id;
    private String province;
    private String city;
}

package top.wisely.reactivestreamproducer.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageEvent {
    private String msg;
    private Date time;
}

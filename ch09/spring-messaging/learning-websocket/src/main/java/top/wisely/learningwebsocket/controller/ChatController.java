package top.wisely.learningwebsocket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class ChatController {

    SimpMessagingTemplate simpMessagingTemplate;

    public ChatController(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @MessageMapping("/chat/{to}")
    @SendTo("/topic/status")
    public String chat(Principal principal,
                       @RequestBody String msg,
                       @DestinationVariable  String to){
        simpMessagingTemplate.convertAndSendToUser(to,"/topic/response", msg);
        return principal.getName() + " 发送的 " + msg + " 消息已送达给" + to;
    }

    @RequestMapping("/user")
    @ResponseBody
    public String user(Principal principal){
        return principal.getName();
    }
}

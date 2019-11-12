package top.wisely.learningspringintegration.service;


import org.springframework.integration.file.FileHeaders;
import org.springframework.messaging.handler.annotation.Header;

public interface SendingGateway {
    void send(@Header(FileHeaders.FILENAME) String filename, String content);
}

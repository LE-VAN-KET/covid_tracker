package com.coranavirus.coronavirus.service;

public interface EmailService {
    void sendMail(String subject, String body, String recipientAddress, String senderAddress);
}

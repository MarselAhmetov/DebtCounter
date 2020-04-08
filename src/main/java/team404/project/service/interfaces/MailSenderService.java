package team404.project.service.interfaces;


import team404.project.model.MailMessage;

public interface MailSenderService {
    void sendMail(MailMessage mailMessage);
}

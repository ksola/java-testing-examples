package directdronedelivery.drone.services;

public interface MessagingService {
    public void sendSMS(String messageText, String phone);
    public void sendEmail(String messageText, String email);
}

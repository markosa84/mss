package hu.ak_akademia.mss.service;

import hu.ak_akademia.mss.model.Appointment;
import hu.ak_akademia.mss.model.RegistrationVerificationCode;
import hu.ak_akademia.mss.model.user.MssUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class EmailService {

    private String uniqueCode;
    private final JavaMailSender emailSender;

    @Autowired
    private final TemplateEngine templateEngine;

    @Autowired
    private RegistrationVerificationCodeService registrationVerificationCodeService;

    @Autowired
    public EmailService(JavaMailSender emailSender, TemplateEngine templateEngine) {
        this.emailSender = emailSender;
        this.templateEngine = templateEngine;
    }

    private void generateUniqueCode() {
        UUID uuid = UUID.randomUUID();
        uniqueCode = uuid.toString();
    }

    public void sendRegistrationEmail(String email) throws MessagingException {
        generateUniqueCode();
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("akmss.project@gmail.com");
        helper.setTo(email);
        helper.setSubject("Regisztráció megerősítése");
        Context context = new Context();
        context.setVariable("generatedCode", uniqueCode);
        String processedHtmlContent = templateEngine.process("activate_registration.html", context);
        helper.setText(processedHtmlContent, true);
        emailSender.send(message);
    }

    public void saveRegistrationVerificationCode(MssUser user) {
        RegistrationVerificationCode verificationCode = new RegistrationVerificationCode();
        verificationCode.setVerificationCode(uniqueCode);
        verificationCode.setUser(user);
        verificationCode.setExpiryDate(LocalDate.now().plusDays(1));
        registrationVerificationCodeService.saveVerificationCode(verificationCode);
    }

    public void sendAppointmentConfirmationEmail(String email, String appointmentDetails) throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("akmss.project@gmail.com");
        helper.setTo(email);
        helper.setSubject("Foglalás megerősítése");
        Context context = new Context();
        context.setVariable("appointmentDetails", appointmentDetails);
        String processedHtmlContent = templateEngine.process("appointment_confirmation.html", context);
        helper.setText(processedHtmlContent, true);
        emailSender.send(message);
    }

    public String getEmailContent(Appointment appointment, String doctorName) {
        return "Kedves %s ! Köszönjük a foglalást. A foglalás a következő időpontban lett rögzítve: %s - %s. Szakirány: %s Orvosnál : %s"
                .formatted(appointment.getMssUserClient().getFirstName() + " " + appointment.getMssUserClient().getLastName(),
                        appointment.getStartDate(),
                        appointment.getEndDate(),
                        appointment.getAreaOfExpertise().getName(),
                        doctorName);
    }
}

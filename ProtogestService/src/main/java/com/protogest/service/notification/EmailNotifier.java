package com.protogest.service.notification;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.protogest.service.util.config;

public class EmailNotifier {

    // The subject line for the email.
    static final String SUBJECT = "You received an invitation from a colleague";

    // The HTML body for the email.
    static final String HTMLBODY = "A colleague has linked your email with a court case." +
            "To view and edit the document, please "
            + "<a href='%1$s'>login</a> "
            + "or <a href='%1$s/signup'>create an account</a> "
            + "with Protogest";

    // The email body for recipients with non-HTML email clients.
    static final String TEXTBODY = "This email was sent through Protogest automatic email service. " +
            "Please do not reply to this email.";

    public static void senInvitationEmailTo(String to) {

        try {
            AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
                    .withRegion(Regions.fromName(config.get().getEmail().getRegion())).build();
            SendEmailRequest request = new SendEmailRequest()
                    .withDestination(
                            new Destination().withToAddresses(to))
                    .withMessage(new Message()
                            .withBody(new Body()
                                    .withHtml(new Content()
                                            .withCharset("UTF-8").withData(String.format(HTMLBODY, config.get().getEmail().getFrom())))
                                    .withText(new Content()
                                            .withCharset("UTF-8").withData(TEXTBODY)))
                            .withSubject(new Content()
                                    .withCharset("UTF-8").withData(SUBJECT)))
                    .withSource(config.get().getEmail().getFrom());
                    // Comment or remove the next line if you are not using a
                    // configuration set
//                    .withConfigurationSetName(CONFIGSET);
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex) {
            System.out.println("The email was not sent. Error message: "
                    + ex.getMessage());
        }
    }

}

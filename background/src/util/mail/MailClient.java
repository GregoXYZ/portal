package util.mail;

import java.util.Properties;
import java.util.ResourceBundle;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import common.Constants;
import common.business.BusinessException;
import common.business.bo.UsuariosBO;
import common.dto.MailsDTO;
import common.dto.UsuariosDTO;
import common.util.spring.SpringUtil;

public class MailClient {	

	private static Session session;
	private static MailClient _instance = null;
	private static ResourceBundle config;
	private static UsuariosBO usuariosBO = (UsuariosBO) SpringUtil.getInstance().getBean("UsuariosBO");

	// Miembros de la clase
	// Inicializador de clase
	static {
		_instance = new MailClient();
	}

	/**
	 * Acceso al Singleton
	 * 
	 * @return MailClient
	 */
	public static MailClient getInstance() {
		/*
		 * if (_instance == null) { _instance = new MailClient(); }
		 */
		return _instance;
	}

	/**
	 * Constructor privado
	 */
	private MailClient() {
		// Accedemos al fichero de properties
		config = ResourceBundle.getBundle("mail");
		
		boolean debug = false;
		
		// Setup mail server
		Properties props = new Properties();
		props.put("mail.smtp.host", config.getString("SMTP_HOST_NAME"));
		props.put("mail.smtp.auth", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.port", config.getString("SMTP_PORT"));
		props.put("mail.smtp.socketFactory.port", config.getString("SMTP_PORT"));
		props.put("mail.smtp.socketFactory.class", config.getString("SSL_FACTORY"));
		props.put("mail.smtp.socketFactory.fallback", "false");		
		//props.put("mail.smtp.starttls.enable", "true");

		// Get a mail session
		session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								config.getString("USER"), config.getString("PASSWORD"));
					}
				});
		
		session.setDebug(debug);
	}

	public void send(MailsDTO mail) throws MessagingException, AddressException, BusinessException {
		UsuariosDTO userFrom = usuariosBO.getByPrimaryKey(mail.getUsuFkOrigen());
		String nombre = userFrom.getUsuNombre() + " " + userFrom.getUsuApellido1() + " " + userFrom.getUsuApellido2();
		String subject = "";
		String body = "";
		String uri = "";
		if (mail.getTipAviFk().compareTo(Constants.AVISO_COMPARTIDOS) == 0)
		{
			subject = "[COMPARTIDOS] Nuevos archivos disponibles";
			body = nombre + " ha compartido " + mail.getCantidad() + " archivo(s) contigo.";
			uri = "https://SocialFiles.dnsalias.net:8443/contenidos/compartidos.do";
		}
		else if (mail.getTipAviFk().compareTo(Constants.AVISO_AMISTAD) == 0)
		{
			subject = "[RELACIÓN] Solicitud de amistad";
			body = nombre + " desea ponerse en contacto contigo.";			
			uri = "https://SocialFiles.dnsalias.net:8443/portal";
		}
		else if (mail.getTipAviFk().compareTo(Constants.AVISO_AMISTAD_ACEPTDA) == 0)
		{
			subject = "[RELACIÓN ACEPTADA] Solicitud de amistad";
			body = nombre + " a aceptado tu solicitud.";			
			uri = "https://SocialFiles.dnsalias.net:8443/portal";
		}
		else if (mail.getTipAviFk().compareTo(Constants.AVISO_FORO) == 0)
		{
			subject = "[FORO] Nuevo aviso en el foro";
			body = nombre + " ha escrito " + mail.getCantidad() + " mensaje(s) en el foro.";
			uri = "https://SocialFiles.dnsalias.net:8443/foros/foro.do";
		}
		else if (mail.getTipAviFk().compareTo(Constants.AVISO_MENSAJE) == 0)
		{
			subject = "[MENSAJE] Nuevo mensaje";
			body = nombre + " te ha escrito " + mail.getCantidad() + " mensaje(s).";
			uri = "https://SocialFiles.dnsalias.net:8443/foros/foro.do";
		}
		
		body += "\n\n" + "Visita " + uri;
		
		send(mail.getUsuFrom(), mail.getUsuTo(), 
				subject, 
				body , null);
}
	
	private void send(String from, String to, String subject, String body, String[] attachments)
			throws MessagingException, AddressException {
		
		// Define a new mail message
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		message.setSubject(subject);

		// Create a message part to represent the body text
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText(body);

		// use a MimeMultipart as we need to handle the file attachments
		Multipart multipart = new MimeMultipart();

		// add the message body to the mime message
		multipart.addBodyPart(messageBodyPart);

		// add any file attachments to the message
		if ( attachments!=null && attachments.length >0 )
			addAtachments(attachments, multipart);

		// Put all message parts in the message
		message.setContent(multipart);

		// Send the message
		Transport.send(message);

	}

	protected static void addAtachments(String[] attachments, Multipart multipart)
			throws MessagingException, AddressException {
		for (int i = 0; i <= attachments.length - 1; i++) {
			String filename = attachments[i];
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();

			// use a JAF FileDataSource as it does MIME type detection
			DataSource source = new FileDataSource(filename);
			attachmentBodyPart.setDataHandler(new DataHandler(source));

			// assume that the filename you want to send is the same as the
			// actual file name - could alter this to remove the file path
			attachmentBodyPart.setFileName(filename);

			// add the attachment
			multipart.addBodyPart(attachmentBodyPart);
		}
	}
}

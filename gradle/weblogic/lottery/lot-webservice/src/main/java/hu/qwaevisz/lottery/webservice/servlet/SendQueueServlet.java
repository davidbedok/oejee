package hu.qwaevisz.lottery.webservice.servlet;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/QueueServlet")
public class SendQueueServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SendQueueServlet.class.getName());

    private static final String CONNECTION_FACTORY_JNDI = "javax.jms.QueueConnectionFactory";
    private static final String QUEUE_JNDI = "jms/queue/lotteryqueue";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("Send Queue Servlet");

        response.setContentType("text/html");
        ServletOutputStream out = response.getOutputStream();
        String message = request.getParameter("message");
        if (message.equals("")) {
            out.print("Enter a message to post to the queue");
        } else {
            try {
                this.send(message);
                out.print("Your message has been posted");
            } catch (Exception e) {
                LOGGER.info("error " + e);
                System.out.print("error " + e);
            }
        }
    }

    private void send( String message ) throws NamingException, JMSException {
        InitialContext context = new InitialContext();
        QueueConnectionFactory connectionFactory = (QueueConnectionFactory) context.lookup(CONNECTION_FACTORY_JNDI);
        QueueConnection connection = connectionFactory.createQueueConnection();
        QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue) context.lookup(QUEUE_JNDI);
        QueueSender sender = session.createSender(queue);
        TextMessage textMessage = session.createTextMessage();

        connection.start();
        textMessage.setText(message);
        sender.send(textMessage);
        LOGGER.info("The message, "+ message +", has been sent to the '" + QUEUE_JNDI + "'.");

        sender.close();
        session.close();
        connection.close();
    }

}

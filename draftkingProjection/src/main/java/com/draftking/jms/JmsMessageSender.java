package com.draftking.jms;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;
 
@Service("messageProducer")
public class JmsMessageSender {
 
  //@Autowired
  private JmsTemplate jmsTemplate;
   
   
  /**
   * send text to default destination
   * @param text
   */
  public void send(final String text) throws JMSException{
     try{
    this.jmsTemplate.send(new MessageCreator() {
    	
      public Message createMessage(Session session) throws JMSException {
        TextMessage message = session.createTextMessage(text);
        return message;
      }
    });
     }
    catch(Exception e){
    	e.printStackTrace();
    }
  }
   
  /**
   * Simplify the send by using convertAndSend
   * @param text
   */
  public void sendText(final String text){
    this.jmsTemplate.convertAndSend(text);
  }
   
  /**
   * Send text message to a specified destination
   * @param text
   */
  public void send(final Destination dest,final String text) {
     
    this.jmsTemplate.send(dest,new MessageCreator() {
    
      public Message createMessage(Session session) throws JMSException {
        Message message = session.createTextMessage(text);
        return message;
      }
    });
  }
}
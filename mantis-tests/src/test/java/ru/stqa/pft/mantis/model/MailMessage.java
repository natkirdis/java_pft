package ru.stqa.pft.mantis.model;

public class MailMessage {

  public String to; //Кому пришло письмо
  public String text; //Текст письма

  public MailMessage(String to, String text){
    this.to = to;
    this.text = text;
  }

}
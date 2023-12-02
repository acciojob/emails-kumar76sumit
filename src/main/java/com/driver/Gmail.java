package com.driver;

import java.util.*;

class Mail
{
    Date date;
    String senderName;
    String message;

    public Mail(Date date, String senderName, String message) {
        this.date = date;
        this.senderName = senderName;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getMessage() {
        return message;
    }
}
public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)

    Deque<Mail> inbox;
    Deque<Mail> trash;
    Stack<Mail> st;
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        this.inbox=new ArrayDeque<>();
        this.trash=new ArrayDeque<>();
        this.st=new Stack<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()>=inboxCapacity)
        {
            Mail oldMail=inbox.removeFirst();
            trash.addLast(oldMail);
        }
        Mail newMail=new Mail(date,sender,message);
        inbox.addLast(newMail);
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        while(inbox.size()!=0 && !inbox.getFirst().message.equals(message))
        {
            st.push(inbox.removeFirst());
        }
        if(inbox.size()!=0)
        {
            trash.addLast(inbox.removeFirst());
        }
        while(st.size()!=0)
        {
            inbox.addFirst(st.pop());
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size()==0)
        {
            return null;
        }
        return inbox.getLast().message;
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0)
        {
            return null;
        }
        return inbox.getFirst().message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
//        int count=0;
//        while(inbox.size()!=0)
//        {
//            Mail currMail=inbox.removeFirst();
//            if(currMail.date.after(start) && currMail.date.before(end))
//            {
//                count++;
//            }
//            st.push(currMail);
//        }
//        while(st.size()!=0)
//        {
//            inbox.addFirst(st.pop());
//        }
//        return count;
        int count = 0;

        for (Mail mail : inbox) {
            Date mailDate = mail.getDate();
            if (mailDate.equals(start) || (mailDate.after(start) && mailDate.before(end)) || mailDate.equals(end)) {
                count++;
            }
        }

        return count;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}

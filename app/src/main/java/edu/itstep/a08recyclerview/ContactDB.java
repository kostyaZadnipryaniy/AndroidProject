package edu.itstep.a08recyclerview;

import java.util.ArrayList;

public class ContactDB {
    //make class
    public static ArrayList<Contact> contacts = new ArrayList<Contact>();

    public static void addContact(Contact contact){contacts.add(contact);}
    public static ArrayList<Contact> getContacts() {
        return contacts;
    }
    public static Contact getContactById(int id){
        return contacts.get(id);
    }

    public static void setContactById(int id, Contact contact){ contacts.set(id, contact);}
    public static void removeContactById(int id){
        contacts.remove(id);
    }

}



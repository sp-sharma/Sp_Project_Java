package org.example.modules;

public class studentinfo {
    int id;
    String name;
    int rollno;
    long contact;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getRollno() {
        return rollno;
    }

    public long getContact() {
        return contact;
    }

    public int getGrade() {
        return grade;
    }

    int grade;
}

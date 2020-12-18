package com.company;
import org.apache.commons.codec.digest.DigestUtils;
import javax.swing.*;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

class GUI implements ActionListener {

    double finalPrice;
    JFrame frame;
    JPanel panel;
    JButton submit;
    JLabel nameLabel;
    JTextField name;
    String[] gradeArray;
    JComboBox gradeCombo;
    JLabel studentIDLabel;
    JTextField studentID;
    JLabel gradeLabel;
    JLabel ticketLabel;
    String[] ticketArray;
    String finalString;
    JButton comeBack;
    JComboBox ticketCombo;

    int[] soloArray = {40,45,55,65};
    int[] coupleArray = {60,68,83,98};
    int[] groupArray = {100,113,138,163};
    int[] yourArray;

    public GUI(){

        frame = new JFrame();
        panel = new JPanel();
        submit = new JButton("Submit");
        nameLabel = new JLabel("Name");name = new JTextField();
        gradeArray = new String[] {"Senior","Junior","Sophomore","Freshman"};
        gradeCombo = new JComboBox(gradeArray);
        studentIDLabel = new JLabel("Student-Id");
        studentID = new JTextField();
        gradeLabel = new JLabel("Grade");
        ticketLabel = new JLabel("Ticket Type");
        ticketArray = new String[] {"Solo","Couple","Group(3-4)"};
        ticketCombo = new JComboBox(ticketArray);
        comeBack = new JButton("Buy Another");


        panel.setBorder(BorderFactory.createBevelBorder(1,Color.blue,Color.gray));
        panel.setPreferredSize(new Dimension(500,200));
        panel.setLayout(new GridLayout(5,5));
        panel.add(nameLabel);
        panel.add(name);
        panel.add(studentIDLabel);
        panel.add(studentID);
        panel.add(gradeLabel);
        panel.add(gradeCombo);
        panel.add(ticketLabel);
        panel.add(ticketCombo);
        panel.add(submit);
        submit.addActionListener(this);


        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Ticket Booth");
        frame.pack();
        frame.setVisible(true);

    }

    public void calculate(){
        String calcGrade = gradeCombo.getSelectedItem().toString();
        String calcTicket = ticketCombo.getSelectedItem().toString();
        if(calcTicket.startsWith("S")){
            yourArray = soloArray;
        }else if(calcTicket.startsWith("C")){
            yourArray=coupleArray;
        }else{
            yourArray=groupArray;
        }
        if(calcGrade.startsWith("Se")){
            finalPrice = yourArray[0];
        }else if(calcGrade.startsWith("J")){
            finalPrice = yourArray[1];
        }else if(calcGrade.startsWith("So")){
            finalPrice = yourArray[2];
        }else{
            finalPrice = yourArray[3];
        }finalString = (name.getText() + " has purchased a " + calcGrade.substring(0,1).toUpperCase() + calcGrade.substring(1) + ", " + calcTicket.substring(0,1).toUpperCase() + calcTicket.substring(1) + " prom ticket for $" + finalPrice);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        calculate();
        panel.removeAll();
        panel.updateUI();
        panel.add(new JLabel(finalString));
        panel.add(new JLabel("Here is your confirmation code: " + new Hasher().result));
        panel.add(comeBack);
        comeBack.addActionListener(new AbstractAction("goBack") {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new GUI();
            }
        });


    }
}

class Hasher{

        int before;
        String result;

    public Hasher(){
        before = (int) (Math.random() * (Math.random() + ((int) Math.random() * 10)) * 500);
        result = DigestUtils.sha256Hex(Integer.toString(before));
        result = result.substring(0,6);
    }
}

class ticketBooth{

    Scanner scanner = new Scanner(System.in);

    String grade;
    boolean properGrade = false;
    String ticketType;
    String name;
    String schoolID;
    boolean properTicket = false;
    int[] soloArray = {40,45,55,65};
    int[] coupleArray = {60,68,83,98};
    int[] groupArray = {100,113,138,163};
    int[] yourArray;
    int price;

    public ticketBooth(){
        System.out.println("Hello, what is your name?");
        name = scanner.next();
        System.out.println("Ok " + name + ", confirm by entering your school-ID");
        schoolID = scanner.next();

        while (!properGrade) {
            System.out.println(name + ", are you buying a, 'Solo', 'Couple', or 'Group'(3-4) persons ticket?");
            ticketType = scanner.next().toLowerCase();
            if (ticketType.startsWith("s")){
                ticketType = "solo";
                yourArray = soloArray;
                properGrade = true;
            }
            if (ticketType.startsWith("c")){
                ticketType = "couple";
                yourArray = coupleArray;
                properGrade = true;
            }
            if (ticketType.startsWith("g")){
                ticketType = "group";
                yourArray = groupArray;
                properGrade = true;
            }
        }

        while (!properTicket) {
            System.out.println(name + ", what grade are you in?");
            grade = scanner.next().toLowerCase();
            if (grade.startsWith("se")){
                grade = "senior";
                price = yourArray[0];
                properTicket = true;
            }
            if (grade.startsWith("so")){
                grade = "sophomore";
                price = yourArray[1];
                properTicket = true;
            }
            if (grade.startsWith("j")){
                grade = "junior";
                price = yourArray[2];
                properTicket = true;
            }
            if (grade.startsWith("f")){
                grade = "freshman";
                price = yourArray[3];
                properTicket = true;
            }
        }
        System.out.println(name + " purchased a " + grade + ", " + ticketType + " ticket for $" + price);
        System.out.println("Your confirmation key is: " + new Hasher().result);
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("Would you like the \"Text Based Interface\" or our \"Guided User Interface\" to complete this transaction? \n0 for text, 1 for GUI.");
        int answer = new Scanner(System.in).nextInt();
        if(answer == 0){
            new ticketBooth();
        }else{
            new GUI();
        }
    }
}

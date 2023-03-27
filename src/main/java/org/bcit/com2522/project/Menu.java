package org.bcit.com2522.project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {




    public static void main (String[] args) {



        //setting up the window
        JFrame frame = new JFrame();
        frame.setTitle("Menu");
        frame.setSize(800, 425);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        //making the menu bar
        JMenuBar mb = new JMenuBar();
        frame.setJMenuBar(mb);

        //making the buttons
        JMenu play = new JMenu("Play");
        JMenu instructions = new JMenu("Instructions");
        JMenu highScores = new JMenu("High Scores");
        JMenu exit = new JMenu("Exit");

        //making a font object for the menu
        Font mainfont = new Font("Algerian", Font.PLAIN, 65);

        //changing the fonts
        play.setFont(mainfont);
        instructions.setFont(mainfont);
        highScores.setFont(mainfont);
        exit.setFont(mainfont);


        //changing the layout of the menubar to be vertical and centered
        mb.setLayout(new BoxLayout(mb, BoxLayout.Y_AXIS));



        mb.add(play);
        mb.add(Box.createVerticalStrut(10)); //This adds space in between the buttons
        mb.add(instructions);
        mb.add(Box.createVerticalStrut(10));
        mb.add(highScores);
        mb.add(Box.createVerticalStrut(10));
        mb.add(exit);

        //Changing the colors used in the menu
        mb.setBackground(Color.black);

        Color textcolor = new Color(192, 192, 192);
        play.setForeground(textcolor);
        instructions.setForeground(textcolor);
        highScores.setForeground(textcolor);
        exit.setForeground(textcolor);

        //attempt to add hover effects, does not work
//        ImageIcon pogtest = new ImageIcon("C:/Users/anov/Downloads/assets/pogchamp.png");
//        play.setRolloverEnabled(true);
//        play.setRolloverIcon(pogtest);


        //Toggles visibility of the menu, must always be at the end of the code or else it might not work properly
        frame.setVisible(true);


        //making the instructions page


        JFrame frame2 = new JFrame();
        frame2.setTitle("Instructions");
        frame2.setSize(800, 425);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar mbtext = new JMenuBar();
        frame2.setJMenuBar(mbtext);

        JMenu line1 = new JMenu("Line1 Line1 Line1 Line1 Line1 Line1 Line1");
        JMenu line2 = new JMenu("Line2 Line2 Line2 Line2 Line2 Line2");
        JMenu line3 = new JMenu("Line3 Line3 Line3 Line3 Line3 Line3 ");
        JMenu line4 = new JMenu("Line4 Line4 Line4 Line4 Line4 Line4 Line4 ");

        Font instructionsfont = new Font("Algerian", Font.PLAIN, 35);

        line1.setFont(instructionsfont);
        line2.setFont(instructionsfont);
        line3.setFont(instructionsfont);
        line4.setFont(instructionsfont);

        mbtext.setLayout(new BoxLayout(mbtext, BoxLayout.Y_AXIS));

        mbtext.add(line1);
        mbtext.add(Box.createVerticalStrut(10));
        mbtext.add(line2);
        mbtext.add(Box.createVerticalStrut(10));
        mbtext.add(line3);
        mbtext.add(Box.createVerticalStrut(10));
        mbtext.add(line4);
        mbtext.add(Box.createVerticalStrut(150));



        mbtext.setBackground(Color.black);


        line1.setForeground(textcolor);
        line2.setForeground(textcolor);
        line3.setForeground(textcolor);
        line4.setForeground(textcolor);

        System.out.println("123");
        //event code (triggers instructions page to popup when button is clicked on menu) (does not work)

        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("clicked");
            }
        });

        System.out.println("345");



        }






}


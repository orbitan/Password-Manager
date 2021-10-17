package Gui;
import Gui.models.ExampleRecordFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.CardLayout;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class StartGui implements ActionListener {
    JPasswordField masterpasswortEingabe;
    JButton bestaetigen;
    JPanel panel;
    JLabel masterpasswort;
    JLabel falschesPasswort;
    JFrame frame;


    public void createAndShowGUI() {


        //Create and set up the window.
        frame = new JFrame("Passwortmanager");
        panel = new JPanel();
        masterpasswort = new JLabel("Bitte Masterpasswort eingeben");
        masterpasswortEingabe = new JPasswordField();
        bestaetigen = new JButton("Bestätigen");
        falschesPasswort = new JLabel("Falsches Passwort. Neuer Versuch bitte.");
        masterpasswort.setFont(new Font("Serif", Font.PLAIN, 20));
        Dimension size = masterpasswort.getPreferredSize();
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        masterpasswort.setBounds(250, 150, size.width, size.height);
        masterpasswortEingabe.setBounds(250, 200, size.width, size.height);
        bestaetigen.setBounds(250, 250, size.width, size.height);
        falschesPasswort.setBounds(250, 300, size.width, size.height);
        masterpasswortEingabe.setFont(new Font("Serif", Font.PLAIN, 20));
        frame.setSize(700, 500);
        frame.getContentPane().add(masterpasswort);

        //Display the window.
        frame.add(panel);
        panel.add(masterpasswort);
        panel.add(bestaetigen);
        panel.add(masterpasswortEingabe);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Check if password is valid.
        bestaetigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mp = String.valueOf(masterpasswortEingabe.getPassword());
                String password = "123";
                String enteredPassword = new String(masterpasswortEingabe.getPassword());
                if (password.equals(enteredPassword)) {
                    System.out.println("Richtiges Passwort");
                    panel.removeAll();
                    panel.validate();
                    panel.repaint();
                    createAndShowGUI2();
                } else {
                    System.out.println("Falsches Passwort");
                    panel.add(falschesPasswort);
                    panel.validate();
                    panel.repaint();
                }
                }
        });
    }

    public void createAndShowGUI2(){
        var examples = ExampleRecordFactory.getExampleRecords();
        JList websites = new JList(examples.getWebsiteList());
        panel.add(websites);

        JMenuBar mb;
        JMenu x;
        JMenuItem m1;
        mb = new JMenuBar();
        x = new JMenu("Menu");
        m1 = new JMenuItem("Neues Passwort hinzufügen");
        x.add(m1);
        mb.add(x);
        frame.setJMenuBar(mb);

        JPanel panel2 = new JPanel();
        JLabel username = new JLabel("Username");
        JLabel passwort = new JLabel("Passwort");
        Dimension size = username.getPreferredSize();
        username.setBounds(250, 150, size.width, size.height);
        passwort.setBounds(250, 200, size.width, size.height);

        panel2.add(passwort);
        panel2.add(username);
        panel.add(websites);

        JScrollPane sp = new JScrollPane(panel, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        websites.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                var index = websites.getSelectedIndex();
                var p = examples.get(index);
                username.setText(p.getUsername());
                passwort.setText(p.getPassword());
            }
        });

        JSplitPane s1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                websites, panel2);
        s1.setOneTouchExpandable(true);
        s1.setDividerLocation(150);

//Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);
        s1.setMinimumSize(minimumSize);
        s1.setMinimumSize(minimumSize);


        frame.add(s1);
        frame.setVisible(true);
        frame.add(panel);
        panel.validate();
        panel.repaint();


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.bestaetigen){
            String mp = String.valueOf(masterpasswortEingabe.getPassword());
            String password = "123";
            String enteredPassword = new String(masterpasswortEingabe.getPassword());
            if (password.equals(enteredPassword)) {
                System.out.println("Richtiges Passwort");
            } else {
                System.out.println("Falsches Passwort");
            }
        }
    }
    public static void main(String[] args) {

        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                StartGui s = new StartGui();
                s.createAndShowGUI();
            }
        });
    }
}

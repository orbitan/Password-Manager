package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class passwordGenerator {
    public int requiredLength;
    public boolean zahlen;
    public boolean letters;
    public boolean specialCases;




    public void  requiredTypes(){  // checkt, aus welchen Typen und wievielen Zeichen das Passwort bestehen soll;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Aus wievielen Zeichen soll das Passwort bestehen? ");  //Zeichenzahl
        int requiredLength = scanner.nextInt();
        this.requiredLength = requiredLength;

        System.out.println("Soll das Passwort Buchstaben enthalten? (Enter 'true' or 'false') "); //Buchstaben ja/nein
        boolean letters = scanner.nextBoolean();
        this.letters = letters;

        System.out.println("Soll das Passwort besondere Zeichen enthalten? (Enter 'true' or 'false') "); //Zeichen ja/nein
        boolean specialCases = scanner.nextBoolean();
        this.specialCases = specialCases;

        System.out.println("Soll das Passwort Zahlen enthalten? (Enter 'true' or 'false') ");
        boolean zahlen = scanner.nextBoolean();
        this.zahlen = specialCases;

        fertigGeneriertesPasswort(requiredLength, letters, specialCases, zahlen);
    }

    public void fertigGeneriertesPasswort(int requiredLength, boolean letters, boolean specialCases, boolean zahlen){ //generiert ein Passwort auf Basis der definierten Attribute
        List<Character> letterList = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        List<Character> specialCharactertest = new ArrayList<Character>(Arrays.asList('!', '#', '+', '*', '?', '&', '$', '-', '_'));
        List<Character> numbers = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        Random random = new Random();
        StringBuilder passwort = new StringBuilder();
        int i = 0;

        if(letters && specialCases && zahlen){
            letterList.addAll(specialCharactertest);
            letterList.addAll(numbers);
            while(i < requiredLength){
                int randomElement = random.nextInt(letterList.size());
                passwort.append(letterList.get(randomElement));
                i ++;
            }
            System.out.println(passwort);

        }else if(letters && zahlen){
            letterList.addAll(numbers);
            while(i < requiredLength){
                int randomElement = random.nextInt(letterList.size());
                passwort.append(letterList.get(randomElement));
                i ++;
            }
            System.out.println(passwort);

        }else if(zahlen){
            while(i < requiredLength){
                int randomElement = random.nextInt(numbers.size());
                passwort.append(numbers.get(randomElement));
                i ++;
            }
            System.out.println(passwort);

        }else if (specialCases){
            while(i < requiredLength){
                int randomElement = random.nextInt(specialCharactertest.size());
                passwort.append(specialCharactertest.get(randomElement));
                i ++;
            }
            System.out.println(passwort);


        }

    }
}


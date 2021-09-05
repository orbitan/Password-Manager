package com.company;

import java.util.*;

public class passwordGenerator {
    public int requiredLength;
    public boolean zahlen;
    public boolean letters;
    public boolean specialCases;
    public boolean upperCases;

// TODO Nummerliste ist eigentlich überflüssig. Besser sowas wie random.choice(0, 10); Das Gleiche gilt für ABC- und abc-Listen;
// TODO Exceptions falls requiredLenght < 1;


    public StringBuilder fertigGeneriertesPasswort(int requiredLength, boolean letters, boolean specialCases, boolean zahlen, boolean upperCases) { //generiert ein Passwort auf Basis der definierten Attribute
        List<Character> letterList = new ArrayList<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'));
        List<Character> upperCaseLetters = new ArrayList<Character>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));
        List<Character> specialCharacters = new ArrayList<Character>(Arrays.asList('!', '#', '+', '*', '?', '&', '$', '-', '_'));
        List<Character> numbers = new ArrayList<Character>(Arrays.asList('0', '1', '2', '3', '4', '5', '6', '7', '8', '9'));
        List<Character> passwortListe = new ArrayList<Character>();
        Random random = new Random();

        int i = 0;
        int randomElement;
        while (i < requiredLength) {
            if (letters) {
                randomElement = random.nextInt(letterList.size());
                passwortListe.add(letterList.get(randomElement));
                i++;
            }
            if (zahlen && i < requiredLength) {
                //randomElement = random.nextInt(numbers.size());
                randomElement = random.nextInt(10);
                passwortListe.add(numbers.get(randomElement));
                i++;
            }
            if (specialCases && i < requiredLength) {
                randomElement = random.nextInt(specialCharacters.size());
                passwortListe.add(specialCharacters.get(randomElement));
                i++;
            }
            if (upperCases && i < requiredLength) {
                randomElement = random.nextInt(letterList.size());
                passwortListe.add(upperCaseLetters.get(randomElement));
                i++;
            }
        }
        StringBuilder password = new StringBuilder();
        while (password.length() < requiredLength) {
            randomElement = random.nextInt(passwortListe.size());
            password.append(passwortListe.get(randomElement));
            passwortListe.remove(randomElement);
        }
        return password;
    }
}

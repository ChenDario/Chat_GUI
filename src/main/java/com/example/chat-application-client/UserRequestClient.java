package com.example;

import java.io.DataOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

public class UserRequestClient {

    public static void user_input_request(DataOutputStream out, Scanner scan, HashMap<String, String> users_key,EncryptionRSA safe_message, HashMap<String, String> group_codes) throws IOException, InterruptedException, Exception {
        Thread.sleep(500);
        String message = "";
        boolean sendMessage = true; 
        // Loop per inviare messaggi
        do {
            stampaMenu();

            do {
                String scelta = "";
                //Inserimento dell'operazione
                System.out.println(ConsoleColors.UNDERLINE + ConsoleColors.BOLD_TEXT +"Scrivi il numero corrispondente all' azione che vorresti fare: " + ConsoleColors.RESET_TEXT);
                scelta = scan.nextLine();

                message = handleRequest(scelta, scan, out, users_key, safe_message, group_codes);
            } while (message == null);// in caso inserisca una stringa non valida ripetere il ciclo

            // Condizione di uscita
            if (message.equalsIgnoreCase("exit")) {
                System.out.println(ConsoleColors.YELLOW_TEXT + "Disconnessione..." + ConsoleColors.RESET_TEXT);
                sendMessage = false;
            } else {
                // Invia il messaggio al server
                out.writeBytes(message + "\n");
            }

            // Questo sleep permette di far eseguire la richiesta e farla stampare prima che
            // ricominci il loop (cosa puramente visiva)
            Thread.sleep(1000);
        } while (sendMessage);
    }

    private static String handleRequest(String scelta, Scanner scanner, DataOutputStream out, HashMap<String, String> users_key, EncryptionRSA safe_message, HashMap<String, String> group_codes) throws IOException, Exception {
        switch (scelta) {
            case "0":
                return "exit";
            case "1":
                return privateMessage(scanner, users_key, out, safe_message);
            case "2":
                return groupMessage(scanner, group_codes);
            case "3":
                return broadcastMessage(scanner);
            case "4":
                return "@_list";
            case "5":
                return "G@_list";
            case "6"://Da request not found
                return getUsersInGroup(scanner);
            case "7":
                return "/list_all";
            case "8":
                return create_group(scanner);
            case "9":
                return addUsersGroup(scanner);
            case "10":
                return leftGroup(scanner);
            default:
                System.out.println(ConsoleColors.RED_TEXT + "Request Not Found" + ConsoleColors.RESET_TEXT);
                return null;
        }
    }

    private static String leftGroup(Scanner scanner){
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC +"Inserisci il nome del gruppo da qui vuoi uscire: " + ConsoleColors.RESET_TEXT);
        String nomeEliminabile = scanner.nextLine();
        return "/left_G@ " + nomeEliminabile;
    }

    private static String addUsersGroup(Scanner scanner){
        String message = "/join_G@ ";
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Inserisci il nome del gruppo a cui desideri aggiungere un altro utente: " + ConsoleColors.RESET_TEXT);
        String nomeGruppoEsistente = scanner.nextLine();
        message += nomeGruppoEsistente + " - ";
        String rispostaSi;

        do {
            System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Inserisci il nome utente da inserire: " + ConsoleColors.RESET_TEXT);
            String nomeUtente = scanner.nextLine();
            message += nomeUtente + ", "; //aggancia ogni volta utente nuovo
            System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Vuoi inserire un altro utente? digitare 'si' se la risposta è affermativa: " + ConsoleColors.RESET_TEXT);
            rispostaSi = scanner.nextLine();
        } while (rispostaSi.equalsIgnoreCase("si"));

        if (message.length() > 0 && message.charAt(message.length() - 2) == ',') { 
            //togliere l'ultimo carrattere perchè è una ,
            message = message.substring(0, message.length() - 2);
        } //dovrebbe essere a posto

        return message;
    }

    private static String create_group(Scanner scanner){
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Digitare il nome del gruppo da creare: " + ConsoleColors.RESET_TEXT);
        String nomeGruppoDaCreare = scanner.nextLine();
        return "/create_group " + nomeGruppoDaCreare;
    }

    private static String getUsersInGroup(Scanner scanner){
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Digita il nome del gruppo per vedere i suoi partecipanti: " + ConsoleColors.RESET_TEXT);
        String gruppoEsistente = scanner.nextLine();
        return "/users_group " + gruppoEsistente;
    }    

    private static String broadcastMessage(Scanner scanner) throws Exception{
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Scrivi il messaggio da inviare a tutti: " + ConsoleColors.RESET_TEXT);
        String messaggioTutti = scanner.nextLine();
        System.out.println("Debug BroadCast message: " + EncryptionAES.encrypt(messaggioTutti, Main.broadcast_code));
        return "@All " + EncryptionAES.encrypt(messaggioTutti, Main.broadcast_code);
    }

    private static String privateMessage(Scanner scanner, HashMap<String, String> users_key, DataOutputStream out, EncryptionRSA safe_message) throws IOException {
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Scrivi il nome utente a cui inviare il messaggio:" + ConsoleColors.RESET_TEXT);
        String nomeUtenteEsistente = scanner.nextLine();
        findPublicKey(nomeUtenteEsistente, users_key, out);

        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC + "Scrivi il messaggio:" + ConsoleColors.RESET_TEXT);
        String messaggio = scanner.nextLine();

        return "@" + nomeUtenteEsistente + " " + encrypt_message(messaggio, nomeUtenteEsistente, users_key, safe_message);    
    }

    private static void findPublicKey(String user, HashMap<String, String> users_key, DataOutputStream out) throws IOException{
        if(users_key.get(user) == null)
        out.writeBytes("/request_key " + user + "\n");
        //Se non ha la chiave la richiede al server
    }

    private static String encrypt_message(String message, String user, HashMap<String, String> users_key, EncryptionRSA safe_message){
        String public_key = users_key.get(user);
        if (public_key == null) {
            System.out.println(ConsoleColors.RED_TEXT + "Chiave pubblica non trovata per l'utente " + user + ConsoleColors.RESET_TEXT);
            return null;
        }
        String[] key_dest = public_key.split(" : ");
        try {
            return safe_message.encrypt(message, new BigInteger(key_dest[0]), new BigInteger(key_dest[1]));
        } catch (Exception e) {
            System.out.println(ConsoleColors.RED_TEXT + "Errore durante la crittografia del messaggio!" + ConsoleColors.RESET_TEXT);
            return null;
        }
    }

    private static String groupMessage(Scanner scanner, HashMap<String, String> groupcodes) throws Exception {
        System.out.println(ConsoleColors.BOLD_TEXT + ConsoleColors.ITALIC +"Scrivi il nome del gruppo a cui inviare il messaggio:" + ConsoleColors.RESET_TEXT );
        String nomeGruppo = scanner.nextLine();
        System.out.println(ConsoleColors.ITALIC +"Scrivi il messaggio:" + ConsoleColors.RESET_TEXT);
        String messaggio = scanner.nextLine();
        try {
            //Cifra il messaggio //C'è da fare il controllo del nome valido del gruppo
            messaggio = EncryptionAES.encrypt(messaggio, groupcodes.get(nomeGruppo));    
        } catch (Exception e) {
            System.out.println("GROUP ERROR");
            messaggio = null;
        }
        System.out.println("Debug Group message: " + messaggio);
        return "G@" + nomeGruppo + " " + messaggio;
    }

    // funzione per stampare il menu
    private static void stampaMenu() { 
        // tutti print out con le opzione
        System.out.println(ConsoleColors.ITALIC + ConsoleColors.BOLD_TEXT + "-----------------------PANNELLO COMANDI-----------------------" + ConsoleColors.RESET_TEXT);
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "0)" + ConsoleColors.RESET_TEXT + " Uscire dall' aplicazione"); // exit
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "1)" + ConsoleColors.RESET_TEXT + " Inviare un messaggio ad un altro utente"); // @username “message” 
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "2)" + ConsoleColors.RESET_TEXT + " Inviare un messaggio ad un gruppo"); // G@group_name “message”
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "3)" + ConsoleColors.RESET_TEXT + " Inviare un messaggio a tutti gli utenti registrati"); // @All “message”
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "4)" + ConsoleColors.RESET_TEXT + " Lista degli utenti attivi"); // @_list
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "5)" + ConsoleColors.RESET_TEXT + " lista dei gruppi attivi"); // G@_list
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "6)" + ConsoleColors.RESET_TEXT + " Lista dei membri di un gruppo"); // /users_group nome_gruppo
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "7)" + ConsoleColors.RESET_TEXT + " Lista di tutte le chat attive (sia gruppi e sia private)"); // /list_all
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "8)" + ConsoleColors.RESET_TEXT + " Creazione di una chat di gruppo tra 3 o più utenti"); // /create_group “group_name”
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "9)" + ConsoleColors.RESET_TEXT + " Per aggiungere un utente ad un gruppo"); // /join_G@ group_name - username1, username2…..
        System.out.println( ConsoleColors.BRIGHT_CYAN + ConsoleColors.BOLD_TEXT + "10)" + ConsoleColors.RESET_TEXT + " Per uscire dal gruppo"); // /left_G@ group_name
    }
}

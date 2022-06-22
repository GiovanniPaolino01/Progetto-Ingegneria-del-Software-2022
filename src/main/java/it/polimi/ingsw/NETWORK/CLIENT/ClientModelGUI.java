package it.polimi.ingsw.NETWORK.CLIENT;

import it.polimi.ingsw.CONTROLLER.Action;
import it.polimi.ingsw.MODEL.Colour;
import it.polimi.ingsw.MODEL.Player;
import it.polimi.ingsw.NETWORK.CLIENT.LoginController;
import it.polimi.ingsw.NETWORK.CLIENT.UserInterface;
import it.polimi.ingsw.NETWORK.MESSAGES.*;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Scanner;

public final class ClientModelGUI extends UserInterface {

    private static boolean buttonIsClicked = false;
    private static ClientAction action;
    private static String actionPlayed;
    protected static boolean boardCreated = false;
    protected static Stage stage = new Stage();
    private static LoginController controller;

    static {
        try {
            controller = new LoginController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    protected static String nickname = "";

    public ClientModelGUI(){

        super();
    }


    //todo : deve mostrare che cosa non va bene
    public static void clientError(ServerMessage message){}

    //todo : deve mostrare il vincitore
    public static void endGame(ServerMessage message){}

    //METODI CHE RICHIEDONO DATI
    public static ClientMessage sendNickname(){

        ClientHeader ch;
        Payload pay;
        ClientMessage cm;


        while(!controller.getButtonIsClicked()){try{Thread.sleep(100);}catch(Exception e){}}
        //System.out.println("e' stato premuto il pulsante");
        String nick = controller.sendNickname();
        nickname = nick;

        ch = new ClientHeader(nickname, ClientAction.SEND_NICKNAME);
        pay = new Payload("nickname", nick);
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendTypeGame(){

        ClientHeader ch;
        Payload pay;
        ClientMessage cm;

        while(!controller.getButtonIsClicked()){try{Thread.sleep(100);}catch(Exception e){}
        }

        String typeGame = controller.sendTypeGame();


        ch = new ClientHeader(nickname, ClientAction.SEND_GAMEMODE);
        pay = new Payload("typeGame", typeGame);
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendNumPlayers(){

        ClientHeader ch;
        Payload pay;
        ClientMessage cm;

        while(!controller.getButtonIsClicked()){try{Thread.sleep(100);}catch(Exception e){}}
        //System.out.println("e' stato premuto il pulsante");
        String numPlayers = controller.sendNumPlayers();

        ch = new ClientHeader(nickname, ClientAction.SEND_NUM_PLAYERS);
        int n = stringToInt(numPlayers);
        pay = new Payload("numPlayer", n);
        cm = new ClientMessage(ch, pay);

        return cm;
    }


    public static ClientAction sendTypeAction(){
        while(!getButtonIsClicked()){try{Thread.sleep(100);}catch(Exception e){}}
        System.out.println("invio l'azione");
        return getAction();
    }

    public static ClientMessage sendPlayCard(){
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;

        ch = new ClientHeader(nickname, ClientAction.PLAY_CARD);
        pay = new Payload();

        //aggiungo i parametri per riconoscere l'azione
        pay.addParameter("nickname", nickname);
        pay.addParameter("Action", Action.PlayCard);
        int n;
        //richiedo il numero della carta
        String actionPlayed = getActionPlayed();

        if(actionPlayed.length() == 5) {
            //System.out.println("la lunghezza e' 5");
            n = Integer.parseInt(actionPlayed.substring(4, 5));
        } else{
            //System.out.println("la lunghezza e' 6");
            n = Integer.parseInt(actionPlayed.substring(4, 6));
        }

        pay.addParameter("num", n);

        //aggiungo parametri nulli
        pay.addParameter("Colour", null);
        pay.addParameter("CharacterParameters", null);

        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendTakeCloud(){
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;


        //INSERISCO I PARAMETRI PER RICONOSCERE L'AZIONE
        pay = new Payload("nickname", nickname);
        pay.addParameter("Action", Action.TakeCloud);

        //CHIEDO I PARAMETRI PER L'AZIONE
        //richiedo il numero della carta
        String actionPlayed = getActionPlayed();
        int n = Integer.parseInt(actionPlayed.substring(5, 6));
        pay.addParameter("num", n);

        //INSERISCO I PARAMTRI NON UTILIZZATI
        pay.addParameter("CharacterParameters", null);
        pay.addParameter("Colour", null);

        //COSTRUISCO E INVIO IL MESSAGGIO
        ch = new ClientHeader(nickname, ClientAction.PLAY_TAKE_CLOUD);
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendMoveMotherNature(){
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;


        //INSERISCO I PARAMETRI PER RICONOSCERE L'AZIONE
        pay = new Payload("nickname", nickname);
        pay.addParameter("Action", Action.MoveMotherNature);

        //CHIEDO I PARAMETRI PER L'AZIONE
        int n;
        if(actionPlayed.length() == 7){
            n = stringToInt(getActionPlayed().substring(6,7));
        } else{
            n = stringToInt(getActionPlayed().substring(6,8));
        }
        int temp = n - motherNature.getNumIsland();

        if(temp < 0){
            n = listIsland.size() - motherNature.getNumIsland() + n;
        }else {
            n = temp;
        }
        pay.addParameter("num", n);

        //INSERISCO I PARAMTRI NON UTILIZZATI
        pay.addParameter("Colour", null);
        pay.addParameter("CharacterParameters", null);

        //COSTRUISCO E INVIO IL MESSAGGIO
        ch = new ClientHeader(nickname, ClientAction.PLAY_MOVE_MOTHERNATURE);
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendMoveStudentInDiningRoom(){
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;

        //INSERISCO I PARAMETRI PER RICONOSCERE L'AZIONE
        pay = new Payload("nickname", nickname);
        pay.addParameter("Action", Action.MoveStudentInDiningRoom);

        //CHIEDO I PARAMETRI PER L'AZIONE
        Colour colour = stringToColour(getActionPlayed());
        pay.addParameter("Colour", colour);

        //INSERISCO I PARAMTRI NON UTILIZZATI
        pay.addParameter("CharacterParameters", null);
        pay.addParameter("num", 0);

        //COSTRUISCO E INVIO IL MESSAGGIO
        ch = new ClientHeader(nickname, ClientAction.PLAY_MOVE_STUDENT_IN_DININGROOM);
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    //TODO : TUTTI I METODI SOTTO QUESTO TODO
    public static ClientMessage sendPlayCharacterCard(){
        String inputLine;
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;


        ch = new ClientHeader("", ClientAction.SEND_NICKNAME);
        pay = new Payload("nickname", "");
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static ClientMessage sendMoveStudentInIsland(){
        String inputLine;
        ClientHeader ch;
        Payload pay;
        ClientMessage cm;


        ch = new ClientHeader("", ClientAction.SEND_NICKNAME);
        pay = new Payload("nickname", "");
        cm = new ClientMessage(ch, pay);

        return cm;
    }

    public static String keepPlaying(){
        return "no";
    }

    //METODI UTILI
    public static void setController(LoginController control){
        //System.out.println("ho cambiato il controller");
        controller = control;

    }
    public static Player getPlayer(String s)throws Exception{
        Player player= null;
        for(Player p : listPlayer){
            if(p.getNicknameClient().equals(s)){
                player = p;
            }
        }
        if(player.equals(null)){
            throw new Exception();
        }else{return player;}
    }

    public static ClientAction getAction(){
        return action;
    }
    protected static void setAction(ClientAction newAction){action = newAction;}

    protected static void setActionPlayed(String s){
        actionPlayed = s;
    }
    public static String getActionPlayed(){
        setButtonIsClicked(false);
        return actionPlayed;
    }

    public static boolean getButtonIsClicked() {
        return buttonIsClicked;
    }
    protected static void setButtonIsClicked(boolean b) {
        buttonIsClicked = b;
    }

    private static int stringToInt(String inputLine){
        int n;
        if(inputLine.equals("0")){
            n = 0;
        }else if(inputLine.equals("1")){
            n = 1;
        }else if(inputLine.equals("2")){
            n = 2;
        }else if(inputLine.equals("3")){
            n = 3;
        }else if(inputLine.equals("4")){
            n = 4;
        }else if(inputLine.equals("5")){
            n = 5;
        }else if(inputLine.equals("6")){
            n = 6;
        }else if(inputLine.equals("7")){
            n = 7;
        }else if(inputLine.equals("8")){
            n = 8;
        }else if(inputLine.equals("9")){
            n = 9;
        }else if(inputLine.equals("10")){
            n = 10;
        }else if(inputLine.equals("11")){
            n = 11;
        }else if(inputLine.equals("12")){
            n = 12;
        }else{
            n = 100;
        }

        return n;
    }
    private static Colour stringToColour(String inputLine) {
        if(inputLine.equals("blue")){
            return Colour.BLUE;
        } else if(inputLine.equals("red")){
            return Colour.RED;
        } else if(inputLine.equals("yellow")){
            return Colour.YELLOW;
        } else if(inputLine.equals("green")){
            return Colour.GREEN;
        } else {
            return Colour.PINK;
        }
    }
    protected static String colourToString(Colour inputLine){

        if(inputLine.equals(Colour.BLUE)){
            return "blue";
        } else if(inputLine.equals(Colour.RED)){
            return "red";
        } else if(inputLine.equals(Colour.YELLOW)){
            return "yellow";
        } else if(inputLine.equals(Colour.GREEN)){
            return "green";
        } else {
            return "pink";
        }
    }

    //CAMBI PAGINA
    public static void changeToLoginPage() {
        controller.changeToLoginPage();
    }

    public static void changeToWaitingPage() {
        controller.changeToWaitingPage();
    }

    public static void changeToBoard(){
        controller.changeToBoard();
    }
}

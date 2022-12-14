package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.CONTROLLER.Exception.WrongActionException;
import it.polimi.ingsw.CONTROLLER.Exception.WrongClientException;
import it.polimi.ingsw.MODEL.CharacterParameters;
import it.polimi.ingsw.MODEL.Colour;
import it.polimi.ingsw.MODEL.Exception.MissingCardException;
import it.polimi.ingsw.MODEL.Exception.MissingCloudException;
import it.polimi.ingsw.MODEL.Exception.MissingPlayerException;
import it.polimi.ingsw.MODEL.Exception.PossibleWinException;
import it.polimi.ingsw.MODEL.Game;
import it.polimi.ingsw.NETWORK.MESSAGES.ClientMessage;
import it.polimi.ingsw.NETWORK.UTILS.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class ControllerTurn implements Observer{
    private Game game;
    private ControllerAction controllerAction;
    private List<String> clientList;
    private String currentClient;
    private boolean mulligan;
    private boolean youCanPlayCharacterCard;

    public ControllerTurn(ControllerAction controllerAction, Game game, List<String> nicknames){
        this.clientList = new ArrayList<>();
        this.clientList.addAll(nicknames);
        this.game = game;
        this.controllerAction = controllerAction;
        this.mulligan = true;
        this.youCanPlayCharacterCard = true;
        currentClient = clientList.get(0);
    }

    //restituisce true se il Sender sta facendo il suo turno (è il currentClient)

    /**
     * it returns true if is the turn of the sender
     * @param sender
     * @return true,false
     */
    public boolean verifyClient(String sender){
        if(sender.equals(this.currentClient)){
            return true;
        }

        else{
            return false;
        }
    }

    /*
    * la funzione richiama il metodo richiesto dal client sse il player che sta chiamando è lo stesso che deve
    * effettuare la mossa altrimenti notifica con un errore
    */

    /**
     * the function calls correct method requested by the client iof the player who are calling is the same that have to play, else throw exception
     * @param action
     * @param nickname
     * @param colourParameter
     * @param numberParameter
     * @param charPar
     * @throws WrongClientException
     */
    public void callAction(Action action, String nickname, Colour colourParameter, int numberParameter, CharacterParameters charPar)throws WrongClientException {
        if(verifyClient(nickname)){

            if(mulligan==true){
                if(action.equals(Action.PlayCard)) {
                    try {
                        this.controllerAction.playCard(nickname, numberParameter);
                        this.endTurn();
                    } catch (MissingCardException e) {
                        game.notifyError(e.getMessage(), nickname);
                    }


                } else{
                    game.notifyError("mossa selezionata non valida, prova a lanciare una carta assistente", nickname);
                }
            }
            else{
                //se ho mulligan a false devo dirgli che non può lanciare un'altra carta
                if(action.equals(Action.PlayCard)){
                    game.notifyError("mossa selezionata non valida", nickname);
                }
                else if(action.equals(Action.MoveMotherNature)){
                    try{
                        this.controllerAction.moveMotherNature(numberParameter);
                    }catch(WrongActionException e){
                        game.notifyError("mossa selezionata non valida", nickname);
                    }catch(Exception e){
                        game.notifyError(e.getMessage(), nickname);
                    }
                }
                else if(action.equals(Action.MoveStudentInDiningRoom)){
                    try {
                        this.controllerAction.moveStudentInDiningRoom(nickname, colourParameter);
                    }catch(WrongActionException e){
                        game.notifyError("mossa selezionata non valida", nickname);
                    }
                }
                else if(action.equals(Action.MoveStudentInIsland)){
                    try {
                        this.controllerAction.moveStudentInIsland(nickname, colourParameter, numberParameter);
                    }catch(WrongActionException e){
                        game.notifyError("mossa selezionata non valida", nickname);
                    }
                }
                else if(action.equals(Action.TakeCloud)){
                    try {
                        this.controllerAction.takeCloud(nickname, numberParameter);
                        this.endTurn();

                    }catch(WrongActionException e) {
                        game.notifyError("mossa selezionata non valida", nickname);
                    }
                }
                else if(action.equals(Action.UseCharacter)){
                    if(this.youCanPlayCharacterCard == true) {
                        try {
                            this.controllerAction.useCharacter(charPar);
                            setyouCanPlayCharacterCard(false);

                        }catch(Exception e){
                            game.notifyError(e.getMessage(), nickname);
                        }
                    } else{
                        game.notifyError("la carta personaggio è già stata giocata questo turno", nickname);
                    }
                }
            }
        }
        else{
            throw new WrongClientException();
        }

    }

    /**
     * set new current client
     * @param newCurrentClient
     */
    public void setCurrentClient(String newCurrentClient){
        this.currentClient = newCurrentClient;
    }

    /**
     * returns currentClient
     * @return currentClient
     */
    public String getCurrentClient(){
        return this.currentClient;
    }

    /*
    * la funzione permette di selezionare il prossimo giocatore che deve effettuare la mossa sulla base
    * dell'ordine imposto dalle carte assistente
    * oppure ordina i player in base alle carte giocate
    */

    /**
     * it select the next player, or order players by played card
     */
    public void endTurn(){
        //nel prossimo turno posso giocare un'altra carta

        this.youCanPlayCharacterCard = true;
        //se il giocatore è l'ultimo allora significa che è finito il giro e dobbiamo rilanciare le carte

        if(currentClient.equals(clientList.get(clientList.size()-1))){

            String temp = "";
            //caso in cui ordino i player in base alle carte
            if(mulligan == true){
                this.setMulligan(false);
                for(int i=0; i<clientList.size()-1; i++){
                    for(int j=i+1; j< clientList.size(); j++){
                        try {
                            if(game.getPlayer(clientList.get(i)).getLastPlayedCard().getValue() > game.getPlayer(clientList.get(j)).getLastPlayedCard().getValue()){
                                temp = clientList.get(i);
                                clientList.set(i,clientList.get(j));
                                clientList.set(j,temp);
                            }
                        } catch (MissingPlayerException | MissingCardException e) {}
                    }
                }
                //setto il prossimo player al primo
                this.setCurrentClient(this.clientList.get(0));
            }

            //caso in cui devo ancora far lanciare la carta a qualcuno
            else{
                game.refillCloud();
                this.setMulligan(true);
                currentClient = this.clientList.get(0);
                //tutti i client hanno la carta giocata 0,0 così si può scegliere qualsiasi tipo di carta senza avere problemi
                for(String client : clientList){
                    try {
                        game.getPlayer(client).setLastPlayedCardZero();
                    } catch (MissingPlayerException e) {}
                }
            }
        }

        //passo al turno del prossimo player
        else{
            for(int i = 0 ; i < clientList.size() - 1; i++){
                if(this.clientList.get(i).equals(currentClient)){
                    currentClient = this.clientList.get(i+1);
                    break;
                }
            }
        }
        setyouCanPlayCharacterCard(true);
        game.setCurrentPlayer(currentClient);
        game.sendBoard("EndTurn");
    }

    /**
     * set if you can play characterCard
     * @param b
     */
    private void setyouCanPlayCharacterCard(boolean b) {
        this.youCanPlayCharacterCard = b;
    }

    @Override
    public void update(Object message) {
        ClientMessage cm = (ClientMessage) message;

        try {
            callAction((Action) cm.getPayload().getParameter("Action"), (String) cm.getPayload().getParameter("nickname"), (Colour) cm.getPayload().getParameter("Colour"), (int)cm.getPayload().getParameter("num"), (CharacterParameters) cm.getPayload().getParameter("CharacterParameters"));
        } catch (WrongClientException e) {
            game.notifyError("non e' il tuo turno", (String)cm.getPayload().getParameter("nickname"));
        } catch (Exception e){
            System.out.println("Eccezione: " + e.getClass());
        }

    }

    /**
     * if mulligan is true you can throw a card
     * @param mulligan
     */
    public void setMulligan(boolean mulligan) {
        this.mulligan = mulligan;
    }

    /**
     * @return mulligan
     */
    public boolean getMulligan() {
        return this.mulligan;
    }

    public boolean getYouCanPlayCharacterCard(){
        return this.youCanPlayCharacterCard;
    }


}


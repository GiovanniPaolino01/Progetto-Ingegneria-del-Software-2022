package it.polimi.ingsw.CONTROLLER;

import it.polimi.ingsw.MODEL.Colour;
import it.polimi.ingsw.MODEL.Game;
import it.polimi.ingsw.VIEW.Client;

import java.util.List;

public class ControllerTurn {
    private Game game;
    private ControllerAction controllerAction;
    private List<Client> clientList;
    private String currentClient;


    //restituisce true se il Sender sta facendo il suo turno (è il currentClient)
    public boolean verifyClient(String Sender){
        if(Sender == this.currentClient){
            return true;
        }
        else{
            return false;
        }
    }

    //richiama verifyClient e dopodiche richiama la funzione corretta in ControllerAction
    public void callAction(Action action, String nickname, Colour colourParameter, int numberParameter)throws WrongClientException{
        if(verifyClient(nickname)){

            if(action == Action.MoveMotherNature){
                try{
                this.controllerAction.moveMotherNature(numberParameter);
                }catch(WrongActionException e){}
            }
            else if(action == Action.MoveStudentInDiningRoom){
                try {
                    this.controllerAction.moveStudentInDiningRoom(nickname, colourParameter);
                }catch(WrongActionException e){}
            }
            else if(action == Action.MoveStudentInIsland){
                try {
                    this.controllerAction.moveStudentInIsland(nickname, colourParameter, numberParameter);
                }catch(WrongActionException e){}
            }
            else if(action == Action.PlayCard){
                    this.controllerAction.playCard(nickname, numberParameter);
            }
            else if(action == Action.TakeCloud){
                try {
                    this.controllerAction.takeCloud(nickname, numberParameter);
                    this.endTurn();

                }catch(WrongActionException e){}
            }
            else if(action == Action.UseCharacter){
                this.controllerAction.useCharacter(numberParameter);
            }
        }
        else{
            throw new WrongClientException();
        }
    }

    public void setCurrentClient(String newCurrentClient){
        this.currentClient = newCurrentClient;
    }

    public String getCurrentClient(){
        return this.currentClient;
    }


    public void endTurn(){
        if(currentClient == clientList.get(clientList.size()-1).getNickname()){
            //TODO UN METODO PER LANCIARE LE CARTE IN MODO DA RIFARE L'ORDINE
        }
        else{
            for(int i = 0 ; i < clientList.size(); i++){
                if(this.clientList.get(i).getNickname() == currentClient){
                    currentClient = this.clientList.get(i+1).getNickname();
                }
            }
        }
    }


}
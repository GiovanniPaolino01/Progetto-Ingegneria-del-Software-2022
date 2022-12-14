package it.polimi.ingsw.MODEL.CharacterCards;

import it.polimi.ingsw.MODEL.CharacterCards.ConcreteCharacterCard;
import it.polimi.ingsw.MODEL.CharacterCards.Decorator;
import it.polimi.ingsw.MODEL.Colour;
import it.polimi.ingsw.MODEL.Game;

import java.io.Serializable;

public class Knight extends ConcreteCharacterCard implements Decorator, Serializable {

    private transient Game game;

    public Knight(Game game){
        nameCard = "Knight";
        this.game = game;
        initialPrice = 2;
        price = 2;
        initialization();
    }

    @Override
    public void initialization() {

    }
    @Override
    public void effect(String nickname){
        if(price==initialPrice){
            price = price+1;
        }
        game.setCardThrown(this.nameCard);
    }

    @Override
    public void effect(String nickname, Colour colour) throws Exception{
        throw new Exception("Error");
    }

    @Override
    public void effect(String nickname, Colour colour, Colour colour2) throws Exception {
        throw new Exception("Error");
    }

    @Override
    public void effect(String nickname, Colour colour, Colour colour2, Colour colour3, Colour colour4) throws Exception{
        throw new Exception("Error");
    }

    @Override
    public void effect(String nickname, Colour colour, Colour colour2, Colour colour3, Colour colour4, Colour colour5, Colour colour6) throws Exception{
        throw new Exception("Error");
    }

    @Override
    public void effect(String nickname, int num) throws Exception{
        throw new Exception("Error");
    }

    @Override
    public void effect(String nickname, int num, Colour colour)  throws Exception{
        throw new Exception("Error");
    }
}

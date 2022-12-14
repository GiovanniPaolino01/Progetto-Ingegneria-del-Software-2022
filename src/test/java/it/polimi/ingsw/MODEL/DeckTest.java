package it.polimi.ingsw.MODEL;

import it.polimi.ingsw.MODEL.Exception.MissingCardException;
import it.polimi.ingsw.MODEL.Exception.PossibleWinException;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

public class DeckTest extends TestCase {

    @Test
    public void testRemoveCard() throws MissingCardException, PossibleWinException {
        Deck d = new Deck();
        Card c = new Card(1, 1);
        int n = 1;

        d.removeCard(20);
        assertEquals(10, d.size());

        d.removeCard(1);
        assertEquals(9, d.size());
        for(int i = 1; i <= d.size(); i++){
            if(i != 1){
                assertFalse(d.getCard(i).equals(c));
            }
        }
    }

    @Test
    public void testGetCard() throws MissingCardException, PossibleWinException{
        Deck d = new Deck();
        Card c = new Card(1, 1);

        assertEquals(d.getCard(1), new Card(1,1 ));
        assertEquals(d.getCard(10), new Card(10,5 ));
    }

    @Test
    public void testSize() {
        Deck d = new Deck();

        assertEquals(10, d.size());

        d.removeCard(1);

        assertEquals(9, d.size());
    }
}
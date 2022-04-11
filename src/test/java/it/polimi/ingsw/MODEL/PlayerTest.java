package it.polimi.ingsw.MODEL;

import junit.framework.TestCase;

public class PlayerTest extends TestCase {

    public void testGetTeam(){
        Game g = new Game("io", "tu");
        Team t = g.getPlayer("io").getTeam();
        assertTrue(t instanceof Team);
    }

    public void testGetLastPlayedCard() {
        Game g = new Game("io", "tu");
        g.getPlayer("io").playCard(3);
        Card c = g.getPlayer("io").getLastPlayedCard();

        assertEquals(3, c.getValue());
    }

    public void testGetDeck() {
        Game g = new Game("io", "tu");
        Deck d = g.getPlayer("io").getDeck();
        assertTrue(d instanceof Deck);
    }

    public void testGetNicknameClient() {
        Game g = new Game("io", "tu");
        assertEquals("io", g.getPlayer("io").getNicknameClient());
    }

    public void testGetEntrance() {
        Game g = new Game("io", "tu");
        Entrance e = g.getPlayer("io").getEntrance();
        assertTrue(e instanceof Entrance);
    }

    public void testNumProfessor() {
        Team t = new Team(ColourTower.BLACK, 8);
        Player p = new Player("io",t);
        int num = 3;

        for(int i = 0; i < num; i++){
            p.addProfessor(new Professor(Colour.BLUE));
        }

        assertEquals(num,p.numProfessor());
    }

    public void testPlayCard() {
        Game g = new Game("io", "tu");
        int num = 3;
        int numCardInDeck_Prima, numCardInDeck_Dopo;

        numCardInDeck_Prima = g.getPlayer("io").getDeck().size();
        g.getPlayer("io").playCard(num);
        numCardInDeck_Dopo = g.getPlayer("io").getDeck().size();

        assertEquals(numCardInDeck_Dopo, numCardInDeck_Prima-1);
    }

    public void testMoveStudentInDiningRoom() {

        Team t = new Team(ColourTower.BLACK,8);
        Player pl = new Player("io", t);
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.addStudent(Colour.GREEN); //aggiungiamo uno studente di quel colore per testare
        pl.addStudentsToEntrances(studentGroup);

        int numPrimaStudentiInDiningRoom = pl.NumStudentsDiningRoom(Colour.GREEN);
        pl.moveStudentInDiningRoom(Colour.GREEN);

        int numDopoStudentiInDiningRoom = pl.NumStudentsDiningRoom(Colour.GREEN);
        assertEquals(numDopoStudentiInDiningRoom, numPrimaStudentiInDiningRoom+1);
    }

    public void testMoveStudentInIsland() {
        Team t = new Team(ColourTower.BLACK, 8);
        Player p = new Player("io",t);
        Island i = new Island(1);
        StudentGroup s = new StudentGroup();
        Colour c = Colour.BLUE;

        s.addStudent(c);

        p.addStudentsToEntrances(s);
        p.moveStudentInIsland(c,i);

        assertEquals(0,p.NumStudentsDiningRoom(c));
        assertEquals(1, i.countStudentsOfColour(c));
    }

    public void testAddStudentsToEntrances() {
        Team t = new Team(ColourTower.BLACK,8);
        Player pl = new Player("io", t);
        StudentGroup studentGroup = new StudentGroup();
        studentGroup.addStudent(Colour.GREEN);
        studentGroup.addStudent(Colour.BLUE);
        studentGroup.addStudent(Colour.YELLOW);

        Entrance oldEntrance = pl.getEntrance();
        pl.addStudentsToEntrances(studentGroup);
        Entrance newEntrance = pl.getEntrance();

        oldEntrance.addGroup(studentGroup);
        assertEquals(newEntrance, oldEntrance);
    }

    public void testRemoveProfessor() {
        Team t = new Team(ColourTower.BLACK, 8);
        Player p = new Player("io",t);
        Colour c = Colour.GREEN;
        Professor prof = new Professor(c);

        p.addProfessor(prof);
        p.removeProfessor(prof);

        assertEquals(0, p.numProfessor());
    }

    public void testAddProfessor() {
        Team t = new Team(ColourTower.BLACK, 8);
        Player p = new Player("io",t);
        Colour c = Colour.GREEN;
        Professor prof = new Professor(c);

        p.addProfessor(prof);
        assertEquals(1, p.numProfessor());
    }

    public void testNumStudentsDiningRoom() {
        Team t = new Team(ColourTower.BLACK, 8);
        Player p = new Player("io",t);
        StudentGroup s = new StudentGroup();
        Colour c = Colour.GREEN;

        s.addStudent(c);

        p.addStudentsToEntrances(s);
        p.moveStudentInDiningRoom(c);

        assertEquals(1,p.NumStudentsDiningRoom(c));
    }
}
package it.polimi.ingsw.MODEL;

import it.polimi.ingsw.MODEL.CharacterCards.*;
import it.polimi.ingsw.MODEL.Exception.*;

import java.util.*;

// TODO scrivere il coso per lanciare di nuovo le carte usando lastFirstPlayer
// TODO regole semplifi e avanzate scelta (guadagno monete e doPlayCharacterCard)

public class Game {
    private int numPlayer;
    private String characterCardThrown;
    private Player lastFirstPlayer;
    private List<Cloud> listCloud;
    private List<Player> listPlayer;
    private List<Team> listTeam;
    private List<Island> listIsland;
    private Bag bag;
    private MotherNature motherNature;
    private List<Professor> professors;
    private List<ConcreteCharacterCard> characterCards;
    private String currentPlayer;

    public Game(String nickname1, String nickname2) {

        listPlayer = new ArrayList<>();
        listTeam = new ArrayList<>();
        listTeam.add(new Team(ColourTower.WHITE, 8));
        listTeam.add(new Team(ColourTower.BLACK, 8));

        listPlayer.add(new Player(nickname1, listTeam.get(0)));
        listPlayer.add(new Player(nickname2, listTeam.get(1)));

        listCloud = new ArrayList<>();
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());

        bag = new Bag();

        listIsland = new ArrayList<>();
        listIsland.add(new Island(0));
        listIsland.add(new Island(1));
        listIsland.add(new Island(2));
        listIsland.add(new Island(3));
        listIsland.add(new Island(4));
        listIsland.add(new Island(5));
        listIsland.add(new Island(6));
        listIsland.add(new Island(7));
        listIsland.add(new Island(8));
        listIsland.add(new Island(9));
        listIsland.add(new Island(10));
        listIsland.add(new Island(11));

        motherNature = new MotherNature(listIsland.get(0));
        listIsland.get(0).setMotherNature(true);

        professors = new ArrayList<>();
        professors.add(new Professor(Colour.BLUE));
        professors.add(new Professor(Colour.GREEN));
        professors.add(new Professor(Colour.PINK));
        professors.add(new Professor(Colour.RED));
        professors.add(new Professor(Colour.YELLOW));

        characterCards = new ArrayList<>();
        characterCardThrown = "";
        currentPlayer = listPlayer.get(0).getNicknameClient();
    }

    public Game(String nickname1, String nickname2, String nickname3) {

        listPlayer = new ArrayList<>();
        listTeam = new ArrayList<>();
        listTeam.add(new Team(ColourTower.WHITE, 6));
        listTeam.add(new Team(ColourTower.BLACK, 6));
        listTeam.add(new Team(ColourTower.GREY, 6));

        listPlayer.add(new Player(nickname1, listTeam.get(0)));
        listPlayer.add(new Player(nickname2, listTeam.get(1)));
        listPlayer.add(new Player(nickname3, listTeam.get(2)));

        listCloud = new ArrayList<>();
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());

        bag = new Bag();

        listIsland = new ArrayList<>();
        listIsland.add(new Island(0));
        listIsland.add(new Island(1));
        listIsland.add(new Island(2));
        listIsland.add(new Island(3));
        listIsland.add(new Island(4));
        listIsland.add(new Island(5));
        listIsland.add(new Island(6));
        listIsland.add(new Island(7));
        listIsland.add(new Island(8));
        listIsland.add(new Island(9));
        listIsland.add(new Island(10));
        listIsland.add(new Island(11));

        motherNature = new MotherNature(listIsland.get(0));
        listIsland.get(0).setMotherNature(true);

        professors = new ArrayList<>();
        professors.add(new Professor(Colour.BLUE));
        professors.add(new Professor(Colour.GREEN));
        professors.add(new Professor(Colour.PINK));
        professors.add(new Professor(Colour.RED));
        professors.add(new Professor(Colour.YELLOW));

        characterCards = new ArrayList<>();

        characterCardThrown = "";
        currentPlayer = listPlayer.get(0).getNicknameClient();
    }

    public Game(String nickname1, String nickname2, String nickname3, String nickname4) {

        listPlayer = new ArrayList<>();
        listTeam = new ArrayList<>();
        listTeam.add(new Team(ColourTower.WHITE, 8));
        listTeam.add(new Team(ColourTower.BLACK, 8));

        listPlayer.add(new Player(nickname1, listTeam.get(0)));
        listPlayer.add(new Player(nickname2, listTeam.get(1)));
        listPlayer.add(new Player(nickname3, listTeam.get(0)));
        listPlayer.add(new Player(nickname4, listTeam.get(1)));

        listCloud = new ArrayList<>();
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());
        listCloud.add(new Cloud());


        bag = new Bag();

        listIsland = new ArrayList<>();
        listIsland.add(new Island(0));
        listIsland.add(new Island(1));
        listIsland.add(new Island(2));
        listIsland.add(new Island(3));
        listIsland.add(new Island(4));
        listIsland.add(new Island(5));
        listIsland.add(new Island(6));
        listIsland.add(new Island(7));
        listIsland.add(new Island(8));
        listIsland.add(new Island(9));
        listIsland.add(new Island(10));
        listIsland.add(new Island(11));

        motherNature = new MotherNature(listIsland.get(0));
        listIsland.get(0).setMotherNature(true);

        professors = new ArrayList<>();
        professors.add(new Professor(Colour.BLUE));
        professors.add(new Professor(Colour.GREEN));
        professors.add(new Professor(Colour.PINK));
        professors.add(new Professor(Colour.RED));
        professors.add(new Professor(Colour.YELLOW));

        characterCards = new ArrayList<>();

        characterCardThrown = "";
        currentPlayer = listPlayer.get(0).getNicknameClient();
    }

    public void startGame() throws MissingStudentException {

        //estrarre casualmente 3 carte
        Random random = new Random();
        for(int i=0; i<3; i++) {
            boolean isPresent = false;
            int num = random.nextInt(8);

            if(num==0){
                for(ConcreteCharacterCard c: characterCards){
                    if(c.getNameCard().equals("Jester")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Jester(this));
                }
                else {
                    i--;
                }
            }

            if(num==1){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Knight")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Knight(this));
                }
                else {
                    i--;
                }
            }

            if(num==2){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Minestrell")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Minstrell(this));
                }
                else {
                    i--;
                }
            }

            if(num==3){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Pirate")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Pirate(this));
                }
                else {
                    i--;
                }
            }

            if(num==4){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("PostMan")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new PostMan(this));
                }
                else {
                    i--;
                }
            }

            if(num==5){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Priest")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Priest(this));
                }
                else {
                    i--;
                }
            }

            if(num==6){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Satyr")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Satyr(this));
                }
                else {
                    i--;
                }
            }

            if(num==7){
                for(CharacterCard c: characterCards){
                    if(c.getNameCard().equals("Woman")){
                        isPresent = true;
                    }
                }
                if(!isPresent){
                    characterCards.add(new Woman(this));
                }
                else {
                    i--;
                }
            }
        }

        // TODO: 13/04/2022 da cambiare
        characterCards.add(0, new Jester(this));
        characterCards.add(1, new Knight(this));
        characterCards.add(2, new Minstrell(this));
        characterCards.add(3, new Pirate(this));
        characterCards.add(4, new PostMan(this));
        characterCards.add(5, new Priest(this));
        characterCards.add(6, new Satyr(this));
        characterCards.add(7, new Woman(this));
        //characterCards.add(1, new ConcreteCharacterCard());
        //characterCards.add(2, new ConcreteCharacterCard());

        for (Cloud cloud : listCloud) { //per ogni nuvola aggiungo 3 studenti estratti casualmente dalla bag
            if (cloud != null) {
                StudentGroup studentGroup = new StudentGroup();
                for(int i=0; i<3; i++) {
                    studentGroup.addStudent(bag.pullOut());}
                cloud.addStudents(studentGroup);
            }
        }

        /*devo tirar fuori dalla bag 2 studenti per colore e
        posizionarli a caso sulle isole tranne sull'isola di madre natura e la sua opposta */
        StudentGroup startingPullOut = bag.startingPullOut();
        for (Island island : listIsland){
            if (island != null && island.getNumIsland()!=motherNature.getNumIsland() && island.getNumIsland()!=((motherNature.getNumIsland()+6)%listIsland.size())){
                //controlla che l'isola non sia quella di madre natura e nemmeno l'opposta
                //island.addStudent(startingPullOut.pullOut());
                // TODO da fare
            }
        }

        for (Player player : listPlayer) { //per ogni player aggiungo nella entrance i 7 studenti estratti casualmente dalla bag
            if (player != null) {
                StudentGroup studentGroup = new StudentGroup();
                for(int i=0; i<7; i++){
                    studentGroup.addStudent(bag.pullOut());}
                player.addStudentsToEntrance(studentGroup);
            }
        }
    }

    /*if the game is finished and there's a winner return True, else return False*/
    public boolean checkWin() {
        if(listIsland.size()<=3 || bag.size()==0){ //if there's 3 or less island or the bag is empty, the game ends
            return true;
        }
        else{
            for(Player p : listPlayer){
                if(p.getTeam().getNumberOfTower()==0 || p.getDeck().size()==0){ //if there's a  player with 0 towers or without card, the game ends
                    return true;
                }
            }
            return false;
        }
    }


    public Team theWinnerIs() {
            int MinTower = listTeam.get(0).getNumberOfTower();
            int numProfteam1 = 0;
            int numProfteam2 = 0;
            Team WinTeam = listTeam.get(0);

            //conto chi ha meno torri
            for(Team t : listTeam){
                if(t.getNumberOfTower()<MinTower){
                    MinTower = t.getNumberOfTower();
                    WinTeam = t;
                }
            }

            //verifico che se ci sono pareggi il vincitore è quello con più professori
            for(Team t : listTeam){
                if(t.getNumberOfTower()==MinTower && !t.equals(WinTeam)){
                    numProfteam1 = 0;
                    numProfteam2 = 0;

                    for(Player player : listPlayer){
                        if(player.getTeam() == WinTeam) numProfteam1 = numProfteam1 + player.numProfessor();
                        else if(player.getTeam() == t) numProfteam2 = numProfteam2 + player.numProfessor();
                    }
                    if(numProfteam2 > numProfteam1){
                    WinTeam = t;
                    }
                }
            }
            return WinTeam;
    }

    public void doMoveMotherNature(int numMovement) { //metti eccezione
        /*
        1. viene sommato il numero dell'isola su cui si trova madre natura con il numero di spostamenti che deve fare
        2. si fa modulo num isole che ci sono, si trova il numero dell'isola su cui madre natura si deve spostare
        3. questo numero è dato alla funzione getIsland, che ritorna l'oggetto isola avente quel numero
         */
        if(characterCardThrown.equals("PostMan")){
            try {
                characterCardThrown = "";
                if(numMovement <= this.getPlayer(currentPlayer).getLastPlayedCard().getMovement()+2){
                    motherNature.move(getIsland((numMovement+motherNature.getNumIsland()) % listIsland.size()));
                }
            } catch (MissingPlayerException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                if(numMovement <= this.getPlayer(currentPlayer).getLastPlayedCard().getMovement()){
                    motherNature.move(getIsland((numMovement+motherNature.getNumIsland()) % listIsland.size()));
                }
            } catch (MissingPlayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void doMoveStudentInDiningRoom(String nickname, Colour colour) {
        try {
            this.getPlayer(nickname).moveStudentInDiningRoom(colour);
        }catch(MissingPlayerException e){}
    }

    //search the id of the player in the player list
    //once is done it calls the method that add the students from the group to the entrance
    //passing as parameter the studentGroup in the cloud that we want to take
    public void doTakeCloud(String nickname, int numCloud)throws MissingCloudException {
        if(numCloud < listCloud.size() && numCloud>=0) {
                for (Player player : listPlayer) {
                    if (player != null) {
                        if (player.getNicknameClient().equals(nickname)) {
                            player.addStudentsToEntrance(listCloud.get(numCloud).getStudents());
                        }
                    }
                }
        }
        else throw new MissingCloudException("Error");
    }

    public void doMoveStudentInIsland(String nickname, Colour colour, int numIsland) {
        for (Player player : listPlayer) {
            if (player.getNicknameClient().equals(nickname)) {
                player.moveStudentInIsland(colour, this.getIsland(numIsland));
            }
        }
    }

    public Island getIsland(int numIsland) throws IllegalArgumentException {
        Island island = null;
        if (numIsland < 0 || numIsland > listIsland.size()) {
            throw new IllegalArgumentException();
        } else {
            for (Island value : listIsland) {
                if (value.getNumIsland() == numIsland) {
                    island = value;
                }
            }
        }
        return island;
    }

    public void doPlayCard(String nickname, int numCard){
        for(Player player : listPlayer){
            if(player != null){
                if(player.getNicknameClient().equals(nickname)){
                    player.playCard(numCard);
                }
            }
        }
    }

    public void checkProfessor(Colour colour) {

        Player MaxPlayer = listPlayer.get(0);

        //il primo ciclo mi dice chi è il player con più studenti del colore colour
        for (int i = 0; i < listPlayer.size(); i++) {
            if (listPlayer.get(i).numStudentsDiningRoom(colour) > 0 &&
                    listPlayer.get(i).numStudentsDiningRoom(colour) >= MaxPlayer.numStudentsDiningRoom(colour)) {
                MaxPlayer = listPlayer.get(i);
            }
        }

        //cerco il professore dello stesso colore
        for (int j = 0; j < professors.size(); j++) {

            if (professors.get(j).getOwner() == null && professors.get(j).getColour() == colour) {

                //se il professore non ha owner e il player con più studenti ha almeno uno studente
                //assegno il professore
                if (professors.get(j).getOwner() == null && MaxPlayer.numStudentsDiningRoom(colour) > 0) {
                    MaxPlayer.addProfessor(professors.get(j));
                    professors.get(j).changeOwner(MaxPlayer);

                }
            }

            //se il professore ha un owner e il player con più studenti ha più studenti dell'owner
            //assegno il professore
            else {
                if (professors.get(j).getColour() == colour && MaxPlayer.numStudentsDiningRoom(colour) > professors.get(j).getOwner().numStudentsDiningRoom(colour)) {
                    MaxPlayer.addProfessor(professors.get(j));
                    professors.get(j).getOwner().removeProfessor(professors.get(j));
                    professors.get(j).changeOwner(MaxPlayer);
                }
            }
        }
    }

    //il metodo ha la funzione di calcolare l'influenza degli studenti
    //sull'isola ed in base a chi ha più influenza sostituire le torri o meno
    //se c'è un caso di vittoria vien segnalato dalla sua eccezione
    //se l'isola è assente viene segnalato dalla sua eccezione
    public void checkTowers(int numIsland)throws MissingIslandException, MissingTowerException{
        Island island = null;

        //cerco l'isola su cui effettuare il controllo
        for(Island island1 : listIsland){
            if(island1.getNumIsland() == numIsland) {
                island = island1;
            }
        }

        //se non esiste lancio un eccezione
        if(island == null){
            characterCardThrown = "";
            throw new MissingIslandException();
        }

        //altrimenti effettuo il conto degli studenti per le torri
        else{
            //inizializzo i punti per ogni team a 0
            int team1 = 0;
            int team2 = 0;

            //associo ai team i corrispettivi punti
            //(a seconda dei professori che controllano e degli studenti presenti sul terreno)
            for(Professor prof : professors){
                if(prof.getOwner() != null &&
                        prof.getOwner().getTeam() == listTeam.get(0)){

                    team1 = team1 +island.countStudentsOfColour(prof.getColour());
                }
                else if(prof.getOwner() != null &&
                        prof.getOwner().getTeam() == listTeam.get(1)){

                    team2 = team2 +island.countStudentsOfColour(prof.getColour());
                }
            }

            //se sono presenti torri aggiungo i punti alla squadra pari al numero di torri

                try {
                    if(characterCardThrown != "Satyr") {
                        ColourTower colourTower = island.getColourTower();
                        if (colourTower.equals(listTeam.get(0).getColourTower())) {
                            team1 = team1 + island.getNumSubIsland();
                        } else {
                            team2 = team2 + island.getNumSubIsland();
                        }
                    }
                    if(characterCardThrown == "Knight"){
                        if (getPlayer(currentPlayer).getTeam() == listTeam.get(0))
                            team1 = team1 + 2;
                        else {
                            team2 = team2 + 2;
                        }
                    }
                    characterCardThrown = "";

                } catch (MissingTowerException e) {
                } catch (MissingPlayerException e){
                }finally {
                    //a questo punto confronto il valore dei team
                    //e se c'è una maggioranza riassegno le torri e controllo eventuali fusioni
                    //altrimenti chiudo il metodo

                    //caso di parità
                    if (team1 == team2) {
                        return;
                    }

                    //caso di maggioranza team1
                    //se il team1 ha finito le torri significa che ha vinto e lancio un'eccezione per
                    //richiamare il metodo checkwin
                    else if (team1 > team2) {
                        listTeam.get(0).useTowers(island.getNumSubIsland());
                        listTeam.get(1).takeTowers(island.getNumSubIsland());
                        island.setColourTower(listTeam.get(0).getColourTower());
                        fusion(numIsland);
                    }
                    // IL METODO PER UNIRE LE ISOLE CHE DEVE ESSERE RICHIAMATO NEI DUE RAMI --> fatto
                    //caso di maggioranza team2
                    //se il team2 ha finito le torri significa che ha vinto e lancio un'eccezione per
                    //richiamare il metodo checkwin
                    else {

                        listTeam.get(1).useTowers(island.getNumSubIsland());
                        listTeam.get(0).takeTowers(island.getNumSubIsland());
                        island.setColourTower(listTeam.get(1).getColourTower());
                        fusion(numIsland);
                    }
                }

        }
    }

    public void fusion(int numIsland){
        if(numIsland == 0){
            try {
                //controllo isola destra
                if(listIsland.get(numIsland+1).getColourTower().equals(listIsland.get(numIsland).getColourTower())) {
                    //fondo isole
                    Island i = new Island(listIsland.get(numIsland), listIsland.get(numIsland + 1));

                    //rimuovo, aggiungo e riordino lista
                    motherNature.move(i);
                    listIsland.remove(numIsland);
                    listIsland.remove(numIsland+1);
                    listIsland.add(i);
                    for(Island island: listIsland){
                        if (island.getNumIsland() > i.getNumIsland()) {
                            island.setNumIsland(island.getNumIsland() - 1);
                        }
                    }
                }
            }catch (MissingTowerException e){e.printStackTrace();}
            try{
                //controllo isola sinistra
                if(listIsland.get(listIsland.size()-1).getColourTower().equals(listIsland.get(numIsland).getColourTower())) {
                    //fondo isole
                    Island i = new Island(listIsland.get(numIsland), listIsland.get(listIsland.size()-1));

                    //rimuovo, aggiungo e riordino lista
                    motherNature.move(i);
                    listIsland.remove(numIsland);
                    listIsland.remove(listIsland.size()-1);
                    listIsland.add(i);
                    for(Island island: listIsland){
                        if (island.getNumIsland() > i.getNumIsland()) {
                            island.setNumIsland(island.getNumIsland() - 1);
                        }
                    }
                }
            }catch (MissingTowerException e){e.printStackTrace();}
        }
        else{
            try {
                //controllo isola destra
                if(listIsland.get((numIsland + 1)%listIsland.size()).getColourTower().equals(listIsland.get(numIsland).getColourTower())) {
                    //fondo isole
                    Island i = new Island(listIsland.get(numIsland), listIsland.get((numIsland + 1)%listIsland.size()));

                    //rimuovo, aggiungo e riordino lista
                    motherNature.move(i);
                    listIsland.remove(numIsland);
                    listIsland.remove((numIsland + 1)%listIsland.size());
                    listIsland.add(i);
                    for(Island island: listIsland){
                        if (island.getNumIsland() > i.getNumIsland()) {
                            island.setNumIsland(island.getNumIsland() - 1);
                        }
                    }
                }
            }catch (MissingTowerException e){e.printStackTrace();}
            try {
                //controllo isola sinistra
                if(listIsland.get(numIsland -1).getColourTower().equals(listIsland.get(numIsland).getColourTower())) {
                    //fondo isole
                    Island i = new Island(listIsland.get(numIsland), listIsland.get(numIsland-1));

                    //rimuovo, aggiungo e riordino lista
                    motherNature.move(i);
                    listIsland.remove(numIsland);
                    listIsland.remove(numIsland-1);
                    listIsland.add(i);
                    for(Island island: listIsland){
                        if (island.getNumIsland() > i.getNumIsland()) {
                            island.setNumIsland(island.getNumIsland() - 1);
                        }
                    }
                }
            }catch (MissingTowerException e){e.printStackTrace();}
        }
    }

    public Player getPlayer(String io)throws MissingPlayerException{
        Player playerreturn = null;

        for (Player p : listPlayer){
            if(p.getNicknameClient().equals(io)){
                playerreturn = p;}
        }
        if(playerreturn == null){
            throw new MissingPlayerException();
        }
        else{
            return playerreturn;
        }
    }

    public Cloud getCloud(int num){
        return listCloud.get(num);
    }

    public Professor getProfessor(Colour col) throws MissingProfessorException {
        Professor professorreturn = null;

        for (Professor p : professors){
            if(p.getColour().equals(col)){
                professorreturn = p;}
        }
        if(professorreturn == null){
            throw new MissingProfessorException();
        }
        else{
            return professorreturn;
        }
    }

    public Bag getBag() {
        return this.bag;
    }

    public void setCardThrown(String characterCard) {
        this.characterCardThrown = characterCard;
    }

    //todo da implementare c'è sempre il problema dei parametri:
    //o faccio un metodo per ogni effetto oppure devo passare un sacco di parametri
    //di cui alcuni inutili
    public void  doPlayCharacterCard(int numCharacter){

    }
}
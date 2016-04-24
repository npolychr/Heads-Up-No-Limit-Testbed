import game.*;
import game.cash.CashGameDescription;
import game.cash.CashGameTableSeater;
import game.deck.DeckFactory;

//For a pre existing serialized deck use this import
//Import game.deck.SerializedDeck;

//For random deck use this import
import game.deck.RandomDeck;

import game.stats.BankrollGraphUI;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import bots.BotRepository;


/**
 * This class starts a simulation on the console and writes a handhistory-file
 * to the ./data directory
 */

public class CashGameConsoleStarter {
    public static void main(String[] args) throws Exception {

        //Number of games to play
        int numGames = 100;
        
        //If to permute seats to reduce variance
        boolean permuteSeats = true;
        
        //Valid BotNames can be obtained from the botRepository       
        //You can add or remove bots as you wish (You have to rebuild the project to take the new players)
        //It is possible to run the same bot against itself as it is show here!
        //DemoBot/SimpleBot just points at where it should find the bot eg. in the folder DemoBot inside the project.
        String[] botNames = new String[]{"DemoBot/SimpleBot", "DemoBot/SimpleBot"};

        BotRepository botRepository = new BotRepository();
        TableSeater tableSeater = new CashGameTableSeater(botRepository, permuteSeats);
        GameIDGenerator gameIDGenerator = new GameIDGenerator(System.nanoTime());
        HandHistoryWriter handHistoryWriter = new HandHistoryWriter();
        String simulationFileName = new SimpleDateFormat("yyMMdd-HHmm").format(new Date());
        handHistoryWriter.setWriter(new FileWriter("./data/" + simulationFileName + "-history.txt"));

        //In the future created via GUI, and persisted via XML to the ./data/games dir
        CashGameDescription cashGameDescription = new CashGameDescription();
        
        //Enable No-Limit - False for Limit
        cashGameDescription.setNolimit(true);
        
        //Small Blind
        cashGameDescription.setSmallBlind(0.01);
        
        //Big Blind
        cashGameDescription.setBigBlind(0.02);
        
        //Stack: Currently 100bb
        cashGameDescription.setInitialBankRoll(2);
        
        cashGameDescription.setNumGames(numGames);

        cashGameDescription.setBotNames(botNames);
        
        //Start the game
        GameRunner runner = cashGameDescription.createGameRunner();
        BankrollGraphUI bankrollgraphUI = new BankrollGraphUI();
        runner.addBankrollObserver(bankrollgraphUI);
        
        //If you want to use the serialized deck, uncomment the following line and comment line 80.
        //DeckFactory deckFactory = SerializedDeck.createFactory("./data/decks/deck-100000.deck");
        
        //Random Deck
        DeckFactory deckFactory = RandomDeck.createFactory();
        
        runner.runGame(deckFactory, tableSeater, gameIDGenerator, Arrays.asList(handHistoryWriter));

        bankrollgraphUI.createGraph(simulationFileName);
    }
}

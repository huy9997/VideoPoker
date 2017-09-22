package PJ4;

import java.util.*;

/*
 * Ref: http://en.wikipedia.org/wiki/Video_poker
 *      http://www.freeslots.com/poker.htm
 *
 *
 * Short Description and Poker rules:
 *
 * Video poker is also known as draw poker.
 * The dealer uses a 52-card deck, which is played fresh after each playerHand.
 * The player is dealt one five-card poker playerHand.
 * After the first draw, which is automatic, you may hold any of the cards and draw
 * again to replace the cards that you haven't chosen to hold.
 * Your cards are compared to a table of winning combinations.
 * The object is to get the best possible combination so that you earn the highest
 * payout on the bet you placed.
 *
 * Winning Combinations
 *
 * 1. One Pair: one pair of the same card
 * 2. Two Pair: two sets of pairs of the same card denomination.
 * 3. Three of a Kind: three cards of the same denomination.
 * 4. Straight: five consecutive denomination cards of different suit.
 * 5. Flush: five non-consecutive denomination cards of the same suit.
 * 6. Full House: a set of three cards of the same denomination plus
 * 	a set of two cards of the same denomination.
 * 7. Four of a kind: four cards of the same denomination.
 * 8. Straight Flush: five consecutive denomination cards of the same suit.
 * 9. Royal Flush: five consecutive denomination cards of the same suit,
 * 	starting from 10 and ending with an ace
 *
 */
 /* This is the video poker game class.
 * It uses Decks and Card objects to implement video poker game.
 * Please do not modify any data fields or defined methods
 * You may add new data fields and methods
 * Note: You must implement defined methods
 */
public class VideoPoker {

    // default constant values
    private static final int startingBalance = 100;
    private static final int numberOfCards = 5;

    // default constant payout value and playerHand types
    private static final int[] multipliers = {1, 2, 3, 5, 6, 10, 25, 50, 1000};
    private static final String[] goodHandTypes = {
        "One Pair", "Two Pairs", "Three of a Kind", "Straight", "Flush	",
        "Full House", "Four of a Kind", "Straight Flush", "Royal Flush"};

    // must use only one deck
    private final Decks oneDeck;

    // holding current poker 5-card hand, balance, bet
    private List<Card> playerHand;
    private int playerBalance;
    private int playerBet;

    /**
     * default constructor, set balance = startingBalance
     */
    public VideoPoker() {
        this(startingBalance);
    }

    /**
     * constructor, set given balance
     */
    public VideoPoker(int balance) {
        this.playerBalance = balance;
        oneDeck = new Decks(1, false);
    }

    /**
     * This display the payout table based on multipliers and goodHandTypes
     * arrays
     */
    private void showPayoutTable() {
        System.out.println("\n\n");
        System.out.println("Payout Table   	      Multiplier   ");
        System.out.println("=======================================");
        int size = multipliers.length;
        for (int i = size - 1; i >= 0; i--) {
            System.out.println(goodHandTypes[i] + "\t|\t" + multipliers[i]);
        }
        System.out.println("\n\n");
    }

    /**
     * Check current playerHand using multipliers and goodHandTypes arrays Must
     * print yourHandType (default is "Sorry, you lost") at the end of function.
     * This can be checked by testCheckHands() and main() method.
     */
    private void checkHands() {
        ArrayList<Card> sortedHand = new ArrayList<Card>(playerHand);
        Collections.sort(sortedHand, new CompareCards());
        //System.out.println(sortedHand);
        int sorter = -1;
        if (RoyalFlush(sortedHand)) {
            sorter = 8;
        } else if (StraightFlush(sortedHand)) {
            sorter = 7;
        } else if (FourOfAKind(sortedHand)) {
            sorter = 6;
        } else if (FullHouse(sortedHand)) {
            sorter = 5;
        } else if (Flush(sortedHand)) {
            sorter = 4;
        } else if (Straight(sortedHand)) {
            sorter = 3;
        } else if (ThreeOfAKind(sortedHand)) {
            sorter = 2;
        } else if (TwoPair(sortedHand)) {
            sorter = 1;
        } else if (Pair(sortedHand)) {
            sorter = 0;
        }

        
        YourHand();

        switch (sorter) {

            case 0:
                System.out.println(goodHandTypes[sorter]);
               playerBalance +=( playerBet *= multipliers[sorter]);

                break;
            case 1:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);
                
                break;
            case 2:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 3:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 4:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 5:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 6:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 7:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;
            case 8:
                System.out.println(goodHandTypes[sorter]);
                playerBalance +=(playerBet *= multipliers[sorter]);

                break;

            default:
                System.out.println("You lost! ");
                playerBet = 0;
                break;
        }

    }

    class CompareCards implements Comparator<Card> {
        // overide compare() method
        public int compare(Card o1, Card o2) {
//              if (o1.getRank() < o2.getRank()){
//                return 1;
//            } else if (o1.getRank() > o2.getRank()) {
//                return -1;
//            }
//            return 0;
            return o1.getRank()  -o2.getRank();
        }
    }

    private void YourHand() {
        for (int i = 0; i < playerHand.size(); i++) {
            System.out.print(playerHand.get(i).toString() + "  ");
        }
    }

    /*    "One Pair (1)", "Two Pairs(2)", "Three of a Kind(3)", "Straight(4)", "Flush(5)	",
        "Full House(6)", "Four of a Kind(7)", "Straight Flush(8)", "Royal Flush"(9)};
     */
    private boolean Pair(ArrayList<Card> YourHand) {
        boolean pair = false;

        for (int i = 0; i < 4 && !pair; i++) {
            if (YourHand.get(i).getRank() == YourHand.get(i + 1).getRank()) {
                pair=true;
            
            }
        } 
        return pair;
    } //1 

    private boolean TwoPair(ArrayList<Card> YourHand) {
        boolean twoPair = false;
        for (int i = 0; i < 2 && !twoPair; i++) {
            if (YourHand.get(i).getRank() == YourHand.get(i + 1).getRank()) {
                for (int x = i + 2; x < 4; x++) {
                    if (YourHand.get(x).getRank() == YourHand.get(x + 1).getRank()) {
                        twoPair = true;
                    }
                }
            }
        }

        return twoPair;
    }//2

    private boolean ThreeOfAKind(ArrayList<Card> YourHand) {
        boolean three = false;
        for (int i = 0; i < 3 && !three; i++) {
            if (YourHand.get(i).getRank() == YourHand.get(i + 1).getRank()
                    && YourHand.get(i + 1).getRank() == YourHand.get(i + 2).getRank()) {
                three = true;
            }
        }

        return three;
    }

    private boolean Straight(ArrayList<Card> YourHand) {
        boolean straight = true;
        for (int i = 0; i < 4 && straight; i++) {
            if (YourHand.get(i).getRank() + 1 != YourHand.get(i + 1).getRank()) {
                straight = false;
            }
        }
        return (straight || AceHighStraight(YourHand));//number 4
    }

    private boolean AceHighStraight(ArrayList<Card> YourHand) {
        return (YourHand.get(0).getRank() == 1
                && YourHand.get(1).getRank() == 10
                && YourHand.get(2).getRank() == 11
                && YourHand.get(3).getRank() == 12
                && YourHand.get(4).getRank() == 13);
    }

    private boolean Flush(ArrayList<Card> YourHand) {
        return (YourHand.get(0).getSuit() == YourHand.get(1).getSuit()
                && YourHand.get(1).getSuit() == YourHand.get(2).getSuit()
                && YourHand.get(2).getSuit() == YourHand.get(3).getSuit()
                && YourHand.get(3).getSuit() == YourHand.get(4).getSuit());
        //number 5
    }

    private boolean FullHouse(ArrayList<Card> YourHand) {
        boolean house = false;
        if (YourHand.get(0).getRank() == YourHand.get(1).getRank()) {
            if (YourHand.get(1).getRank() == YourHand.get(2).getRank()) {
                if (YourHand.get(3).getRank() == YourHand.get(4).getRank()) {
                    house = true;
                }// both cases of getting a full house
            } else if ((YourHand.get(2).getRank() == YourHand.get(3).getRank())
                    && (YourHand.get(3).getRank() == YourHand.get(4).getRank())) {
                house = true;
            }
        }
        return house;
    }

    //number 6
    private boolean FourOfAKind(ArrayList<Card> YourHand) {
        boolean four = false;
        for (int i = 0; i < 2 && !four; i++) {
            if (YourHand.get(i).getRank() == YourHand.get(i + 1).getRank()
                    && YourHand.get(i + 1).getRank() == YourHand.get(i + 2).getRank()
                    && YourHand.get(i + 2).getRank() == YourHand.get(i + 3).getRank()) {
                four = true;
            }
        }

        return four;
    } // number 7

    private boolean StraightFlush(ArrayList<Card> YourHand) {
        boolean straightFlush = false;
        if (Flush(YourHand) == true && Straight(YourHand) == true) {
            straightFlush = true;
        }
        return straightFlush;//number 
    }

    private boolean RoyalFlush(ArrayList<Card> YourHand) {
        boolean royal = false;
        if (AceHighStraight(YourHand) == true && Flush(YourHand) == true) {

            royal = true;
            ;//number 9
        }

        return royal;
    }

    /*
    "One Pair (1)", "Two Pairs(2)", "Three of a Kind(3)", "Straight(4)", "Flush(5)	",
        "Full House(6)", "Four of a Kind(7)", "Straight Flush(8)", "Royal Flush"(9)};

     */
    private boolean tryAgain() {
        boolean StartNewGame = false;
        if (playerBalance <= 0) {
            System.out.println("Your broke (0$)  you should stop playing.");
            return StartNewGame;
        }
        System.out.println("You Balance is:" + playerBalance + "Would you like to keep playing? y or n");
        Scanner repeatGame = new Scanner(System.in);
        if (repeatGame.hasNext() && repeatGame.nextLine().equalsIgnoreCase("y")) {

            System.out.println("Do you want to see payout table? y or n");
            Scanner showPayoutTable = new Scanner(System.in);
            if (showPayoutTable.hasNext() && showPayoutTable.nextLine().equalsIgnoreCase("y")) {
                showPayoutTable();
                oneDeck.reset();
                StartNewGame = true;
            } else {
                StartNewGame = true;
            }
        } else {
            System.out.println("Game Ended");
            StartNewGame = false;
        }

        return StartNewGame;
    }

    private void ReplaceCards() {
        Scanner replace = new Scanner(System.in);
        List<Card> hold = new ArrayList<Card>();
        System.out.println("\nEnter the positions of the cards (1-5) you would like to hold: ");
        String heldPlace = replace.nextLine();
        Scanner position = new Scanner(heldPlace);
        position = position.useDelimiter("\\s*");
        try {
            hold = oneDeck.deal(numberOfCards);
        } catch (PlayingCardException x) {
            System.out.println("Error with dealing cards.");
        }
        while (position.hasNext()) {
            String nums = position.findInLine("\\d+");
            hold.set(Integer.parseInt(nums) - 1, playerHand.get(Integer.parseInt(nums) - 1));
        }
        playerHand = hold;
    }

    public void play() {
        
        
        showPayoutTable();
        boolean play = true;
        while (play) {
            System.out.println("\nYour current balance is: $" + playerBalance);

            System.out.print("Please enter the amount you would like to bet: $");
            Scanner getBet = new Scanner(System.in);

            do {
                playerBet = getBet.nextInt();
            } while (!(playerBet > 0) || !(playerBet <= playerBalance));

            playerBalance -= playerBet;

            oneDeck.reset();
            oneDeck.shuffle();

            try {
                playerHand = oneDeck.deal(numberOfCards);
            } catch (PlayingCardException e) {
                System.out.println("Exception dealing a new hand" + e.getMessage());
            }

            for (int i = 0; i < playerHand.size(); i++) {
                System.out.print(playerHand.get(i).toString() + " ");
            }
            ReplaceCards();
            checkHands();

            play = tryAgain();
        }
    }

    /*
         * The main algorithm for single player poker game
         *
         * Steps: showPayoutTable()
         *
         * ++ show balance, get bet verify bet value, update balance reset deck,
         * shuffle deck, deal cards and display cards ask for positions of cards
         * to replace get positions in one input line update cards check hands,
         * display proper messages update balance if there is a payout if
         * balance = O: end of program else ask if the player wants to play a
         * new game if the answer is "no" : end of program else :
         * showPayoutTable() if user wants to see it goto ++
     */
    // implement this method!
    //}
    /**
     * ***********************************************
     * Do not modify methods below
     * /*************************************************
     *
     * /** testCheckHands() is used to test checkHands() method checkHands()
     * should print your current hand type
     */
    public void testCheckHands() {
        try {
            playerHand = new ArrayList<Card>();

            // set Royal Flush
            playerHand.add(new Card(3, 1));
            playerHand.add(new Card(3, 10));
            playerHand.add(new Card(3, 12));
            playerHand.add(new Card(3, 11));
            playerHand.add(new Card(3, 13));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Straight Flush
            playerHand.set(0, new Card(3, 9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Straight
            playerHand.set(4, new Card(1, 8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Flush
            playerHand.set(4, new Card(3, 5));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // "Royal Pair" , "Two Pairs" , "Three of a Kind", "Straight", "Flush	",
            // "Full House", "Four of a Kind", "Straight Flush", "Royal Flush" };
            // set Four of a Kind
            playerHand.clear();
            playerHand.add(new Card(4, 8));
            playerHand.add(new Card(1, 8));
            playerHand.add(new Card(4, 12));
            playerHand.add(new Card(2, 8));
            playerHand.add(new Card(3, 8));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Three of a Kind
            playerHand.set(4, new Card(4, 11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Full House
            playerHand.set(2, new Card(2, 11));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set Two Pairs
            playerHand.set(1, new Card(2, 9));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set One Pair
            playerHand.set(0, new Card(2, 3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set One Pair
            playerHand.set(2, new Card(4, 3));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");

            // set no Pair
            playerHand.set(2, new Card(4, 6));
            System.out.println(playerHand);
            checkHands();
            System.out.println("-----------------------------------");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /* Quick testCheckHands() */
    public static void main(String args[]) {
        VideoPoker pokergame = new VideoPoker();
        pokergame.testCheckHands();
        
    }
}

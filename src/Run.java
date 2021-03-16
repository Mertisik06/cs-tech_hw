import java.util.*;

public class Run {
    static int[] first = new int[10];
    static int[] second = new int[10];
    static int[] third = new int[10];
    static int[] last = new int[10];
    static int[] Positive = new int[10];
    static int[] Negative = new int[10];
    static int Ptemp, Ntemp;
    static int index=0,n;
    public static void main(String[] args) {
        boolean game_end=false;
        int[] random = createNumber();

        /*for (int i = 0; i < 4 ;i++) { // Look computer guess
            System.out.print(random[i]);
        }*/

        int indexMatch = 0;
        int matchnumber;
        System.out.println("Choose a number , game will start ...");
        int turn=1;
        while(!game_end){
            System.out.println("_______________________________________________________");
            System.out.println("Computer is guessing for round : "+turn);
            if (Positive[index] != 4) {
                createGuess();
            }
            System.out.println("Computer guess is : " + first[index] + second[index] + third[index] + last[index]);
            System.out.print("Output for computer guess (Positive,Negative) : ");
            Scanner keyboard = new Scanner(System.in);
            String out = keyboard.nextLine();
            Positive[index] = out.charAt(0)-48;
            Negative[index] = -Integer.parseInt(out.substring(out.lastIndexOf(',') + 1).trim());
            if(Positive[index]==4){
                game_end = true;
                System.out.println("Game over winner is : Computer . The number that human pick is :"+ first[index] + second[index] + third[index] + last[index]);
                break;
            }
            index++;

            System.out.println("Human is guessing for round : "+turn);
            if(indexMatch != 4) {
                int[] guess = getGuess();
                indexMatch = 0;
                matchnumber = 0;
                for (int i = 0; i < guess.length; i++) {
                    if (guess[i] == random[i]) {
                        indexMatch++;
                    } else if (guess[i] == random[0] || guess[i] == random[1] || guess[i] == random[2] || guess[i] == random[3]) {
                        matchnumber--;
                    }
                }
                if (indexMatch == 4) {
                    System.out.println("Game over winner is : Human . The number that computer pick is :" );
                    for (int i = 0; i < guess.length; i++) {
                        System.out.print(guess[i]);
                    }
                    game_end = true;
                    break;
                } else {
                    System.out.println("Output for human guess (Positive,Negative) : "+indexMatch+","+matchnumber);
                }
            }
            turn++;
        }
    }
    public static void createGuess(){
        int count = 0;
        int[] create = createNumber();
        last[index] = create[3];
        third[index] = create[2];
        second[index] = create[1];
        first[index] = create[0];
        for (n = 0; n < index; n++)
        {
            Ptemp = 0; Ntemp = 0;
            if (first[n] == second[index] || first[n] == third[index] || first[n] == last[index]) { Ntemp++; } else if (first[n] == first[index]) { Ptemp++; }
            if (second[n] == first[index] || second[n] == third[index] || second[n] == last[index]) { Ntemp++; } else if (second[n] == second[index]) { Ptemp++; }
            if (third[n] == second[index] || third[n] == first[index] || third[n] == last[index]) { Ntemp++; } else if (third[n] == third[index]) { Ptemp++; }
            if (last[n] == second[index] || last[n] == third[index] || last[n] == first[index]) { Ntemp++; } else if (last[n] == last[index]) { Ptemp++; }
            // Üretilen sayıya bakılıp Positive,Negative sayısına erişilmesi kontrol ediliyor . Eşitse kullanılıyor .
            if (Ptemp != Positive[n] || Ntemp != Negative[n])
            {
                int[] create1 = createNumber();
                last[index] = create1[3];
                third[index] = create1[2];
                second[index] = create1[1];
                first[index] = create1[0];
                count++;
                if(count==10000) {
                    System.out.println("The game was terminated , please enter the correct answers in the next game...");
                    System.exit(0);
                }
                n = -1;
            }
        }
    }
    public static int[] getGuess() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter your guess: ");
        String input = keyboard.nextLine();
        if (input.length() != 4) {
            System.out.println("Invalid number. You must enter 4 digits between 0-9 only.");
            return getGuess();
        }
        int[] guess = new int[4];
        for (int i = 0; i < 4; i++) {
            guess[i] = Integer.parseInt(String.valueOf(input.charAt(i)));
        }
        return guess;
    }
    public static int[] createNumber() {
        String result = "";
        int[] last= new int[4]; ;
        int random;
        int count=0;
        while(true){
            random  = (int) ((Math.random() * (10 )));
            if(result.length() == 0 && random == 0){//the number doesn't start with 0
                random+=1;
                result+=random;
                last[count]=random;
                count++;
            }
            else if(!result.contains(Integer.toString(random))){//add it to the result
                result+=Integer.toString(random);
                last[count]=random;
                count++;
            }
            if(result.length()>=4){//return the final result
                break;
            }
        }
        return last;
    }
}

import java.util.Random;

public class Generator {

    public static String generateAge(){
        Random r = new Random();
        int age =r.nextInt(45);
        return String.valueOf(age);
    }

    public static String generateScore(){
        Random r = new Random();
        int score = r.nextInt(1000);
        return String.valueOf(score);
    }

    public static String generateUserName(){
        Random r = new Random();
        int index = r.nextInt(15);
        switch (index){
            case 0:return "Mandy";
            case 1:return "XXTheRealYou";
            case 2:return "Dandy";
            case 3:return "Fuji";
            case 4:return "Accel";
            case 5:return "Flora";
            case 6:return "Robert";
            case 7:return "Sandy";
            case 8:return "Anna";
            case 9:return "Browdy";
            case 10:return "Jack";
            case 11:return "Oliver";
            case 12:return "Shelly";
            case 13:return "Rose";
            case 14:return "XqS";
            case 15:return "Paul";
        }
        return null;
    }

    public static String generateLevel(){
        Random r = new Random();
        int level = r.nextInt(100);
        return String.valueOf(level);
    }

}

import java.util.Scanner;
import java.io.File;
import java.nio.file.*;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Dao DbConnect = new Dao();

        System.out.println("Welcome to teste");
        String userContinue = "Y";
        Integer userInput = 0;
        Integer albumId, artistId = 0;
        String albumTitle = null;

        while (userContinue.equalsIgnoreCase("Y")) {
            System.out.println("Choose an option:");
            System.out.println("-> 1 - Insert");
            System.out.println("-> 2 - Delete");
            System.out.println("-> 3 - Update");
            System.out.println("-> 4 - Select");

            try{
                userInput = Integer.parseInt(scanner.nextLine().trim());
            } catch (Exception error) {
                System.out.println("Invalid input");
            }

            switch (userInput){
                case 1:
                    try{
                        System.out.println("Album title: ");
                        albumTitle = scanner.nextLine().trim();

                        System.out.println("Album artist id: ");
                        artistId = Integer.parseInt(scanner.nextLine().trim());

                        DbConnect.insert(albumTitle, artistId);

                    } catch (Exception error) {
                        System.out.println("Invalid input");
                    };
                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    DbConnect.select();
                    break;

            }

            System.out.println("Continue? (Y/N)");
            userContinue = scanner.nextLine().trim();
        }

    }
}

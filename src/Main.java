import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Series riverdale = new Series("Riverdale", riverdaleEpisodes());
        Series friends = new Series("Friends",friendsEpisodes());
        Series theVoice = new Series("The Voice", theVoiceEpisodes());
        Series[] seriesList = {riverdale, friends, theVoice};
        UserAccountActions accountActions = new UserAccountActions(seriesList);
        while (true) {
            boolean showMenuOptions = true;
            System.out.println("Welcome!\nPlease enter your option of choice:\n(1) Open a new account\n(2) " +
                    "Sign in an existing account\n---------------------------------------------------------");
            System.out.println("Your choice:");
            Scanner scanner = new Scanner(System.in);
            int usersChoice1 = scanner.nextInt();
            if ((usersChoice1 > Constants.SIGN_IN_OPTION) || (usersChoice1 < Constants.REGISTRATION_OPTION)) {
                System.out.println("Invalid number entered!");
            } else {
                UserAccount currentUserAccount = new UserAccount();
                if (usersChoice1 == Constants.REGISTRATION_OPTION) {
                    currentUserAccount = registerANewAccount(accountActions);
                } else {
                    boolean accountExists = checkIfAccountExists(accountActions);
                    if (!accountExists) {
                        System.out.println("This account does not exist!");
                        System.out.println("-----------------------------------------------");
                        showMenuOptions = false;
                    } else {
                        System.out.println("account found!");
                        System.out.println("-----------------------------------------------");
                        currentUserAccount = accountActions.signIntoExistingAccount();
                    }
                }
                while (showMenuOptions) {
                    showMenuOptions();
                    int usersChoice2 = scanner.nextInt();
                    switch (usersChoice2) {
                        case 1:
                            System.out.println("List of all series:");
                            for (int i = 0; i < seriesList.length; i++) {
                                System.out.println("(" + i + ") - " + seriesList[i].getName());
                            }
                            break;
                        case 2:
                            SeriesWatched[] usersWatchedSeries = currentUserAccount.getSeriesWatched();
                            System.out.println("List of series which you started watching:");
                            for (int i = 0; i < usersWatchedSeries.length; i++) {
                                if (usersWatchedSeries[i].getSeries().isWasWatched()) {
                                    System.out.println(usersWatchedSeries[i].getSeries().getName());
                                    System.out.println("Last episode watched:");
                                    int lastEpisodeWatched = usersWatchedSeries[i].getSeries().lastEpisodeWatched();
                                    usersWatchedSeries[i].getSeries().getEpisodeList()[--lastEpisodeWatched].printEpisodeInfo();
                                    System.out.println("--------------------------------------------");
                                } else if (i == usersWatchedSeries.length - 1 && !usersWatchedSeries[i].getSeries().isWasWatched()) {
                                    System.out.println("You didn't watch any shows yet!");
                                }
                            }
                            break;
                        case 3:
                            accountActions.getUserAccount().printUserDetails();
                            currentUserAccount.numberOfSeriesWatched();
                            currentUserAccount.numberOfEpisodesWatched();
                            System.out.println("-----------------------");
                            break;
                        case 4:
                            System.out.println("Please enter the name of the series you would like to watch:");
                            String usersChoice = scanner.nextLine();
                            String catchEnter = scanner.next();
                            boolean exists = checkIfSeriesExists(catchEnter, seriesList);
                            if (!exists) {
                                System.out.println("The series you typed does not exist");
                            } else {
                                Series wantedSeries = returnWantedSeriesToWatch(catchEnter, seriesList);
                                SeriesWatched seriesChosenByUser = new SeriesWatched(wantedSeries, wantedSeries.lastEpisodeWatched());
                                for (int i = 0; i < seriesList.length; i++) {
                                    if (catchEnter.equals(seriesList[i].getName())) {
                                        System.out.println("Episode list:");
                                        for (int j = 0; j < seriesList[i].getEpisodeList().length; j++) {
                                            seriesList[i].getEpisodeList()[j].printEpisodeInfo();
                                        }
                                        if (seriesList[i].isWasWatched()) {
                                            System.out.println("Last episode watched:");
                                            int lastEpisodeWatched = seriesList[i].lastEpisodeWatched();
                                            seriesList[i].getEpisodeList()[--lastEpisodeWatched].printEpisodeInfo();
                                            System.out.println("--------------------------------------------");

                                        }
                                    }
                                }
                                System.out.println("Please enter the number of episode you want to watch:");
                                int usersEpisodeChoice = scanner.nextInt();
                                currentUserAccount.markSeriesAsWatched(seriesChosenByUser);
                                currentUserAccount.markEpisodeAsWatched(usersEpisodeChoice, seriesChosenByUser);
                                System.out.println("Enjoy your watch!");
                            }
                            break;
                        case 5:
                            showMenuOptions = false;
                            break;
                    }
                }
            }
        }
    }

    public static UserAccount registerANewAccount (UserAccountActions registration){
        Scanner scanner = new Scanner(System.in);
        UserAccount newUserAccount = new UserAccount();
        registration.setUserAccount(newUserAccount);
        //UserAccountActions registration = new UserAccountActions(newUserAccount);
        System.out.println("Please choose and write your user name:");
        newUserAccount.setUserName(scanner.nextLine());
        while(registration.userNameIsTaken(newUserAccount)){
            System.out.println("The user name you entered is taken, please choose a different one:");
            newUserAccount.setUserName(scanner.nextLine());
        }
        System.out.println("Please choose and write your password - at least 6 characters, one english letter and one digit:");
        newUserAccount.setPassword(scanner.nextLine());
        while(!registration.passwordIsStrong(newUserAccount)){
            System.out.println("The password you chose is not strong enough, please choose a different one:");
            newUserAccount.setPassword(scanner.nextLine());
        }
        registration.addAUserAccount(newUserAccount);
        return newUserAccount;
    }

   public static boolean checkIfAccountExists(UserAccountActions accountActions){
        Scanner scanner = new Scanner(System.in);
        UserAccount userAccount = new UserAccount();
        System.out.println("Please enter your user name:");
        userAccount.setUserName(scanner.nextLine());
        System.out.println("Please enter your password:");
        userAccount.setPassword(scanner.nextLine());
        return accountActions.checkIfExists(userAccount);
    }

    public static Series returnWantedSeriesToWatch (String usersChoice, Series[] seriesList) {
        for (int i = 0; i < seriesList.length; i++) {
            if (usersChoice.equals(seriesList[i].getName())) {
                return seriesList[i];
            }
        }
        return null;
    }


    public static boolean checkIfSeriesExists (String usersChoice, Series[] seriesList){
        for (int i=0; i<seriesList.length; i++){
            if(usersChoice.equals(seriesList[i].getName())){
                return true;
            }
        }
        return false;
    }

    public static int returnValidUsersChoiceNumber (int usersChoice){
        Scanner scanner = new Scanner(System.in);
        while(usersChoice<Constants.REGISTRATION_OPTION || usersChoice> Constants.SIGN_IN_OPTION){
            System.out.println("The number you entered is not valid, please try again:");
            usersChoice = scanner.nextInt();
        }
        return usersChoice;

    }

    public static Episode[] riverdaleEpisodes (){
        Episode[] riverdaleEpisodes = {new Episode(Constants.FIRST_EPISODE_INDEX, "Archie and Betty", new Date()),
                new Episode(Constants.SECOND_EPISODE_INDEX, "Veronica and Jughead", new Date()),
                new Episode(Constants.THIRD_EPISODE_INDEX, "Going to prom", new Date())};
        return riverdaleEpisodes;
    }

    public static Episode[] friendsEpisodes (){
        Episode[] friendsEpisodes = {new Episode(Constants.FIRST_EPISODE_INDEX, "Ross and Rachel", new Date()),
                new Episode(Constants.SECOND_EPISODE_INDEX, "Phoebe and Joey", new Date()),
                new Episode(Constants.THIRD_EPISODE_INDEX, "Chandler and Monica", new Date())};
        return friendsEpisodes;
    }

    public static Episode[] theVoiceEpisodes (){
        Episode[] theVoiceEpisodes = {new Episode(Constants.FIRST_EPISODE_INDEX, "Auditions", new Date()),
                new Episode(Constants.SECOND_EPISODE_INDEX, "Battles", new Date()),
                new Episode(Constants.THIRD_EPISODE_INDEX, "Final", new Date())};
        return theVoiceEpisodes;
    }


    public static void showMenuOptions (){
        System.out.println("Please choose your next wanted action:\n(1) - Show a list of all series\n" +
                "(2) - Show list of series I started watching\n(3) - Show account details\n" +
                "(4) - choose a series to watch\n(5) - Sign out");
    }
}


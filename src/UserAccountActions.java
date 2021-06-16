public class UserAccountActions {

    private UserAccount[] userAccountsList;
    private UserAccount userAccount;
    private Series[] seriesList;

    public UserAccountActions (){}

    public UserAccountActions (Series[] seriesList){
        this.seriesList = seriesList;
    }

    public UserAccountActions (UserAccount userAccount){
        this.userAccount = userAccount;
    }

    public UserAccount signIntoExistingAccount (){
        for(int i=0;i<userAccountsList.length;i++){
             if(this.checkIfExists(userAccountsList[i])){
                 return userAccountsList[i];
             }
        }
        return null;
    }

    public boolean checkIfExists(UserAccount userAccount){
        if (userAccountsList == null) return false;
        boolean passwordMatch = false;
        for(int i=0;i<userAccountsList.length; i++){
            if(userAccount.getUserName().equals(userAccountsList[i].getUserName())){
                if(userAccount.getPassword().equals(userAccountsList[i].getPassword())){
                    passwordMatch = true;
                    break;
                }
            }
        }
        return passwordMatch;
    }

    public boolean passwordIsStrong (UserAccount newUserAccount) {
        int countDigits = 0;
        int countLetters = 0;
        if (newUserAccount.getPassword().length() < Constants.MIN_REQUIRED_CHARACTERS) return false;
        for (int i = 0; i < newUserAccount.getPassword().length(); i++) {
            if (newUserAccount.getPassword().charAt(i) > '0' && newUserAccount.getPassword().charAt(i) < '9') {
                countDigits++;
            }
            if (Character.isAlphabetic(newUserAccount.getPassword().charAt(i))) {
                countLetters++;
            }
        }
        if (countLetters > 0 && countDigits > 0) {
            return true;
        }
        return false;
    }

    public void addAUserAccount (UserAccount newUserAccount) {
        if (userAccountsList == null) {
            UserAccount[] newUserAccountsList = new UserAccount[1];
            newUserAccountsList[Constants.FIRST_ARRAY_INDEX] = newUserAccount;
            this.userAccountsList = newUserAccountsList;
        } else {
            int counter = 0;
            for (int i = 0; i < userAccountsList.length; i++) {
                counter++;
            }
            if (counter >= 1) {
                UserAccount[] newUserAccountsList = new UserAccount[counter + 1];
                for (int i = 0; i < userAccountsList.length; i++) {
                    newUserAccountsList[i] = userAccountsList[i];
                    newUserAccountsList[i].setUserAccountId(i);
                }
                newUserAccountsList[counter] = newUserAccount;
                this.userAccountsList = newUserAccountsList;
            }
        }
    }

    public boolean userNameIsTaken(UserAccount newUserAccount){
        if (userAccountsList == null) return false;
        for(int i=0; i<userAccountsList.length; i++){
            if(newUserAccount.getUserName().equals(userAccountsList[i].getUserName())){
                return true;
            }
        }
        return false;
    }

    public Series[] getSeriesList() {
        return seriesList;
    }

    public void setSeriesList(Series[] seriesList) {
        this.seriesList = seriesList;
    }

    public UserAccount[] getUserAccountsList() {
        return userAccountsList;
    }

    public void setUserAccountsList(UserAccount[] userAccountsList) {
        this.userAccountsList = userAccountsList;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}

import java.util.Date;

public class UserAccount {
    private int userAccountId;
    private String userName;
    private String password;
    private Date creationDate;
    private Date expirationDate;
    private SeriesWatched[] seriesWatched;


    public UserAccount(){
        this.creationDate = new Date();
    }

    public void printUserDetails(){
        System.out.println("Creation date: "+this.creationDate+"\nExpiration date: 30 days from now\n");
    }

    public void numberOfSeriesWatched (){
        int counter = 0;
        for(int i=0; i<seriesWatched.length;i++){
            if(seriesWatched[i].getSeries().isWasWatched()){
                counter++;
            }
        }
        System.out.println("Total number of series you have watched: "+counter);
    }

    public void numberOfEpisodesWatched(){
        int counter = 0;
        for (int i=0; i<seriesWatched.length; i++){
            for(int j=0; j<seriesWatched[i].getSeries().getEpisodeList().length; j++){
                if(seriesWatched[i].getSeries().getEpisodeList()[j].isWasWatched()){
                    counter++;
                }
            }
        }
        System.out.println("Total number of episodes you have watched: "+counter);
    }

    public void markSeriesAsWatched(SeriesWatched wantedSeries){
        if(seriesWatched == null){
            SeriesWatched[] newSeriesWatched = new SeriesWatched[1];
            newSeriesWatched[Constants.FIRST_ARRAY_INDEX] = wantedSeries;
            wantedSeries.getSeries().setWasWatched(true);
            this.seriesWatched = newSeriesWatched;
        } else {
            int counter = 0;
            for (int i = 0; i < seriesWatched.length; i++) {
                counter++;
            }
            if (counter >= 1) {
                SeriesWatched[] newSeriesWatched = new SeriesWatched[counter + 1];
                for (int i = 0; i < seriesWatched.length; i++) {
                    newSeriesWatched[i] = seriesWatched[i];
                }
                newSeriesWatched[counter] = wantedSeries;
                wantedSeries.getSeries().setWasWatched(true);
                this.seriesWatched = newSeriesWatched;
               }
           }
        }

    public void markEpisodeAsWatched (int episodeIndex,SeriesWatched wantedSeries) {
        if (wantedSeries.getSeries().isWasWatched()) {
            for (int i = 0; i < seriesWatched.length; i++) {
                if(seriesWatched[i] == wantedSeries){
                    seriesWatched[i].getSeries().markEpisodeAsWatched(--episodeIndex);
                }
            }
        }
    }

    public int getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(int userAccountId) {
        this.userAccountId = userAccountId;
    }

    public SeriesWatched[] getSeriesWatched() {
        return seriesWatched;
    }

    public void setSeriesWatched(SeriesWatched[] seriesWatched) {
        this.seriesWatched = seriesWatched;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}
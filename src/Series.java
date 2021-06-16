public class Series {

    private String name;
    private Episode[] episodeList;
    private boolean wasWatched;

    public Series (String name,Episode[] episodeList){
        this.name = name;
        this.episodeList = episodeList;
        this.wasWatched = false;
    }

    public void markEpisodeAsWatched(int usersEpisodeChoice){
        for(int i=0; i<episodeList.length;i++){
            if(i==usersEpisodeChoice){
                episodeList[i].setWasWatched(true);
            }
        }
    }

    public int lastEpisodeWatched(){
        int counter = 0;
        for (int i = 0; i<episodeList.length; i++){
            if(episodeList[i].isWasWatched()){
                counter++;
            }
        }
        return counter;
    }

    public boolean isWasWatched() {
        return wasWatched;
    }

    public void setWasWatched(boolean wasWatched) {
        this.wasWatched = wasWatched;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Episode[] getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(Episode[] episodeList) {
        this.episodeList = episodeList;
    }
}

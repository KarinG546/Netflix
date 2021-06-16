import java.util.Date;

public class Episode {

    private int episodeId;
    private String summary;
    private Date airingDate;
    private boolean wasWatched;

    public Episode (int episodeId,String summary, Date airingDate){
        this.episodeId = episodeId;
        this.summary = summary;
        this.airingDate = airingDate;
        this.wasWatched = false;
    }

    public void printEpisodeInfo(){
        System.out.println("Episode number: "+this.episodeId+"\nEpisode summary: "+this.summary+"\n" +
                "Episode airing date: "+this.airingDate);
        System.out.println("-----------------------------------------");
    }

    public boolean isWasWatched() {
        return wasWatched;
    }

    public void setWasWatched(boolean wasWatched) {
        this.wasWatched = wasWatched;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public Date getAiringDate() {
        return airingDate;
    }

    public void setAiringDate(Date airingDate) {
        this.airingDate = airingDate;
    }
}

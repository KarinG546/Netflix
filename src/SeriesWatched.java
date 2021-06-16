public class SeriesWatched {

    private Series series;
    private int lastEpisodeWatched;

    public SeriesWatched (Series series, int lastEpisodeWatched){
        this.series = series;
        this.lastEpisodeWatched = lastEpisodeWatched;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public int getLastEpisodeWatched() {
        return lastEpisodeWatched;
    }

    public void setLastEpisodeWatched(int lastEpisodeWatched) {
        this.lastEpisodeWatched = lastEpisodeWatched;
    }
}

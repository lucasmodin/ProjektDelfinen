package Sortmethods;

public enum SwimmingDiscipline {

    BREASTSTROKE("Brystsvømning"),
    BUTTERFLY("Butterfly"),
    CRAWL("Crawl"),
    BACKCRAWL("Rygsvømning"),
    STOP(null);

    public String discipline;

    SwimmingDiscipline(String discipline){
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }
}

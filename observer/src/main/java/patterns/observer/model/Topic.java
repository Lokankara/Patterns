package patterns.observer.model;

public enum Topic {
    ENTERTAINMENT,
    TECHNOLOGY,
    SCIENCE,
    SPORT,
    MUSIC,
    NEWS;

    public boolean equalsIgnoreCase(Topic topic) {
        return this == topic || (topic != null
                && this.name().equalsIgnoreCase(topic.name()));
    }
}

package model.incidence;

public class Incidence {
    private int id;
    private IncidenceType type;
    private String description;

    public IncidenceType getType() {
        return type;
    }

    public void setType(IncidenceType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}

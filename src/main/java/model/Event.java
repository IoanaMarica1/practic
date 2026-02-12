package model;

public class Event {
    private Integer id;
    private Integer fahrerId;
    private Typ typ;
    private Integer basePoints;
    private Integer lap;

    public Event(){}

    public Event(Integer id, Integer fahrerId, Typ typ, Integer basePoints, Integer lap) {
        this.id = id;
        this.fahrerId = fahrerId;
        this.typ = typ;
        this.basePoints = basePoints;
        this.lap = lap;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFahrerId() {
        return fahrerId;
    }

    public void setFahrerId(Integer fahrerId) {
        this.fahrerId = fahrerId;
    }

    public Typ getTyp() {
        return typ;
    }

    public void setTyp(Typ typ) {
        this.typ = typ;
    }

    public Integer getBasePoints() {
        return basePoints;
    }

    public void setBasePoints(Integer basePoints) {
        this.basePoints = basePoints;
    }

    public Integer getLap() {
        return lap;
    }

    public void setLap(Integer lap) {
        this.lap = lap;
    }
}

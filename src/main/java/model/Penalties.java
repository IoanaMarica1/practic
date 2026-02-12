package model;

public class Penalties {

    private Integer id;
    private Integer fahrerId;
    private Grund grund;
    private Integer seconds;
    private Integer lap;

    public Penalties(){}

    public Penalties(Integer id, Integer fahrerId, Grund grund, Integer seconds, Integer lap) {
        this.id = id;
        this.fahrerId = fahrerId;
        this.grund = grund;
        this.seconds = seconds;
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

    public Grund getGrund() {
        return grund;
    }

    public void setGrund(Grund grund) {
        this.grund = grund;
    }

    public Integer getSeconds() {
        return seconds;
    }

    public void setSeconds(Integer seconds) {
        this.seconds = seconds;
    }

    public Integer getLap() {
        return lap;
    }

    public void setLap(Integer lap) {
        this.lap = lap;
    }
}

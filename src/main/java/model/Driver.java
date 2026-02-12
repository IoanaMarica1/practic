package model;

public class Driver {
    private Integer id;
    private String name;
    private String team;
    private Status status;
    private Integer skillLevel;

    public Driver(){}

    public Driver(Integer id, String name, String team, Status status, Integer skillLevel) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.status = status;
        this.skillLevel = skillLevel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Integer skillLevel) {
        this.skillLevel = skillLevel;
    }
}

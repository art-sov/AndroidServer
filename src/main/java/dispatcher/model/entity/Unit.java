package dispatcher.model.entity;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class Unit {
    private int id;
    private int codeStantion;
    private int unitGroup;
    private int unitSubgroup;
    private String name;
    private String specification;
    private int och;
    private int active;

    public Unit() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeStantion() {
        return codeStantion;
    }

    public void setCodeStantion(int codeStantion) {
        this.codeStantion = codeStantion;
    }

    public int getUnitGroup() {
        return unitGroup;
    }

    public void setUnitGroup(int unitGroup) {
        this.unitGroup = unitGroup;
    }

    public int getUnitSubgroup() {
        return unitSubgroup;
    }

    public void setUnitSubgroup(int unitSubgroup) {
        this.unitSubgroup = unitSubgroup;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public int getOch() {
        return och;
    }

    public void setOch(int och) {
        this.och = och;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}

package dispatcher.model;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class Station {
    private int id;
    private int codeEnergySystem;
    private String type;
    private int och;
    private String shortNameRus;
    private String shortNameUkr;
    private String fullNameRus;
    private String fullNameUkr;

    public Station() {
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCodeEnergySystem() {
        return codeEnergySystem;
    }

    public void setCodeEnergySystem(int codeEnergySystem) {
        this.codeEnergySystem = codeEnergySystem;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOch() {
        return och;
    }

    public void setOch(int och) {
        this.och = och;
    }

    public String getShortNameRus() {
        return shortNameRus;
    }

    public void setShortNameRus(String shortNameRus) {
        this.shortNameRus = shortNameRus;
    }

    public String getShortNameUkr() {
        return shortNameUkr;
    }

    public void setShortNameUkr(String shortNameUkr) {
        this.shortNameUkr = shortNameUkr;
    }

    public String getFullNameRus() {
        return fullNameRus;
    }

    public void setFullNameRus(String fullNameRus) {
        this.fullNameRus = fullNameRus;
    }

    public String getFullNameUkr() {
        return fullNameUkr;
    }

    public void setFullNameUkr(String fullNameUkr) {
        this.fullNameUkr = fullNameUkr;
    }

}

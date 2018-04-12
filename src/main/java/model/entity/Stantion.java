package model.entity;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class Stantion {
    private int id;
    private int codeEnergySystem;
    private int code;
    private String type;
    private int och;
    private String shortNameRus;
    private String shortNameUkr;
    private String fullNameRus;
    private String fullNameUkr;
    private int stanSqlLink;
    private int stanScLink;

    public Stantion() {
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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
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

    public int getStanSqlLink() {
        return stanSqlLink;
    }

    public void setStanSqlLink(int stanSqlLink) {
        this.stanSqlLink = stanSqlLink;
    }

    public int getStanScLink() {
        return stanScLink;
    }

    public void setStanScLink(int stanScLink) {
        this.stanScLink = stanScLink;
    }
}

package dispatcher.model.entity;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class EnergySystem {
    private int id;
    private int code;
    private String name;

    public EnergySystem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package dispatcher.model.entity;

/**
 * Created by Sovalov.AV on 12.04.2018.
 */
public class IniPassword {
    private String name;
    private String password;
    private char f1;
    private char f2;
    private char f3;
    private char f4;
    private char f5;
    private String oraUser;
    private String oraPassword;

    public IniPassword() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public char getF1() {
        return f1;
    }

    public void setF1(char f1) {
        this.f1 = f1;
    }

    public char getF2() {
        return f2;
    }

    public void setF2(char f2) {
        this.f2 = f2;
    }

    public char getF3() {
        return f3;
    }

    public void setF3(char f3) {
        this.f3 = f3;
    }

    public char getF4() {
        return f4;
    }

    public void setF4(char f4) {
        this.f4 = f4;
    }

    public char getF5() {
        return f5;
    }

    public void setF5(char f5) {
        this.f5 = f5;
    }

    public String getOraUser() {
        return oraUser;
    }

    public void setOraUser(String oraUser) {
        this.oraUser = oraUser;
    }

    public String getOraPassword() {
        return oraPassword;
    }

    public void setOraPassword(String oraPassword) {
        this.oraPassword = oraPassword;
    }
}

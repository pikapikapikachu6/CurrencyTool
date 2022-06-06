package model.currency;

/**
 * Currencies class used to show each currency code and name
 */
public class Currencies {
    public String code;
    public String name;

    /**
     * Used to: show the currency in the table list
     * @param code currency code
     * @param name currency name
     */
    public Currencies(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package model.conversion;

/**
 * An interface includes all the converse
 */
public interface Converse {
    public String latest(String from, String to);

    public String conversion(String from, String to, String amount, Boolean cache);

    public String checkData(String from, String to);

    public String getData(String from, String to);

    public Boolean save(String from, String to, String current);
}

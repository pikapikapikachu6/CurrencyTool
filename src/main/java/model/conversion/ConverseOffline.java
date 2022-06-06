package model.conversion;

/**
 * offline conversion type
 * implements Converse interface
 */
public class ConverseOffline implements Converse{

    /**
     * Used to: Get exchange rate
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @return the current rate obtained from API
     */
    @Override
    public String latest(String from, String to) {
        if (from.equals("CNY")) {
            if (to.equals("AUD")) return "CNY to AUD rate is: 0.21322631";
            if (to.equals("USD")) return "CNY to USD rate is: 0.15132848";
            if (to.equals("CAD")) return "CNY to CAD rate is: 0.19519691";
        }
        if (from.equals("AUD")) {
            if (to.equals("CNY")) return "AUD to CNY rate is: 4.65268768";
            if (to.equals("USD")) return "AUD to USD rate is: 0.70408414";
            if (to.equals("CAD")) return "AUD to CAD rate is: 0.90819024";
        }
        if (from.equals("USD")) {
            if (to.equals("AUD")) return "USD to AUD rate is: 1.42028479";
            if (to.equals("CNY")) return "USD to CNY rate is: 6.60814157";
            if (to.equals("CAD")) return "USD to CAD rate is: 1.28988879";
        }
        if (from.equals("CAD")) {
            if (to.equals("AUD")) return "CAD to AUD rate is: 1.10109089";
            if (to.equals("USD")) return "CAD to USD rate is: 0.77526063";
            if (to.equals("CNY")) return "CAD to CNY rate is: 5.12303201";
        }
        return null;
    }

    /**
     * Used to: Get the conversion result from one currency to another
     * @param from the currency want to transfer from
     * @param to the currency want to transfer to
     * @param amount the amount value want to converse
     * @param cache choose use cache data or not (true: use from cache; false: use from API)
     * @return the conversion result value
     */
    @Override
    public String conversion(String from, String to, String amount, Boolean cache) {
        String result;
        String rates = latest(from, to);
        rates = rates.substring(20);
        Double rate = Double.valueOf(rates);

        Double amounts = Double.valueOf(amount);
        result = String.valueOf(amounts * rate);
        return result;
    }

    @Override
    public String checkData(String from, String to) {
        return null;
    }

    @Override
    public String getData(String from, String to) {
        return null;
    }

    @Override
    public Boolean save(String from, String to, String current) {
        return null;
    }
}

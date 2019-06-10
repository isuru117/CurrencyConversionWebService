package isuru117.conversionwebservice;
public class Currency{
    String id;
    String value;

    public Currency(String i, String CurrencyName) {
        super();
        this.id = i;
        this.value = CurrencyName;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}

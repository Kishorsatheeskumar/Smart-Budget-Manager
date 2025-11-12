package l_t_project;

public class IncomeTransaction {
    private String source;
    private double amount;
    private String date; // yyyy-mm-dd

    public IncomeTransaction(String source, double amount, String date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
    }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
}

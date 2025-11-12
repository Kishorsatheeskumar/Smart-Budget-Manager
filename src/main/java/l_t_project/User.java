package l_t_project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private UserDetails details;

    private final List<IncomeTransaction> incomes = new ArrayList<>();
    private final List<ExpenseTransaction> expenses = new ArrayList<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // --- Auth / identity ---
    public String getUsername() { return username; }
    public String getPassword() { return password; } // demo only

    // --- Profile ---
    public UserDetails getDetails() { return details; }
    public void setDetails(UserDetails details) { this.details = details; }

    // --- Transactions ---
    public void addIncome(IncomeTransaction tx) {
        if (tx != null) incomes.add(tx);
    }

    public void addExpense(ExpenseTransaction tx) {
        if (tx != null) expenses.add(tx);
    }

    public List<IncomeTransaction> getIncomes() {
        return Collections.unmodifiableList(incomes);
    }

    public List<ExpenseTransaction> getExpenses() {
        return Collections.unmodifiableList(expenses);
    }

    // --- Summary helpers ---
    public double getTotalIncome() {
        return incomes.stream().mapToDouble(IncomeTransaction::getAmount).sum();
    }

    public double getTotalExpense() {
        return expenses.stream().mapToDouble(ExpenseTransaction::getAmount).sum();
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }
}

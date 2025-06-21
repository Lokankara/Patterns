package patterns.logic;

import lombok.Getter;

@Getter
public class Account {
    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }
    public synchronized boolean withdraw(double amount){
        if (amount> 0 && balance >= amount){
            balance -= amount;
            return true;
        }
        return false;
    }
}

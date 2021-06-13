package example;

import java.io.Serializable;

public class PaymentDetails implements Serializable {

    private String debtorOrCreditor;
    private Integer amount;
    private String depositNumber;

    public String getDebtorOrCreditor() {
        return debtorOrCreditor;
    }

    public void setDebtorOrCreditor(String debtorOrCreditor) {
        this.debtorOrCreditor = debtorOrCreditor;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }
}

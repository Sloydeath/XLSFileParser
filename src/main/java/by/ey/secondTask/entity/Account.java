package by.ey.secondTask.entity;

import javax.persistence.*;

@Entity(name = "Account")
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    private int id;

    @Column(name = "input_balance_asset", nullable = false)
    private double inputBalanceAsset;

    @Column(name = "input_balance_liability", nullable = false)
    private double inputBalanceLiability;

    @Column(name = "outgoing_balance_asset", nullable = false)
    private double outgoingBalanceAsset;

    @Column(name = "outgoing_balance_liability", nullable = false)
    private double getOutgoingBalanceLiability;

    @Column(name = "debit", nullable = false)
    private double debit;

    @Column(name = "credit", nullable = false)
    private double credit;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "class_fk", nullable = false)
    private Class accountClass;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accounts_group_fk", nullable = false)
    private AccountGroup accountGroup;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_fk", nullable = false)
    private File file;

    public Account() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getInputBalanceAsset() {
        return inputBalanceAsset;
    }

    public void setInputBalanceAsset(double inputBalanceAsset) {
        this.inputBalanceAsset = inputBalanceAsset;
    }

    public double getInputBalanceLiability() {
        return inputBalanceLiability;
    }

    public void setInputBalanceLiability(double inputBalanceLiability) {
        this.inputBalanceLiability = inputBalanceLiability;
    }

    public double getOutgoingBalanceAsset() {
        return outgoingBalanceAsset;
    }

    public void setOutgoingBalanceAsset(double outgoingBalanceAsset) {
        this.outgoingBalanceAsset = outgoingBalanceAsset;
    }

    public double getGetOutgoingBalanceLiability() {
        return getOutgoingBalanceLiability;
    }

    public void setGetOutgoingBalanceLiability(double getOutgoingBalanceLiability) {
        this.getOutgoingBalanceLiability = getOutgoingBalanceLiability;
    }

    public double getDebit() {
        return debit;
    }

    public void setDebit(double debit) {
        this.debit = debit;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Class getAccountClass() {
        return accountClass;
    }

    public void setAccountClass(Class accountClass) {
        this.accountClass = accountClass;
    }

    public AccountGroup getAccountGroup() {
        return accountGroup;
    }

    public void setAccountGroup(AccountGroup accountGroup) {
        this.accountGroup = accountGroup;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "Account{" +
                ", inputBalanceAsset=" + inputBalanceAsset +
                ", inputBalanceLiability=" + inputBalanceLiability +
                ", outgoingBalanceAsset=" + outgoingBalanceAsset +
                ", getOutgoingBalanceLiability=" + getOutgoingBalanceLiability +
                ", debit=" + debit +
                ", credit=" + credit +
                '}';
    }
}

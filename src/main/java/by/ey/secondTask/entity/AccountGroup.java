package by.ey.secondTask.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "AccountGroup")
@Table(name = "ACCOUNTS_GROUPS")
public class AccountGroup {

    @Id
    @Column(name = "accounts_group_id")
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_fk", nullable = false)
    private File file;

    @OneToMany(mappedBy = "accountGroup", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    public AccountGroup() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Account account) {
        accounts.add(account);
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "AccountGroup{" +
                "id=" + id +
                '}';
    }
}

package by.ey.secondTask.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity(name = "Class")
@Table(name = "classes")
public class Class {

    @Id
    @Column(name = "class_id")
    private int id;

    @Column(name = "class_name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "accountClass", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Account> accounts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "file_fk", nullable = false)
    private File file;

    public Class(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Class() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Class{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return id == aClass.id &&
                name.equals(aClass.name) &&
                accounts.equals(aClass.accounts) &&
                file.equals(aClass.file);
    }
}

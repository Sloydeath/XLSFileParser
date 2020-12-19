package by.ey.secondTask.entity;

import javax.persistence.*;

@Entity(name = "FileDescription")
@Table(name = "FILE_DESCRIPTION")
public class FileDescription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_description_id")
    private int id;

    @Column(name = "BANK_NAME", nullable = false)
    private String bankName;

    @Column(name = "PERIOD", nullable = false)
    private String period;

    @OneToOne(mappedBy = "description")
    private File file;

    public FileDescription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "FileDescription{" +
                "id=" + id +
                ", bankName='" + bankName + '\'' +
                ", period='" + period + '\'' +
                '}';
    }
}

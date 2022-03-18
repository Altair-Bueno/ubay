package uma.taw.ubay.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "example", schema = "public")
public class ExampleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "number")
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExampleEntity that = (ExampleEntity) o;

        if (number != that.number) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return number;
    }
}

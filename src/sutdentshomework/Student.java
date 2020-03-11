package sutdentshomework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "")
public class Student {

    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String jmbg;
    @Column
    private String city;

    public Student() {
    }

    public Student(String name, String surname, String jmbg, String city) {
        this.name = name;
        this.surname = surname;
        this.jmbg = jmbg;
        this.city = city;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "(" + id + ") " + name + " " + surname + " - " + jmbg + " " + city;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        } else if (obj instanceof Student) {
            Student temp = (Student) obj;
            return temp.id == this.id;
        } else {
            return false;
        }

    }
    
    public String listaId(){
    
        return name + " " + "["+id+"]";
        
        
    }

}

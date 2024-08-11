package models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "alumnos")
public class Alumn implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "alumno_id")
    private int alumnId;
    
    @Column(name = "codigo")
    private String code;
    
    @Column(name = "nombre")
    private String name;
    
    @Column(name = "sexo")
    private char gener;
    
    @Column(name = "edad")
    private int age;

    public Alumn(int alumnId, String code, String name, char gener, int age) {
        this.alumnId = alumnId;
        this.code = code;
        this.name = name;
        this.gener = gener;
        this.age = age;
    }

    public Alumn(String code, String name, char gener, int age) {
        this.code = code;
        this.name = name;
        this.gener = gener;
        this.age = age;
    }

    public Alumn() {
    }

    public int getAlumnId() {
        return alumnId;
    }

    public void setAlumnId(int alumnId) {
        this.alumnId = alumnId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGener() {
        return gener;
    }

    public void setGener(char gener) {
        this.gener = gener;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Alumno{" + "codigo=" + code + ", nombre=" + name + ", sexo=" + gener + ", edad=" + age + '}';
    }
}

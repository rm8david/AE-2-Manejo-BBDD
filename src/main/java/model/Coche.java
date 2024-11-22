package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coche {
    private int id;
    private String marca;
    private String modelo;
    private int cv;
    private double precio;
    private String matricula;

    public Coche(String marca, String modelo, int cv, double precio, String matricula) {
        this.marca = marca;
        this.modelo = modelo;
        this.cv = cv;
        this.precio = precio;
        this.matricula = matricula;
    }
}

package model;

import lombok.*;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pasajero {
    private int id;
    private String nombre;
    private int edad;
    private int peso;
    private int cocheId;

    public Pasajero(String nombre, int edad, int peso) {
        this.nombre = nombre;
        this.edad = edad;
        this.peso = peso;
    }


}

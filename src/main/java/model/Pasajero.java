package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Override
    public String toString() {
        return "Pasajero{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", peso=" + peso +
                ", cocheId=" + cocheId +
                '}';
    }
}

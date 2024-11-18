package model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Coche {
    private String marca;
    private String modelo;
    private int cv;
    private double precio;
    private String matricula;
}

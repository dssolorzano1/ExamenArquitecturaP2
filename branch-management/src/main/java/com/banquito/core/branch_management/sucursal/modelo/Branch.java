package com.banquito.core.branch_management.sucursal.modelo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SUCURSAL")
public class Branch implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sucursal_seq")
    @SequenceGenerator(name = "sucursal_seq", sequenceName = "sucursal_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @NotNull
    @Column(name = "NOMBRE", length = 100)
    private String name;

    @NotNull
    @Column(name = "EMAIL", length = 100)
    private String emailAddress;

    @NotNull
    @Column(name = "TELEFONO", length = 15)
    private String phoneNumber;

    @NotNull
    @Column(name = "ESTADO", length = 10)
    private String state;

    @Column(name = "FECHA_CREACION")
    private LocalDateTime fechaCreacion;

    @Column(name = "FECHA_ACTUALIZACION")
    private LocalDateTime fechaActualizacion;

    @Column(name = "FECHA_ULTIMA_MODIFICACION")
    private LocalDateTime lastModifiedDate;

    public Branch(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Branch branch = (Branch) o;
        return Objects.equals(id, branch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Branch{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", state='" + state + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", lastModifiedDate=" + lastModifiedDate +
                '}';
    }
}

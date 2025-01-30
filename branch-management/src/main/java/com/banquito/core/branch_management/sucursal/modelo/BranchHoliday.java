package com.banquito.core.branch_management.sucursal.modelo;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@Entity
@Table(name = "SUCURSAL_FERIADO")
public class BranchHoliday implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "feriado_seq")
    @SequenceGenerator(name = "feriado_seq", sequenceName = "feriado_id_seq", allocationSize = 1)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "SUCURSAL_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    @Column(name = "FECHA", nullable = false)
    private LocalDate date;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String name;

    public BranchHoliday(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BranchHoliday holiday = (BranchHoliday) o;
        return Objects.equals(id, holiday.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

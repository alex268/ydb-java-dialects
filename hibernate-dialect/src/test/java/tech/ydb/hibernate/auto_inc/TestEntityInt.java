package tech.ydb.hibernate.auto_inc;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Kirill Kurdyukov
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "test_table_int_auto_inc")
public class TestEntityInt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String text;
}

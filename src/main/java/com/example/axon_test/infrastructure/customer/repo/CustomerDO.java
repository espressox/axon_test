package com.example.axon_test.infrastructure.customer.repo;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;
import java.util.Objects;

/**
 * @author xin
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity(name = "CUSTOMER")
public class CustomerDO {
    @Id
    private Long id;

    @Version
    private Long version;

    private String customerId;
    private String memberId;
    private String globalId;
    private Long registeredCapital;
    private String companyName;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CustomerDO that = (CustomerDO) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}

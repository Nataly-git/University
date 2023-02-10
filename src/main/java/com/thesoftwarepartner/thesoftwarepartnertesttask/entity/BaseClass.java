package com.thesoftwarepartner.thesoftwarepartnertesttask.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@MappedSuperclass
@Getter
@Setter
public abstract class BaseClass {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BaseClass baseClass = (BaseClass) o;
        return id != null && Objects.equals(id, baseClass.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

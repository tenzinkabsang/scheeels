package com.kabz.scheeels.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;

@MappedSuperclass
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(insertable = false, updatable = false)
    @CreatedDate
    private Instant createdDate;

    @Column(insertable = false)
    @LastModifiedDate
    private Instant modifiedDate;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;

        if(this.id == null || obj == null || !(this.getClass().equals(obj.getClass())))
            return false;

        BaseEntity that = (BaseEntity) obj;
        return this.id.equals(that.id);
    }

    @Override
    public int hashCode() { return id == null ? 0 : id.hashCode(); }
}

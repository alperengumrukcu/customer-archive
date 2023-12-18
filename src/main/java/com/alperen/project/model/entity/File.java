package com.alperen.project.model.entity;

import com.alperen.project.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity(name = "files")
public class File extends BaseEntity {
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "path",nullable = false)
    private String path;
    @Column(name = "customer_id",nullable = false)
    private Long customerId;
    @Column(name = "content_type",nullable = false)
    private String contentType;
    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id",nullable = false,insertable = false,updatable = false)
    private Customer customer;
}

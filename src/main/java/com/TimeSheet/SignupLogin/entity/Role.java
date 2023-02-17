package com.TimeSheet.SignupLogin.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Role {

    @Id
    private UUID role_id;
    private String name;
    private UUID permission;
    private Timestamp created_at;
    private Timestamp updated_at;
}

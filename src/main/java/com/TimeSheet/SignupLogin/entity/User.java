package com.TimeSheet.SignupLogin.entity;

import com.TimeSheet.SignupLogin.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.rmi.server.UID;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="userDetails")
public class User {

    @Id
    @GenericGenerator(name = "UUIDGenerator", strategy = "uuid2")
    @GeneratedValue(generator = "UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID userId;
    private String userName;
    private String password;
    private String firstname;
    private String lastname;
    private String email;
    private String number;
    private UserType usertype;
    @OneToOne(cascade = CascadeType.ALL)
    private Role role;
    private String birthday;
    private Timestamp created_at;
    private Timestamp updated_at;



}

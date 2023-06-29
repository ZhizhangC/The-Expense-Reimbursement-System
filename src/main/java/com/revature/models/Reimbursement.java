package com.revature.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "reimbursements")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reimbursement {
    @Id
    @Column(name = "reimb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "amount", nullable = false)
    private int amount;

    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id_fk")
    private MyUser user_id;

    @ManyToOne
    @JoinColumn(name = "status_id_fk")
    private Status status_id;


}




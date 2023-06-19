package com.revature.models;
import javax.persistence.*;

@Entity
@Table(name = "reimbursements")
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
    private User user_id;

    @ManyToOne
    @JoinColumn(name = "status_id_fk")
    private Status status_id;

    public Reimbursement() {
    }

    public Reimbursement(int id, int amount, String description, User user_id, Status status_id) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.user_id = user_id;
        this.status_id = status_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser_id() {
        return user_id;
    }

    public void setUser_id(User user_id) {
        this.user_id = user_id;
    }

    public Status getStatus_id() {
        return status_id;
    }

    public void setStatus_id(Status status_id) {
        this.status_id = status_id;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", user_id=" + user_id +
                ", status_id=" + status_id +
                '}';
    }
}




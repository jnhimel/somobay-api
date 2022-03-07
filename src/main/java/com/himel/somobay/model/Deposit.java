package com.himel.somobay.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double depositAmount;
    private Double interestRate;
    private Integer period;
    private Integer duration;
    @Transient
    private Double targetAmount;
    @Transient
    private Double totalAmount;
    private LocalDate startDate;
    @Column(columnDefinition = "boolean default false")
    private boolean approved;
    @Column(columnDefinition = "boolean default false")
    private boolean active;
    @Transient
    private boolean delayed;
    @Transient
    private boolean complete;
    @Transient
    private LocalDate endDate;
    @OneToMany(mappedBy = "deposit")
    @JsonIgnore
    private List<DepositTransaction> transactions;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Double getTargetAmount() {
        return (duration / period) * depositAmount;
    }

    public Double getTotalAmount() {
        if(transactions != null){
            return transactions.stream()
                    .filter(DepositTransaction::getApproved)
                    .map(DepositTransaction::getAmount).
                    reduce(0.0,Double::sum);
        } else {
            return 0.0;
        }

    }

    public LocalDate getEndDate() {
        return startDate.plusDays(duration);
    }

    public boolean isDelayed() {
        double v = (startDate.until(LocalDate.now(), ChronoUnit.DAYS) / period) * depositAmount;
        return v > getTotalAmount();
    }

    public boolean isComplete() {
        return getTotalAmount() >= getTargetAmount();
    }
}

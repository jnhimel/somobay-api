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
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double loanAmount;
    private Double interestRate;
    @Transient
    private Double installmentAmount;
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
    @OneToMany(mappedBy = "loan")
    @JsonIgnore
    private List<Installment> installments;
    @ManyToOne
    @JsonIgnore
    private User user;

    public Double getTargetAmount() {
        return loanAmount + ((loanAmount * interestRate / 100) * (duration / 365)) ;
    }

    public Double getInstallmentAmount() {
        return Math.ceil(getTargetAmount() / (duration / period));
    }

    public Double getTotalAmount() {
        if(installments != null){
            return installments.stream()
                    .filter(Installment::getApproved)
                    .map(Installment::getAmount).
                    reduce(0.0,Double::sum);
        } else {
            return 0.0;
        }

    }

    public LocalDate getEndDate() {
        return startDate.plusDays(duration);
    }

    public boolean isDelayed() {
        double v = (startDate.until(LocalDate.now(), ChronoUnit.DAYS) / period) * getInstallmentAmount();
        return v > getTotalAmount();
    }

    public boolean isComplete() {
        return getTotalAmount() >= getTargetAmount();
    }
}

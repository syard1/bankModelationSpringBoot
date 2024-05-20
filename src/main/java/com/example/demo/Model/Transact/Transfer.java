package com.example.demo.Model.Transact;

import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Transfer  extends Transaction{
    
    private int idRealiseTransation;
}

package com.ditra.travelagency.core.type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Type {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)

    private int id;

}


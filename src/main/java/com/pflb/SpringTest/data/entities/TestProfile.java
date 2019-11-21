package com.pflb.SpringTest.data.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class TestProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToMany(mappedBy = "testProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;

}

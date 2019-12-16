package com.pflb.springtest.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "test_profile")
public class TestProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @OneToMany(mappedBy = "testProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;

}

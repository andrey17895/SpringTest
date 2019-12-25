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
@SequenceGenerator(name = "test_profile_id_seq", sequenceName = "test_profile_id_seq", allocationSize = 1)
public class TestProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_profile_id_seq")
    private Long id;

    @NonNull
    @OneToMany(mappedBy = "testProfile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Request> requests;

}

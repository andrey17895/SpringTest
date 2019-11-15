package com.pflb.SpringTest.data.entities;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
//@Entity
public class TestProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    @ElementCollection
//    @CollectionTable(name = "test_profile_requests", joinColumns = @JoinColumn(name = "test_profile_id"))
    @OneToMany(mappedBy = "test_profile")
    private List<Request> requests;

}

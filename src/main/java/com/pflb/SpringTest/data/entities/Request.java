package com.pflb.SpringTest.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    private String body;

//    @JsonDeserialize(using = ListToMapDeserializer.class)
//    @ElementCollection
//    @CollectionTable(name = "order_item_mapping",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "item_name")
//    @Column(name = "price")
//    private Map<String, String> headers;

//    @JsonDeserialize(using = ListToMapDeserializer.class)
//    private Map<String, String> params;

    private HttpMethod method;
    private Double perc;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_profile_id", nullable = false)
    @JsonIgnore
    private TestProfile testProfile;
}

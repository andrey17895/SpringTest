package com.pflb.SpringTest.data.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.pflb.SpringTest.parser.deserializers.ListToMapDeserializer;
import lombok.Data;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Map;

@Data
//@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String url;
    private String body;

    @JsonDeserialize(using = ListToMapDeserializer.class)
//    @ElementCollection
//    @CollectionTable(name = "order_item_mapping",
//            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
//    @MapKeyColumn(name = "item_name")
//    @Column(name = "price")
    private Map<String, String> headers;

    @JsonDeserialize(using = ListToMapDeserializer.class)
    private Map<String, String> params;

    private HttpMethod method;
    private Double perc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_profile_id")
    private TestProfile testProfile;
}

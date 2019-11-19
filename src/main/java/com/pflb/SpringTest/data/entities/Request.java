package com.pflb.SpringTest.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@RequiredArgsConstructor
@ToString(exclude = {"testProfile"})
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

//    @JsonAlias({"postData"})
//    @JsonDeserialize(using = CustomBodyDeserializer.class)
    private String body;

//    @JsonDeserialize(using = ListToMapDeserializer.class)
    @ElementCollection
    @CollectionTable(name = "request_headers",
            joinColumns = {@JoinColumn(name = "request_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> headers;

//    @JsonAlias({"postData"})
//    @JsonDeserialize(using = CustomParamsDeserialiser.class)
    @ElementCollection
    @CollectionTable(name = "request_params",
            joinColumns = {@JoinColumn(name = "request_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> params;

    private HttpMethod method;

    private Double perc = 0D;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_profile_id", nullable = false)
    @JsonIgnore
    private TestProfile testProfile;

}


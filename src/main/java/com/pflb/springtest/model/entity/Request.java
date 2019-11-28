package com.pflb.springtest.model.entity;

import lombok.*;
import org.springframework.http.HttpMethod;

import javax.persistence.*;
import java.util.Map;

@Data
@Entity
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"testProfile"})
@Table(name = "request")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private String body;

    @ElementCollection
    @CollectionTable(name = "request_headers",
            joinColumns = {@JoinColumn(name = "request_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "name")
    @Column(name = "value")
    private Map<String, String> headers;

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
    @EqualsAndHashCode.Exclude
    private TestProfile testProfile;

}


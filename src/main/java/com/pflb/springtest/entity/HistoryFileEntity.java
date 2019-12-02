package com.pflb.springtest.entity;

import com.pflb.springtest.dto.HarDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "history_file")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class HistoryFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Type(type = "jsonb")
    private HarDto content;

    private String browser;

    private String version;

    @NonNull
    private Date uploadTime;
}

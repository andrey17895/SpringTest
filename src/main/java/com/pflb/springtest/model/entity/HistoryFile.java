package com.pflb.springtest.model.entity;

import com.pflb.springtest.model.dto.har.HarDto;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@RequiredArgsConstructor
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "history_file")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@SequenceGenerator(name = "history_file_id_seq", sequenceName = "history_file_id_seq", allocationSize = 1)
public class HistoryFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_file_id_seq")
    private Long id;

    @NonNull
    private String name;

    @NonNull
    @Type(type = "jsonb")
    private HarDto content;

    private String browser;

    private String version;

    @NonNull
    private LocalDateTime uploadTime;
}

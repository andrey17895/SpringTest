package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.provider.HarDtoProvider;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertNotNull;

//import com.pflb.springtest.provider.TestGenerator;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@Disabled
class HistoryFileRepositoryTest {
    @Test
    void save() {
        HistoryFile historyFile = HistoryFile.builder()
                .id(null)
                .name("Name")
                .content(HarDtoProvider.dtoFromFile("/har/har_valid_minimal.json"))
                .browser("browser")
                .version("1.2")
                .uploadTime(LocalDateTime.now())
                .build();
        HistoryFile response = repository.save(historyFile);
        assertNotNull(response.getId());
    }


    @Autowired
    private HistoryFileRepository repository;

}
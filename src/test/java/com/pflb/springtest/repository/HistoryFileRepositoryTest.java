package com.pflb.springtest.repository;

import com.pflb.springtest.generator.TestGenerator;
import com.pflb.springtest.model.entity.HistoryFile;
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
                .content(TestGenerator.harDto("ya.ru", null))
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
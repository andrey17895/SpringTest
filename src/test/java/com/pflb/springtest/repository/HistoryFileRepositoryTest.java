package com.pflb.springtest.repository;

import com.pflb.springtest.model.entity.HistoryFile;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= Replace.NONE)
@ActiveProfiles("test")
@Transactional
class HistoryFileRepositoryTest {
    @Autowired
    private HistoryFileRepository repository;

    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HistoryFileArgs#save")
    @Rollback
    void save(HistoryFile historyFile) throws IOException {

        HistoryFile actualResponse = repository.save(historyFile);

        assertNotNull(actualResponse.getId());
    }

}
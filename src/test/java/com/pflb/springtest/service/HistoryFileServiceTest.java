package com.pflb.springtest.service;

import com.pflb.springtest.generator.TestGenerator;
import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.repository.HistoryFileRepository;
import com.pflb.springtest.service.impl.HistoryFileService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class HistoryFileServiceTest {

    @Mock
    private HistoryFileRepository fileRepository;

    @Mock
    private ModelMapper mapper;

    @Mock
    private JmsProducer jmsProducer;

    @Mock
    private IParserService harParserService;

    @InjectMocks
    private HistoryFileService historyFileServiceImpl;


    @ParameterizedTest
    @DisplayName("Processing file Ok")
    @MethodSource("com.pflb.springtest.service.provider.HistoryFileArgProvider#processFileOk")
    public void processFileOk(HistoryFileDto historyFileDto, HistoryFile historyFileEntity, MultipartFile multipartFile) throws IOException {
        when(harParserService.parse(eq(multipartFile)))
                .thenReturn(TestGenerator.harDto("ya.ru", null));
        when(fileRepository.save(any(HistoryFile.class)))
                .thenReturn(historyFileEntity);
        when(mapper.map(eq(historyFileEntity), eq(HistoryFileDto.class))).thenReturn(historyFileDto);

        assertEquals(historyFileDto, historyFileServiceImpl.processFile(multipartFile));
        verify(jmsProducer).sendMessage(any(HarDto.class));
    }

    @ParameterizedTest
    @DisplayName("Get all files from repository")
    @MethodSource("com.pflb.springtest.service.provider.HistoryFileArgProvider#getAllFiles")
    public void getAllFiles(Collection<HistoryFileDto> historyFileDtos, Collection<HistoryFile> historyFiles) {
        when(fileRepository.findAll()).thenReturn(historyFiles);
        when(mapper.map(any(), eq(new TypeToken<List<HistoryFileDto>>() {}.getType()))).thenReturn(historyFileDtos);

        assertEquals(historyFileDtos, historyFileServiceImpl.getAllFiles());
    }

    @Test
    @DisplayName("Delete all files in repository")
    public void deleteAllFiles() {
        historyFileServiceImpl.deleteAllFiles();
        verify(fileRepository, times(1)).deleteAll();
    }

//    @NotNull
//    private static Stream<Arguments> streamOfHistoryFileList() {
//        return Stream.of(
//                Arguments.of(
//                        Collections.singletonList(TestGenerator.historyFileDto("ya.ru")),
//                        Collections.singletonList(TestGenerator.historyFileEntity(1L, "ya.ru"))
//                ),
//                Arguments.of(
//                        Arrays.asList(
//                                TestGenerator.historyFileDto("ya.ru"),
//                                TestGenerator.historyFileDto("bumq.io")
//                        ),
//                        Arrays.asList(
//                                TestGenerator.historyFileEntity(1L, "ya.ru"),
//                                TestGenerator.historyFileEntity(2L, "bumq.io")
//                        )
//                ),
//                Arguments.of(
//                        Collections.emptyList(),
//                        Collections.emptyList()
//                )
//        );
//    }
//
//    private static Stream<Arguments> streamOfHistoryFiles() throws IOException {
//        return Stream.of(
//                Arguments.of(
//                        TestGenerator.historyFileDto("ya.ru"),
//                        TestGenerator.historyFileEntity(1L, "ya.ru"),
//                        TestGenerator.multipartFile()
//                )
//        );
//    }

}
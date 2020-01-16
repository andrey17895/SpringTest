package com.pflb.springtest.service;

import com.pflb.springtest.jms.producer.JmsProducer;
import com.pflb.springtest.model.dto.har.HarDto;
import com.pflb.springtest.model.dto.profile.HistoryFileDto;
import com.pflb.springtest.model.entity.HistoryFile;
import com.pflb.springtest.model.exception.ApplicationException;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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
    private HistoryFileService historyFileService;


    @ParameterizedTest
    @MethodSource("com.pflb.springtest.argument.HistoryFileServiceArgs#processFile_thenThrowException")
    public void processFile_thenThrowException_whenInvalidHar(MultipartFile multipartFile,
                                                              HarDto harDto,
                                                              ApplicationException expectedException){

        when(harParserService.parse(eq(multipartFile)))
                .thenReturn(harDto);

        ApplicationException actualException =
                assertThrows(ApplicationException.class, () -> historyFileService.processFile(multipartFile));

        assertEquals(expectedException, actualException);
    }

    @ParameterizedTest
    @DisplayName("Processing file Ok")
    @MethodSource("com.pflb.springtest.argument.HistoryFileServiceArgs#processFile_thenReturnDto")
    public void processFile_thenReturnDto_whenValid(HistoryFileDto historyFileDto,
                                                    HistoryFile historyFileEntity,
                                                    MultipartFile multipartFile,
                                                    HarDto harDto){

        when(harParserService.parse(eq(multipartFile)))
                .thenReturn(harDto);
        when(fileRepository.save(any(HistoryFile.class)))
                .thenReturn(historyFileEntity);
        when(mapper.map(eq(historyFileEntity), eq(HistoryFileDto.class))).thenReturn(historyFileDto);

        HistoryFileDto actualDto = historyFileService.processFile(multipartFile);

        assertEquals(historyFileDto, actualDto);
        verify(jmsProducer).sendMessage(any(HarDto.class));
    }

    @ParameterizedTest
    @DisplayName("Get all files from repository")
    @MethodSource("com.pflb.springtest.argument.HistoryFileServiceArgs#getAllFiles_thenReturnDtoList")
    public void getAllFiles_thenReturnDtoList(List<HistoryFileDto> historyFileDtos, List<HistoryFile> historyFiles) {

        when(fileRepository.findAll()).thenReturn(historyFiles);
        when(mapper.map(any(), eq(new TypeToken<List<HistoryFileDto>>() {}.getType()))).thenReturn(historyFileDtos);

        List<HistoryFileDto> actualDtoList = historyFileService.getAllFiles();
        assertEquals(historyFileDtos, actualDtoList);
    }

    @Test
    @DisplayName("Delete all files in repository")
    public void deleteAllFiles() {

        doNothing().when(fileRepository).deleteAll();

        historyFileService.deleteAllFiles();

        verify(fileRepository, times(1)).deleteAll();
    }
}
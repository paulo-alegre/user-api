package com.cognizant.sg.userapi;

import com.cognizant.sg.userapi.entity.User;
import com.cognizant.sg.userapi.repository.UserRepository;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Reads csv data and save in the database
 *
 * @author Adrian Alegre
 */
@Component
@Slf4j
public class CsvReaderApplicationRunner implements ApplicationRunner {

    private final UserRepository userRepository;

    /**
     * Constructor Injection
     * @param userRepository
     */
    CsvReaderApplicationRunner(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Value("${users-file.path}")
    private String users_file_path;

    /**
     * {@link UserRepository} that saves all the from csv.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("CSV Reader");
        userRepository.saveAll(convertCsvToEntity(users_file_path, User.class));
    }

    /**
     * Read the csv file and convert into list of strings
     *
     * @param fileName
     * @param type
     * @param <T>
     * @return List<T>
     */
    private <T> List<T> convertCsvToEntity(final String fileName, Class<T> type) {
        try {
            CsvMapper mapper = new CsvMapper();
            File file = new ClassPathResource(fileName).getFile();
            MappingIterator<T> readValues =
                    mapper.readerFor(type).with(CsvSchema.emptySchema().withHeader()).readValues(file);
            return readValues.readAll();
        } catch (Exception e) {
            log.error("Error occurred while loading object list from file " + fileName, e);
            return Collections.emptyList();
        }
    }
}

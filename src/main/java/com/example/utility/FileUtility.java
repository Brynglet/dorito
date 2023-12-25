package com.example.utility;

import com.example.exception.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.example.utility.Common.DORITO_ROOT;

public class FileUtility {

    public static List<File> getWalkAllPgnFiles() {

        List<File> allDoritoFiles = new ArrayList<>();

        try (Stream<Path> walkStream = Files.walk(Paths.get(DORITO_ROOT))) {
            walkStream.filter(p -> p.toFile().isFile()).forEach(f -> {
                allDoritoFiles.add(f.toFile());
            });
        } catch (Exception e) {
            throw new ApiError(HttpStatusCode.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()), e.getMessage());
        }

        return allDoritoFiles;
    }

}

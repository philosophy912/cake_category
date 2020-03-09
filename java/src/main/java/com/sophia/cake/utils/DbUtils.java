package com.sophia.cake.utils;

import com.philosophy.base.util.FilesUtils;
import lombok.SneakyThrows;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author lizhe
 * @date 2020-03-08 22:19
 */
public class DbUtils {

    private static final String FOLDER_NAME = "db";

    @SneakyThrows
    public Path getPaths(String name) {
        Path folder = Paths.get(FilesUtils.getCurrentPath() + File.separator + FOLDER_NAME);
        if(!Files.exists(folder)){
            Files.createDirectories(folder);
        }
        Path path = Paths.get(FilesUtils.getCurrentPath() + File.separator + FOLDER_NAME + File.separator + name);
        if(!Files.exists(path)){
            Files.createFile(path);
        }
        return path;
    }
}

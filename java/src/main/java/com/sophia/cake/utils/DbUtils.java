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

    @SneakyThrows
    public Path getPaths(String name){
        ClassPathResource classPathResource = new ClassPathResource(name);
        Path path;
        try{
            path = classPathResource.getFile().toPath();
        }catch (IOException e){
            path = Paths.get(FilesUtils.getCurrentPath() + File.separator + name);
            Files.createFile(path);
        }
        return path;
    }
}

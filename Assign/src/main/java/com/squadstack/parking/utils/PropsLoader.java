package com.squadstack.parking.utils;

import lombok.Getter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

@Getter
public final class PropsLoader {

    private final Properties properties;
    public static PropsLoader INSTANCE;

    private PropsLoader() throws IOException {
       properties = loadPropsValue();
    }

    private Properties loadPropsValue() throws IOException {
        Properties prop = new Properties();
        String propFileName = "command.properties";
        InputStream fis = null;
        try {
            fis = getClass().getClassLoader().getResourceAsStream(propFileName);
            prop = new Properties();
            prop.load(fis);
        } catch(FileNotFoundException exception) {
            exception.printStackTrace();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } finally {
            fis.close();
        }
        return prop;
    }

    public static PropsLoader getInstance() throws IOException {
        if (Objects.nonNull(INSTANCE)) {
            return INSTANCE;
        }
        INSTANCE = new PropsLoader();
        return INSTANCE;
    }

    public boolean checkOptionalParam(String token) throws IllegalAccessException {
        if(Objects.isNull(properties)) {
            throw new IllegalAccessException("File is not loaded ..");
        }
        boolean res = false;
        String value = properties.getProperty(Constants.OPTIONAL);
        if(value.contains(token)){
            res = true;
        }
        return res;
    }

    public boolean isValidCommand(String token) throws IOException, IllegalAccessException {
        if(Objects.isNull(properties)) {
            throw new IllegalAccessException("File is not loaded ..");
        }
        boolean res = false;
        String value = properties.getProperty(Constants.COMMAND);
        if(value.contains(token)){
            res = true;
        }
        return res;
    }

}

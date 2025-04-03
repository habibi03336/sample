package com.hollysgang.sample.encryption.framework.core;

import java.util.function.Function;

public interface PersonalInformationProcessor {
    String encryptPersonalInformation(String type, String plain);

    String decryptPersonalInformation(String type, String encrypted);

    void encryptPersonalInformation(Object obj);

    void decryptPersonalInformation(Object obj);

    void setEncFunc(String type, Function<String, String> func);
    void setDecFunc(String type, Function<String, String> func);

}
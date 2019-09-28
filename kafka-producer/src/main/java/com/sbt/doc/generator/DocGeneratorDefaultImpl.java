package com.sbt.doc.generator;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocGeneratorDefaultImpl implements DocGenerator {

    @Override
    public String generateDoc() {
        return "TEST";
    }
}
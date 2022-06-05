package com.example.geektrust.commandLineInputs.executors;

import com.example.geektrust.domain.inputlayer.DomainMapper;

@FunctionalInterface
public interface CommandExecutor {
    public String execute(DomainMapper mapper);
}

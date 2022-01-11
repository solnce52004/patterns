package com.example.patterns.singleton;

import com.example.patterns.singleton.canonical.NamedService;


public class ModernSingletonClient implements NamedService {
    private final ModernServiceRegistry registry;

    public ModernSingletonClient(ModernServiceRegistry registry) {
        this.registry = registry;
    }

    public static void main(String[] args) {
        ModernServiceRegistry registry = new ModernServiceRegistry();
        new ModernSingletonClient(registry).start();
    }

    public void start() {
        registry.register(this);
    }

    @Override
    public String getName() {
        return "MAIN";
    }
}

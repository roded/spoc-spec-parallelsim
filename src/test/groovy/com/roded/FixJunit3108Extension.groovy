package com.roded

import org.spockframework.runtime.extension.IGlobalExtension
import org.spockframework.runtime.model.SpecInfo
import spock.config.RunnerConfiguration

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FixJunit3108Extension implements IGlobalExtension {
    private static ExecutorService threadPool

    private final RunnerConfiguration runnerConfiguration

    FixJunit3108Extension(RunnerConfiguration runnerConfiguration) {
        this.runnerConfiguration = runnerConfiguration
    }

    @Override
    void start() {
        threadPool = Executors.newFixedThreadPool(runnerConfiguration.parallel.parallelExecutionConfiguration.parallelism)
    }

    @Override
    void visitSpec(SpecInfo spec) {
        spec.addInterceptor {
            threadPool.submit(it::proceed).get()
        }
    }

    @Override
    void stop() {
        threadPool?.shutdown()
    }
}

package com.greglturnquist.hackingspringboot.reactive.blockhoundtest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.assertj.core.api.Assertions.*;

public class BlockHoundTest {

//    @Test
//    void threadSleepIsABlockingCall() {
//        Mono.delay(Duration.ofSeconds(1))
//                .flatMap(tick -> {
//                    try {
//                        Thread.sleep(10);
//                        return Mono.just(true);
//                    } catch (InterruptedException e) {
//                        return Mono.error(e);
//                    }
//                })
//                .as(StepVerifier::create)
//                .verifyErrorMatches(throwable -> {
//                    assertThat(throwable.getMessage())
//                            .contains("Blocking call! java.lang.Thread.sleep");
//                    return true;
//                });
//    }

}

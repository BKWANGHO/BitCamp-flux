package com.bitclass.webflux;

import com.bitclass.webflux.common.config.CustomException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FluxAndMonoTest {

    @Mock
    CustomException customExceptionMono;
    @Mock
    CustomException customExceptionFlux;

    @BeforeEach
    void setup(){
        customExceptionFlux = new CustomException("Flux");
        customExceptionMono = new CustomException("Mono");
    }

    @Test @DisplayName("Flux just() sample")
    void fluxTest(){
        List<String> names = new ArrayList<>();
        Flux<String> flux = Flux.just("김구","윤봉길","유관순").log();
        flux.subscribe(names::add);
        assertThat(names).isEqualTo(Arrays.asList("김구","윤봉길","유관순"));
    }


    @Test @DisplayName("Mono just() sample")
    void monoTest(){
//        Reactive Stream 에서는 Data, Event, Signal 중에서 Signal을 사용한다.
//        onNext, onComplete, onError
        List<Signal<Integer>> list = new ArrayList<>(4);

        final Integer[] result = new Integer[1];

        Mono<Integer> mono = Mono.just(1).log()
                .doOnEach(i->{
                    list.add(i);
            System.out.println("Signal: "+i);
                });

        mono.subscribe(i->result[0] = i );

//        리스트의 사이즈는
        assertThat(list.size()).isEqualTo(2);
    }


    @Test @DisplayName("Flux just() sample")
    void FluxCreateTest(){
        Flux<Integer> flux = Flux.create((FluxSink<Integer> sink)->{
            sink.onRequest(request->{
                for(int i =0; i<request+3; i++){
                    sink.next(i);
                }
                sink.complete();
            });
        });

        flux.subscribe(System.out::println);
        assertThat("1").isEqualTo("1");
    }

    @Test @DisplayName("Flux just() sample")
    void FluxGeneratortTest(){
        Flux<String> flux = Flux.generate(()->{
            return 0;},
        (state,sink)-> {
            sink.next("3 * " +state + " = " +state*3);
            if (state ==10){
                sink.complete();
            }
            return state +1;
        });

        flux.subscribe(System.out::println);

        assertThat("1").isEqualTo("1");
    }

}


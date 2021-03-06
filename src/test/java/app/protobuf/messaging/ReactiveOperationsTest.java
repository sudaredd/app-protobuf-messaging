package app.protobuf.messaging;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.function.Consumer;

public class ReactiveOperationsTest {

    String s = "hello";
    @Test
    public void testMono() {
        Mono<String> hello = Mono.just(s)
                .map(String::toUpperCase);
        Mono<String> monoDefer = Mono.defer(() -> Mono.just(s))
                .map(String::toUpperCase);
        hello.subscribe((str)-> System.out.println("mono just consumer 1:::"+str));
        monoDefer.subscribe((str)-> System.out.println("mono defer consumer 1:::"+str));
        s = "hello2";
        hello.subscribe((str)-> System.out.println("mono just consumer 2:::"+str));
        monoDefer.subscribe((str)-> System.out.println("mono defer consumer 2:::"+str));
        s = "hello3";
        hello.subscribe((str)-> System.out.println("mono just consumer 3:::"+str));
        monoDefer.subscribe((str)-> System.out.println("mono defer consumer 3:::"+str));

    }

    @Test
    public void testZip() {
        Flux<String> numbers = Flux.fromIterable(Arrays.asList("1", "2", "3","4"));
        Flux<String> strings = Flux.fromIterable(Arrays.asList("one", "two", "three","four","five"));
        numbers.zipWith(strings, (a,b)-> a + "->"+ b)
                .subscribe(System.out::println);
    }

}

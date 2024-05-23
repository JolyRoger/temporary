package org.code;

import lombok.extern.slf4j.Slf4j;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class CalculateHits {

    private record Pair(Integer num, String line) {}
    public void go() {
        var lst = List.of("1:one", "2:two", "3:three", "2:newTwo", "4:four", "1:newOne", "2:anotherTwo", "1:anotherOne");

        var res = lst.stream()
                        .map(line -> {
                            var lineArr = line.split(":");
                            var num = Integer.parseInt(lineArr[0]);
                            var payload = lineArr[1];
                            return new Pair(num, payload);
                        }).collect(Collectors.groupingBy(rec -> rec.num));

        var maxHits = res.values().stream()
                .max(Comparator.comparingInt(List::size))
                .map(List::size)
                .orElse(-1);

        var finalRes = res.values().stream()
                .filter(maxListCandidate -> maxListCandidate.size() == maxHits)
                .flatMap(Collection::stream)
                .map(Pair::line)
                .collect(Collectors.joining(","));
        log.info("Final res: {}", finalRes);
    }

    public static void main(String[] args) {
        new CalculateHits().go();
    }
}

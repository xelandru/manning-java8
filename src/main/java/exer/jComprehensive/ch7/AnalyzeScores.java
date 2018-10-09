package exer.jComprehensive.ch7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.function.Function;

import static java.util.stream.Collectors.*;

/**
 * Ex 7.4
 */
public class AnalyzeScores {
    public static void main(String[] args) {
        int[] scores = new int[]{1, 2, 3,2,3,1,3,3,0,1,0};

        IntSummaryStatistics statistics = Arrays
                .stream(scores)
                .summaryStatistics();


        Map<Boolean, Long> analize = Arrays
                .stream(scores)
                .boxed()
                .collect(partitioningBy(x -> x <= statistics.getAverage(), counting()));

        System.out.println(analize);
    }
}

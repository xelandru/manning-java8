package chp3;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by alex on 7/19/18.
 */
@FunctionalInterface
public interface BufferedReaderProcessor{
    String process(BufferedReader b) throws IOException;
}

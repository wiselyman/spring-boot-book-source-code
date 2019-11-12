package top.wisely.learningspringbootactuator.actuator;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MyHttpTraceRepository implements HttpTraceRepository {
    private static List< HttpTrace> traceList = new ArrayList<>();
    @Override
    public List<HttpTrace> findAll() {
        return traceList;
    }

    @Override
    public void add(HttpTrace trace) {
        traceList.add(trace);
    }
}

package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.TrainingSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ChartServiceImplementation implements ChartService{

    @Override
    public List<List<Object>> buildPieChartPercentageData(HashMap<String, Integer> sourceData, String xName, String yName) {

        List<Object> header = new ArrayList<>();
        header.add(xName);
        header.add(yName);

        List<List<Object>> chartData = new ArrayList<>();
        chartData.add(header);

        for (String key : sourceData.keySet()) {
            Integer percentage = sourceData.get(key);
            List<Object> row = new ArrayList<>();
            row.add(key);
            row.add(percentage);
            chartData.add(row);
        }

        return chartData;
    }

    @Override
    public List<Map<String, Number>> buildLineChartData(List<TrainingSession> sessions,
                                                        String xName, Function<TrainingSession, Number> xFunction,
                                                        String yName, Function<TrainingSession, Number> yFunction) {
        return sessions.stream()
                .map(session -> {
                    Map<String, Number> point = new HashMap<>();
                    point.put(xName, xFunction.apply(session));
                    point.put(yName, yFunction.apply(session));
                    return point;
                })
                .collect(Collectors.toList());
    }
}

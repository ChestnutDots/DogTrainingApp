package com.myApps.DogTrainingApp.service;

import com.myApps.DogTrainingApp.entities.TrainingSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;


public interface ChartService {
   List<List<Object>> buildPieChartPercentageData(HashMap<String, Integer> sourceData, String xName, String yName);
   List<Map<String,Number>> buildLineChartData(List<TrainingSession> sessions,
                                               String xName, Function<TrainingSession, Number> xFunction,
                                               String yName, Function<TrainingSession, Number> yFunction);
}

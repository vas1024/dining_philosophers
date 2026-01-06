package diningPhilosophers.controller;

import diningPhilosophers.service.EatingService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class CircleDiagramController {

  private final EatingService eatingService;

  public CircleDiagramController( EatingService eatingService ) { this.eatingService = eatingService; }


  @GetMapping("/api/chart-data")
  @ResponseBody
  public List<Map<String, Object>> getChartData() {
    return eatingService.getPhilosophersForChart();
  }

  @GetMapping("/chart")
  public String chartPage() {
    return "chart"; // HTML страница
  }

}

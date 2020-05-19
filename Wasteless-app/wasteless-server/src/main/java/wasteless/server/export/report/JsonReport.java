package wasteless.server.export.report;

import com.google.gson.Gson;
import wasteless.server.export.decorator.ReportColor;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static wasteless.server.export.decorator.ReportColor.GRAY;

public class JsonReport implements Report {

    private ReportColor reportColor = GRAY;

    public JsonReport(ReportColor reportColor) {
        this.reportColor = reportColor;
    }

    public ReportColor getReportColor() {
        return reportColor;
    }

    @Override
    public void setReportColor(ReportColor reportColor) {
        this.reportColor = reportColor;
    }

    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {
        Gson gson = new Gson();
        try {
            Files.createDirectories(Paths.get("reports"));
            Path path = Paths.get("reports","json-report.json");
            FileWriter fileWriter = new FileWriter(path.toFile());
            gson.toJson(wasteCalculatorDTO, fileWriter);
            gson.toJson(reportColor, fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

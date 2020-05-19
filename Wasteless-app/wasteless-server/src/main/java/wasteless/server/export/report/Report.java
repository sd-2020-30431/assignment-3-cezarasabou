package wasteless.server.export.report;

import wasteless.server.export.decorator.ReportColor;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

public interface Report {

    void setReportColor(ReportColor reportColor);
    void dumpReport(WasteCalculatorDTO wasteCalculatorDTO);
}

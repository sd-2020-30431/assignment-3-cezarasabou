package wasteless.server.export.decorator;

import wasteless.server.export.report.Report;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import static wasteless.server.export.decorator.ReportColor.GREEN;

public class GreenReportDecorator extends ReportDecorator{

    public GreenReportDecorator(Report decoratedReport) {
        super(decoratedReport);
    }

    @Override
    public void setReportColor(ReportColor reportColor) {
        reportColor = GREEN;
    }

    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {
        super.dumpReport(wasteCalculatorDTO);
    }
}

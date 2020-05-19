package wasteless.server.export.decorator;

import wasteless.server.export.report.Report;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

public abstract class ReportDecorator implements Report {

    protected Report decoratedReport;

    public ReportDecorator(Report decoratedReport) {
        this.decoratedReport = decoratedReport;
    }

    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {
        decoratedReport.dumpReport(wasteCalculatorDTO);
    }
}

package wasteless.server.export.decorator;

import wasteless.server.export.report.Report;
import wasteless.server.presentation.dto.WasteCalculatorDTO;

import static wasteless.server.export.decorator.ReportColor.RED;

public class RedReportDecorator extends ReportDecorator{
    public RedReportDecorator(Report decoratedReport) {
        super(decoratedReport);
    }

    @Override
    public void setReportColor(ReportColor reportColor) {
        reportColor = RED;
    }

    @Override
    public void dumpReport(WasteCalculatorDTO wasteCalculatorDTO) {
        super.dumpReport(wasteCalculatorDTO);
    }


}

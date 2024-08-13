package Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {

    public static ExtentReports getReportObject()
    {
        // 1. ExtentSparkReport Class - Creates report and config report
        String path = System.getProperty("user.dir") + "//reports//index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        reporter.config().setReportName("Web Automation Test Results");
        reporter.config().setDocumentTitle("Online Automation Results");

        // 2. ExtentReports - Drives all our reporting execution
        ExtentReports reportDriver = new ExtentReports();
        reportDriver.attachReporter(reporter);
        reportDriver.setSystemInfo("Tester","Adaline");
        return reportDriver;

    }


}

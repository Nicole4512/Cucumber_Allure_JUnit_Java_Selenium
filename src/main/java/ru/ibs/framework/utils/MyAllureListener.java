package ru.ibs.framework.utils;

import io.cucumber.plugin.event.EventHandler;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.Status;
import io.cucumber.plugin.event.TestStepFinished;
import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.ibs.framework.managers.DriverManager;

public class MyAllureListener extends AllureCucumber5Jvm implements TestWatcher {
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "pgn")
    public byte[] getScreenshot(){
        return ((TakesScreenshot) DriverManager.getDriverManager().getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void setEventPublisher(EventPublisher publisher) {
        publisher.registerHandlerFor(TestStepFinished.class, new EventHandler<TestStepFinished>() {
            @Override
            public void receive(TestStepFinished testStepFinished) {
                if(testStepFinished.getResult().getStatus().is(Status.FAILED)){
                    getScreenshot();
                }
            }
        });
        super.setEventPublisher(publisher);
    }
}

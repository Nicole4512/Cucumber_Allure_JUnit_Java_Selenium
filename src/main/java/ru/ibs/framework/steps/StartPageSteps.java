package ru.ibs.framework.steps;

import io.cucumber.java.ru.И;
import ru.ibs.framework.managers.PageManager;

public class StartPageSteps {
    private final PageManager pageManager = PageManager.getPageManager();

    @И("^Выполняем поиск по '(.+)'$")
    public void fillFinder(String value){
        pageManager.getStartPage().getHeaderBlock().fillFinder(value);
    }
}

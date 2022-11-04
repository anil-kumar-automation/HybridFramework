package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Calendar extends ExplicitWaiting{
  public WebDriver driver;

    public void selectDate(String expectDay, String expectMonth, String expectYear) {
        if (expectMonth.equals("February") && Integer.parseInt(expectDay) > 29) {
            System.out.println("wrong date: " + expectMonth + " : " + expectDay);
            return;
        }
        if (Integer.parseInt(expectDay) > 31) {
            System.out.println("wrong date: " + expectMonth + " : " + expectDay);
            return;
        }

        String monthvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
        String yearvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        System.out.println(monthvalue);
        System.out.println(yearvalue);

        while (!(monthvalue.equals(expectMonth) && yearvalue.equals(expectYear))) {
            driver.findElement(By.cssSelector("i.oxd-icon.bi-chevron-right")).click(); //clicking next action
            monthvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-month-selected']/p")).getText();
            yearvalue = driver.findElement(By.xpath("//div[@class='oxd-calendar-selector-year-selected']/p")).getText();
        }
        int count = driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).size();
        for (int i = 0; i < count; i++) {
            String text = driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).getText();
            if (text.equalsIgnoreCase(expectDay)) {
                driver.findElements(By.xpath("//div[@class='oxd-calendar-dates-grid']/div")).get(i).click();
                break;
            }
        }
    }
}


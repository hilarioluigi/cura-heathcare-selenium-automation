package pages;

import actionDriver.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;

public class AppointmentPage extends Action {
    By menu = By.id("menu-toggle");
    By history = By.linkText("History");
    By logout = By.linkText("Logout");

    By checkReadmission = By.id("chk_hospotal_readmission");
    By optionMedicareProgram = By.xpath("//input[@value='Medicare']");
    public By dateVisit = By.id("txt_visit_date");
    By calender = By.className("datepicker-days");
    By monthYearCalender = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch' and text()]");
    By nextMonthButton = By.xpath("//div[@class='datepicker-days']//th[@class='next']");
    By areaComment = By.id("txt_comment");
    By buttonBookAppointment = By.id("btn-book-appointment");

    LocalDate expectedDate = LocalDate.now().plusMonths(14);
    DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("d");
    String currentMonthYearCalender;
    String expectedMonthYear = expectedDate.format(monthYearFormatter);
    String expectedDay = expectedDate.format(dayFormatter);

    By dayCalender = By.xpath("//td[@class='day' and text()=\"" + expectedDay + "\"]");


    String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vel erat vitae nisi interdum imperdiet. Mauris efficitur elit felis, ac dignissim magna malesuada ac. Ut et dolor nibh. Aliquam tempus eros pellentesque, eleifend erat non, porta quam. Donec rhoncus ultrices elementum. Mauris accumsan felis vel efficitur tempor. Ut sollicitudin dolor justo, eu maximus mauris vulputate ut. Curabitur quis rutrum dolor, efficitur ullamcorper leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc justo erat, consequat id viverra et, ultrices eu neque. Phasellus ac feugiat augue. Cras laoreet lacus felis, a pellentesque eros sollicitudin ut. Suspendisse accumsan ac dolor viverra dignissim. Aliquam sagittis volutpat eros, et convallis ipsum vulputate non.";

    public AppointmentPage(WebDriver driver){
        super(driver);
    }

    public void openMenu() {
        waitForClickable(menu, 15).click();
    }

    public void clickLogout() {
        waitForClickable(logout, 15).click();
    }

    public void clickHistory() {
        waitForClickable(history, 15).click();
    }

    public void selectFacility(String facilityText) {
        By comboFacility = By.id("combo_facility");
        WebElement dropDownFacility = waitForVisibility(comboFacility, 15);
        Select facilityOption = new Select(dropDownFacility);

        waitForClickable(comboFacility,15);
        facilityOption.selectByVisibleText(facilityText);
    }

    public void selectHospitalReadmission() {
        waitForClickable(checkReadmission, 15).click();
    }

    public void selectHealthcareProgram() {
        waitForClickable(optionMedicareProgram, 15).click();
    }

    public void clickVisitDate() {
        waitForClickable(dateVisit, 15).click();
    }

    public void chooseDate() {
        waitForVisibility(calender, 15);
        currentMonthYearCalender = waitForVisibility(monthYearCalender, 20).getText();
        //log
        int counter = 0;
        while(!Objects.equals(currentMonthYearCalender, expectedMonthYear)) {
            waitForClickable(nextMonthButton, 15).click();
            waitForVisibility(calender, 15);
            currentMonthYearCalender = waitForVisibility(monthYearCalender, 20).getText();
            //log
            System.out.println("Loop date checked " + counter);
            counter++;
        }
        waitForClickable(dayCalender, 15).click();
    }

    public void enterComment() {
        waitForClickable(areaComment, 15).sendKeys(comment);
    }

    public void clickBookAppointment() {
        waitForClickable(buttonBookAppointment, 15).click();
    }
}

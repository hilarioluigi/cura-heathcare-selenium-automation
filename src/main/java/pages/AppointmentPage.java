package pages;

import actionDriver.WaitAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.time.LocalDate;

public class AppointmentPage extends WaitAction {
    By menu = By.id("menu-toggle");
    By history = By.linkText("History");
    By logout = By.linkText("Logout");
    By comboFacility = By.id("combo_facility");
    public By facilityTokyoFacilityOpt = By.xpath("//option[@value='Tokyo CURA Healthcare Center']");
    public By facilityHongkongFacilityOpt = By.xpath("//option[@value='Tokyo CURA Healthcare Center']");
    public By facilitySeoulFacilityOpt = By.xpath("//option[@value='Seoul CURA Healthcare Center']");
    By checkReadmission = By.id("chk_hospotal_readmission");
    By optionMedicareProgram = By.xpath("//input[@value='Medicare']");
    By dateVisit = By.id("txt_visit_date");
    By monthYearCalender = By.xpath("//div[@class='datepicker-days']//th[@class='datepicker-switch']");
    By nextMonthButton = By.xpath("//div[@class='datepicker-days']//th[@class='next']");
    By areaComment = By.id("txt_comment");
    By buttonBookAppointment = By.id("btn-book-appointment");

    LocalDate expectedDate = LocalDate.now().plusMonths(14);
    DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("MMMM yyyy");
    DateTimeFormatter dayFormatter = DateTimeFormatter.ofPattern("dd");
    String expectedMonthYear = expectedDate.format(monthYearFormatter);
    String expectedDay = expectedDate.format(dayFormatter);

    By dayCalender = By.xpath("//td[@class='day' and text()=\"" + expectedDay + "\"]");


    String comment = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla vel erat vitae nisi interdum imperdiet. Mauris efficitur elit felis, ac dignissim magna malesuada ac. Ut et dolor nibh. Aliquam tempus eros pellentesque, eleifend erat non, porta quam. Donec rhoncus ultrices elementum. Mauris accumsan felis vel efficitur tempor. Ut sollicitudin dolor justo, eu maximus mauris vulputate ut. Curabitur quis rutrum dolor, efficitur ullamcorper leo. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Nunc justo erat, consequat id viverra et, ultrices eu neque. Phasellus ac feugiat augue. Cras laoreet lacus felis, a pellentesque eros sollicitudin ut. Suspendisse accumsan ac dolor viverra dignissim. Aliquam sagittis volutpat eros, et convallis ipsum vulputate non.";

    public AppointmentPage(WebDriver driver){
        super(driver);
    }

    public void openMenu() {
        click(menu);
    }

    public void clickLogout() {
        click(logout);
    }

    public void clickHistory() {
        click(history);
    }

    public void selectFacility(By facility) {
        click(comboFacility);
        click(facility);
    }

    public void selectHospitalReadmission() {
        click(checkReadmission);
    }

    public void selectHealthcareProgram() {
        click(optionMedicareProgram);
    }

    public void chooseDate() {
        click(dateVisit);
        String currentMonthYearCalender = getText(monthYearCalender);
        while(!Objects.equals(currentMonthYearCalender, expectedMonthYear)) {
            click(nextMonthButton);
            currentMonthYearCalender = getText(monthYearCalender);
        }
        click(dayCalender);
    }

    public void enterComment() {
        inputText(areaComment, comment);
    }

    public void clickBookAppointment() {
        click(buttonBookAppointment);
    }
}

package tests.smartphones;

import helpers.JavaScriptHelper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.SmartphoneProductPage;
import pages.SmartphonesPage;
import pages.StartPage;
import tests.BaseTest;

public class Pattern1POMTest extends BaseTest {
    @Test
    public void dnsTest() {
        // ***** Стартовая страница сайта DNS *****
        StartPage startPage = new StartPage(driver);
        // Открыть страницу https://www.dns-shop.ru/
        startPage.openPage();
        // Наведение курсора мыши на ссылку "Смартфоны и гаджеты"
        startPage.linkSmartsAndGadgetsMove();
        // Нажатие на ссылку "Смартфоны"
        startPage.linkSmartsClick();

        // ***** Страница "Смартфоны" *****
        SmartphonesPage smartphonesPage = new SmartphonesPage(driver);
        // Отображение сортировки
        smartphonesPage.accordeonSortClick();
        // Установка сортировки "Сначала дорогие"
        String type = "Сначала дорогие";
        smartphonesPage.radiobuttonSortClick(type);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 600);
        // Установка фильтра "Производитель"
        String company = "Samsung"; // производитель
        smartphonesPage.checkboxCompanyClick(company);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 400);
        // Отображение фильтра "Объем оперативной памяти"
        smartphonesPage.accordeonRAMClick();
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 400);
        // Установка фильтра "Объем оперативной памяти"
        String ram = "8 Гб"; // объем ОП
        smartphonesPage.checkboxRAMClick(ram);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 600);
        // Нажатие на кнопку "Применить"
        smartphonesPage.buttonApplyClick();
        // Прокрутка страницы вверх
        JavaScriptHelper.scrollBy(0, -2000);
        // Нажатие на ссылку первого продукта в списке
        smartphonesPage.linkFirstProductClick("Смартфон Samsung Galaxy Z Flip3 256 ГБ бежевый");

        // ***** Страница "Продукт. Смартфон"*****
        SmartphoneProductPage smartphoneProductPage = new SmartphoneProductPage(driver);
        String actual = smartphoneProductPage.getPageTitle();

        // Проверка заголовка открытой страницы
        String expected = "Купить 6.7\" Смартфон Samsung Galaxy Z Flip3 256 ГБ бежевый в интернет магазине DNS. Характеристики, цена Samsung Galaxy Z Flip3 | 9913525";
        Assertions.assertEquals(expected, actual, "Ошибка! Заголовок страницы не соответствует ожидаемому!");
    }
}

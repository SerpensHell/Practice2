package tests.smartphones;

import helpers.JavaScriptHelper;
import org.junit.jupiter.api.Test;
import pages.SmartphoneProductPagePFPE;
import pages.SmartphonesPagePFPE;
import pages.StartPagePFPE;
import tests.BaseTest;
import tests.smartphones.matchers.SmartphoneProductPageMatcherPFPE;

public class Pattern5PETest extends BaseTest {
    @Test
    public void dnsTest() {
        // 1. Arrange
        String company = "Samsung"; // производитель
        String ram = "8 Гб"; // объем ОП

        // 2. Act
        SmartphoneProductPagePFPE smartphoneProductPage = getProductPage(company, ram);

        // 3. Assert
        // Проверка заголовка открытой страницы
        String expected = "Купить 6.7\" Смартфон Samsung Galaxy Z Flip3 256 ГБ бежевый в интернет магазине DNS. Характеристики, цена Samsung Galaxy Z Flip3 | 9913525";
        SmartphoneProductPageMatcherPFPE smartphoneProductPageMatcher = new SmartphoneProductPageMatcherPFPE(smartphoneProductPage);
        smartphoneProductPageMatcher.pageTitleEquals(expected);
    }

    // Получение заголовка страницы с продуктом
    // Получение заголовка страницы с продуктом
    public SmartphoneProductPagePFPE getProductPage(String company, String ram) {
        // ***** Стартовая страница сайта DNS *****
        StartPagePFPE startPage = new StartPagePFPE(driver);
        // Открыть страницу https://www.dns-shop.ru/
        startPage.openPage();
        // Наведение курсора мыши на ссылку "Смартфоны и гаджеты"
        startPage.linkSmartsAndGadgets().focusOnLink();
        // Нажатие на ссылку "Смартфоны"
        startPage.linkSmarts().click();

        // ***** Страница "Смартфоны" *****
        SmartphonesPagePFPE smartphonesPage = new SmartphonesPagePFPE(driver);
        // Отображение сортировки
        smartphonesPage.accordeonSort().show();
        // Установка сортировки "Сначала дорогие"
        String type = "Сначала дорогие";
        smartphonesPage.radiobuttonSort(type).setSelected(true);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 600);
        // Установка фильтра "Производитель"
        smartphonesPage.checkboxCompany(company).setChecked(true);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 400);
        // Отображение фильтра "Объем оперативной памяти"
        smartphonesPage.accordeonRAM().show();
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 400);
        // Установка фильтра "Объем оперативной памяти"
        smartphonesPage.checkboxRAM(ram).setChecked(true);
        // Прокрутка страницы вниз
        JavaScriptHelper.scrollBy(0, 600);
        // Нажатие на кнопку "Применить"
        smartphonesPage.buttonApply().click();
        // Прокрутка страницы вверх
        JavaScriptHelper.scrollBy(0, -2000);
        // Нажатие на ссылку первого продукта в списке
        smartphonesPage.linkFirstProduct().openInNewWindow();

        // ***** Страница "Продукт. Смартфон" *****
        return new SmartphoneProductPagePFPE(driver);
    }
}

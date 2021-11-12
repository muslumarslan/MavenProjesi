import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class BaseTest {
    WebDriver driver;
    @BeforeAll
    public void setUp() {
        //System.setProperty("webdriver.chrome.driver","C:\\Users\\Lenovo\\Downloads\\chromedriver_win32\\chromedriver.exe");
        WebDriverManager.chromedriver().driverVersion("95.0.4638.69").setup();
        driver = new ChromeDriver();
        //driver.get("https://www.lcwaikiki.com/tr-TR/TR");
        //driver.manage().window().maximize();
    }

    @Test
    @Order(1)
    public void AnaSayfa(){
        driver.get("https://www.lcwaikiki.com/tr-TR/TR");
        driver.manage().window().maximize();
        System.out.println("Ana sayfa açıldı");
    }

    @Test
    @Order(2)
    public void Giris(){
        //driver.get("https://www.lcwaikiki.com/tr-TR/TR/giris");
        //driver.manage().window().maximize();

        driver.findElement(By.linkText("Giriş Yap")).click();
        driver.findElement(By.id("LoginEmail")).sendKeys("muslum123!*@gmail.com");
        System.out.println("muslum123!*@gmail.com yazıldı");
        //driver.findElement(By.id("Password")).click();
        driver.findElement(By.id("Password")).sendKeys("123456");
        System.out.println("123456 yazıldı");
        driver.findElement(By.id("loginLink")).click();
        System.out.println("Giriş Başarılı");
    }

    @Test
    @Order(3)
    public void Arama(){
        driver.findElement(By.id("search")).click();
        //driver.findElement(By.cssSelector("#AnındaSepetİndirimi")).sendKeys("");
        driver.findElement(By.id("search")).sendKeys("pantolon");
        System.out.println("pantolon yazıldı");
        driver.findElement(By.id("search")).sendKeys(Keys.ENTER);
        System.out.println("Arama tuşuna basıldı");
    }

    @Test
    @Order(4)
    public void SayfanınSonu(){

        // //*[@id="model_1430481_4856700"]/div[1]/img[1]
        driver.findElement(By.xpath("/html/body/div[5]/div[4]/div[1]/div/div/div[1]/a[1]/img"));
        System.out.println("scroll bar aşağı kaydırıldı.");
        //driver.findElement(By.id("pageIndex")).click();
        //System.out.println("Daha fazla ürün gör butonuna basıldı.");
    }

    @Test
    @Order(5)
    public void DahaFazlaUrun(){
        driver.findElement(By.id("pageIndex")).click();
        System.out.println("Daha fazla ürün gör butonuna basıldı.");
    }

    @Test
    @Order(6)
    public void RastgeleUrunSec(){
        driver.findElement(By.xpath("//*[@id=\"model_1458850_4691617\"]/div[1]/img[1]")).click(); //ürün seçildi
        System.out.println("Rastgele ürün seçildi.");
    }


    @Test
    @Order(7)
    public void UrunuSepeteEkleme(){

        driver.findElement(By.xpath("//*[@id=\"option-size\"]/a[7]")).click();   //beden
        driver.findElement(By.xpath("//*[@id=\"option-height\"]/a[4]")).click(); //boy
        driver.findElement(By.xpath("//*[@id=\"pd_add_to_cart\"]")).click();     //sepete ekleme
        System.out.println("Ürün sepete eklendi.");
    }


    @Test
    @Order(8)
    public void FiyatKarsilastirma(){
        driver.findElement(By.linkText("Sepetim")).click();   //spete tıklandı
        String urunSayfasındakiFiyat = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]/span[1]")).getText(); //ürün sayfasındaki fiyat
        String sepettekiFiyat = driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[2]/div[1]/div[6]/div/span[2]")).getText();                  //sepetteki fiyat
        System.out.println("Fiyatlar alındı.");
        /*    if (Integer.valueOf(urunSayfasındakiFiyat) == Integer.valueOf(sepettekiFiyat)){
            System.out.println("Ürün sayfasındaki fiyat ile sepetteki fiyat eşittir.");
        }
        else if (Integer.valueOf(urunSayfasındakiFiyat) > Integer.valueOf(sepettekiFiyat)){
            System.out.println("Ürün sayfasındaki fiyat fazladır.");
        }
        else{
            System.out.println("Sepetteki fiyat fazladır.");
        }
*/
    }

    @Test
    @Order(9)
    public void SepettekiUrunMiktariniArttirma(){

        /*String Deger =driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[1]/div/input")).getText().toString();
        System.out.println(Deger);
        Integer DegerInt = Integer.valueOf(Deger);
        DegerInt = DegerInt + 1 ;
        System.out.println(DegerInt);
        String DegerStr = String.valueOf(DegerInt);
        System.out.println(DegerStr);
        driver.findElement(By.xpath("//*[@id=\"ShoppingCartContent\"]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div/div[1]/div/input")).sendKeys(DegerStr );
         */
        driver.findElement(By.linkText("+")).click();     //sepetteki ürün arttrıldı

        //driver.findElement(By.cssSelector("//a[@class='oq-up plus oq-disable']")).click();
        //driver.findElement(By.cssSelector("//input[@class='item-quantity-input ignored']")).click();
        System.out.println("Sepetteki ürün miktarı arttırıldı.");
        //System.out.println(driver.findElement(By.cssSelector("input[type='text'][value='1']")).getText());   //kontrolü yapıldı.

    }

    @Test
    @Order(10)
    public void SepettekiUrunleriSilme(){

        driver.findElement(By.className("//a[@class='cart-square-link']")).click();
        //driver.findElement(By.cssSelector("#Cart_ProductDelete_706687164")).click();
        System.out.println("Sepetteki ürünler silindi.");
        System.out.println(driver.findElement(By.cssSelector("input[type='text'][value='1']")).getText());    //kontrolü yapıldı.

    }


}

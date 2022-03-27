package com.devops.dxc.devops;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.devops.dxc.devops.model.Util;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DevopsApplicationTests {

	//Caso 1
	//Sueldo: $2.000.000
	//Ahorro: $10.000.000
	@Test
	public void testGetDxc_CASO1() throws Exception{
		assertEquals(1000000, Util.getDxc(10000000, 2000000));
	}

	@Test
	public void testGetImpuesto_CASO1() throws Exception{
		assertEquals(80000, Util.getImpuesto(10000000, 2000000));
	}

	@Test
	public void testGetSaldo_CASO1() throws Exception{
		assertEquals(9000000, Util.getSaldo(10000000, 2000000));
	}

	//Caso 2
	//Sueldo: $1.400.000
	//Ahorro: $30.000.000
	@Test
	public void testGetDxc_CASO2() throws Exception{
		//10%
		assertEquals(3000000, Util.getDxc(30000000, 1400000));
	}

	@Test
	public void testGetImpuesto_CASO2() throws Exception{
		//Impuesto
		assertEquals(0, Util.getImpuesto(30000000, 1400000));
	}

	@Test
	public void testGetSaldo_CASO2() throws Exception{
		//Saldo
		assertEquals(27000000, Util.getSaldo(30000000, 1400000));
	}

	//Caso 3
	//Sueldo: $2.000.000
	//Ahorro: $18.000.000
	@Test
	public void testGetDxc_CASO3() throws Exception{
		//10%
		assertEquals(1800000, Util.getDxc(18000000, 2000000));
	}

	@Test
	public void testGetImpuesto_CASO3() throws Exception{
		//Impuesto
		assertEquals(144000, Util.getImpuesto(18000000, 2000000));
	}

	@Test
	public void testGetSaldo_CASO3() throws Exception{
		//Saldo
		assertEquals(16200000, Util.getSaldo(18000000, 2000000));
	}

	@Test
	public void seleniumTest() throws Exception{
	  System.out.print("**********************Ejecutando Test de Selenium***********************");
	  JavascriptExecutor js;
	  WebDriver driver;
	  String resultado;
	  Integer timout = 4;

	  String OS = System.getProperty("os.name").toLowerCase();
	  System.out.println("OS: " + OS);
	  if(OS.contains("win")){
		System.out.println("Es Windows");
		System.setProperty("webdriver.chrome.driver","src/driver/win/chromedriver.exe"); // Windows
	  }else{
		System.out.println("No es Windows");
		System.setProperty("webdriver.chrome.driver","src/driver/linx/chromedriver"); // Linux
	  }

	  // Chrome Driver Options (Utiliza el navegador sin interfaz)
	  ChromeOptions options = new ChromeOptions();
	  options.addArguments("--headless");
	  options.addArguments("--no-sandbox");
	  options.addArguments("--disable-dev-shm-usage");
	  driver = new ChromeDriver(options);
	  Integer sleepTimeMill = 30000; // 30 segundos

	  js = (JavascriptExecutor) driver;
	  System.out.print("Ejecutando Test de Selenium");
	  driver.get("http://localhost:3000/");
	  driver.manage().window().setSize(new Dimension(1276, 693));
	  driver.findElement(By.id("input_sueldo")).click();
	  driver.findElement(By.id("input_sueldo")).sendKeys("2000000");
	  driver.findElement(By.id("input_ahorro")).click();
	  driver.findElement(By.id("input_ahorro")).sendKeys("10000000");

	  // Test DXC
	  driver.findElement(By.id("btn_dxc")).click();
	  TimeUnit.MILLISECONDS.sleep(sleepTimeMill);
	  resultado = driver.findElement(By.id("resultado")).getText();
	  assertEquals("DXC: 1000000", resultado);

	  // Test Saldo
	  driver.findElement(By.id("btn_saldo")).click();
	  TimeUnit.MILLISECONDS.sleep(sleepTimeMill);
	  resultado = driver.findElement(By.id("resultado")).getText();
	  assertEquals("Saldo: 9000000", resultado);

      // Test Impuesto
      driver.findElement(By.id("btn_impuesto")).click();
      TimeUnit.MILLISECONDS.sleep(sleepTimeMill);
      resultado = driver.findElement(By.id("resultado")).getText();
      assertEquals("Impuesto: 80000", resultado);

	  driver.close();
	}

}

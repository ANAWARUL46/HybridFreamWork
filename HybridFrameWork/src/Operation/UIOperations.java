package Operation;

import java.io.File;
import java.util.Properties;

import org.apache.commons.collections4.bag.SynchronizedSortedBag;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class UIOperations {
	WebDriver driver;
	public UIOperations(WebDriver driver) {
		this.driver=driver;
		
	}
	public void KeyWordPerform(Properties p, String keyword,String objectName,String objectType,
		String data	) throws Exception {
		switch( keyword.toUpperCase()) {
		case"GOTOURL":
			driver.get(p.getProperty(data));
			break;
			
		case"SENDKEYS":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
			
		case"CLICK":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			break;
			
		case"SELECT":
			new Select(driver.findElement(this.getObject(p, objectName, objectType))).selectByVisibleText(data);;
			break;
			
		case"GETTITLE":
			String tital=driver.getTitle();
			System.out.println("Tital is :+title");
			break;
			
		case"GETTEXT":
			String text=driver.findElement(this.getObject(p, objectName, objectType)).getText();
			System.out.println("Text is : "+text);
			break;
			
		case"GETTYPEDTEXT":
			String TypedText=driver.findElement(this.getObject(p, objectName, objectType)).getAttribute("value");
			System.out.println("Typed Text is: "+TypedText);
			break;
			
		
			
		case"GETPOINT":
			Point p1=driver.findElement(this.getObject(p, objectName, objectType)).getLocation();
			System.out.println("Point is:"+p1);
			
		case"NEWLINE":
			driver.findElement(this.getObject(p, objectName, objectType)).sendKeys(data);
			break;
			
	    case"SCREENSHOT":
			File f=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(f,new File(System.getProperty("user.dir")+"\\"+"CaptureShot\\ScreenShot1.png"));
			//FileUtils.copyFile(srcFile, new File("C:\\Shot\\Facebook.jpg"));
			break;
			
		case"GOTOBACKPAGE":
			driver.navigate().back();
			break;
			
		case"GOTOFORWARDPAGE":
			driver.navigate().forward();
			break;
			
		case"PAGEREFRESH":
			driver.navigate().refresh();
			break;
			
		case"ALERTHANDLING":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			Alert alt=driver.switchTo().alert();
			alt.accept();
			System.out.println(alt.getText());
			break;
			
		case"IMAGEVERIFY":
			Actions act=new Actions (driver);
			act.moveToElement(driver.findElement(this.getObject(p, objectName, objectType))).build().perform();
			break;
			
		case"DRAGANDDROP":
			WebElement sre=driver.findElement(this.getObject(p, objectName, objectType));
			WebElement dst=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act1=new Actions (driver);
			act1.dragAndDrop(sre, dst).build().perform();
			//System.out.println("Drag And Drop Successfully");
			break;
			
		case"DOUBLECLICK":
			WebElement DBC=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act2=new Actions(driver);
			act2.doubleClick(DBC).build().perform();
			break;
			
		case"RIGHTCLICK":
			WebElement element1=driver.findElement(this.getObject(p, objectName, objectType));
			Actions act3=new Actions(driver);
			act3.contextClick(element1).build().perform();
			break;
			
		case"FILEDOWNLOAD":
			driver.findElement(this.getObject(p, objectName, objectType)).click();
			System.out.println("File Downlod Completed");
			break;
			
		case"SCROLLDOWN":
			JavascriptExecutor jse=(JavascriptExecutor)driver;
			jse.executeScript("window.scrollBy(0,2300)");
			System.out.println("Scrolldown completed sucessfully");
			break;
			
		case"SCROLLUP":
			JavascriptExecutor jse1=(JavascriptExecutor)driver;
			jse1.executeScript("window.scrollBy(0,-2300)");
			System.out.println("ScrollUp completed sucessfully");
			break;
		
		case"IFRAMEHANDLING":
			driver.switchTo().frame(data);
	    	driver.findElement(this.getObject(p, objectName, objectType)).sendKeys();
	    	driver.switchTo().defaultContent();
	    	break;
	    
		case"":
		
		case"THREAD":
			Thread.sleep(3000);
			break;
			
		case"CLOSE":
			driver.close();
			break;
			
		case"QUIT":
			driver.quit();
			break;
			
			
			
		}

	}
	public By getObject(Properties p,String objectName,String objectType) throws Exception {
		if(objectType.equalsIgnoreCase("ID")) {
			return By.id(p.getProperty(objectName));
		}
		else if (objectType.equalsIgnoreCase("NAME")) {
			return By.name(p.getProperty(objectName));
		
		}
		else if (objectType.equalsIgnoreCase("XPATH")) {
			return By.xpath(p.getProperty(objectName));
	
		}
		else if (objectType.equalsIgnoreCase("CLASS")) {
			return By.className(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("TAGNAME")) {
			return By.tagName(p.getProperty(objectName));
			
		}
		else if(objectType.equalsIgnoreCase("LINKTEXT")) {
			return By.linkText(p.getProperty(objectName));
		}
		else if(objectType.equalsIgnoreCase("PARTIALLINKTEXT")) {
					return By.partialLinkText(p.getProperty(objectName));
					
			}
		else if(objectType.equalsIgnoreCase("CSSSELECTOR")) {
					return By.cssSelector(p.getProperty(objectName));
		}
		else {
			throw new Exception("Wrong object type");
		}
			
	}
}

		
		
		
		
		
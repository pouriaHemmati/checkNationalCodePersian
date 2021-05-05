# national Code Persian



[![](https://jitpack.io/v/pouriaHemmati/nationalCode.svg)](https://jitpack.io/#pouriaHemmati/nationalCode)


## Installing
**Quick Setup**

**1.You can import in build.gradle like this**

```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  ```
  
```
dependencies {
	        implementation 'com.github.pouriaHemmati:nationalCode:1.0.1'
	}
  ```
  
  **use in project**
  
  ```
  var checkNationalCode = CheckNationalCode().getService()
  
  checkNationalCode.checkNationalCode("your text")
  ```
  
  ## Authors

[pouriaHemmati](https://github.com/pouriaHemmati)

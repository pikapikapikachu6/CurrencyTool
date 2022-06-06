# Major_Project

## Major_Project Commit Version And Chosen functions
Input API: Currency scoop  
Output API: Imgur  

#### Cache
```
saved the conversion data into cache
```
```
How to use: 
1. The Clean button at the menu can clean the cache
2. When conversion processing:
    if the cache exists the rate data, it will show a pop-up to let the user choose use 
cache data or not.
    if the cache doesn't exist the rate data, it will automatically save the rate data into cache.
```
#### Splash image
```
When entering the application, it will show the splash image and a loading bar for 15 seconds to enter the app.
```

#### Theme song
```
After entering the app, the theme song will automatically play and the user can stop and play through the button 
called "Music Play/Stop".
```

#### Add an ‘About’ feature
```
Added a menubar at the top of the page. And the "About" menuItem will show some informations.
```

#### Concurrency
```
Realized get httpRequest concurrency.
```

#### Spinning progress indicator
```
Realized during API calls shows a indicator about loading.
```

## Content (example for "online online")
**step1: Add currency**
```
input: click "Add Currency" and then choose a country from the map.
```
```
outcome: if the currency exists, the homepage will show the currency code and name;  
otherwise, it will show an alert.
```

**step2: Add currency**
```
input: try step1 again to choose another currency to add.
```
```
outcome: the homepage list will show two currencies.
The "Conversion" button will shows on homepage.
```

**step3: Conversion**
```
input: click "Conversion" button and then choose two currenices code and input the amount.  
And then click "Conversion" button to run.
```
```
outcome: it will show the coversion result.
```

**step4: Report**
```
input: click "report" button.
```
```
outcome: it will show the link, click the link and will show the QR picture, scan the QR and then can read informations.
```

**step5: Delete Currency**
```
input: click "Delete Currency" button at the homepage, and then input the currency code.
Click "Delete" button.
```
```
outcome: The chosen currency will be delete if exists in the currency list.
Otherwise, it will show an alert.
When the currency list is empty, it will show an alert.
```

**step5: Clean table**
```
input: clcik "Clean table" button at the homepage.
```
```
outcome: The currency list will be empty.
```

## Run Steps

**Basically Build**

```
gradle build
```

**Run**

```
gradle run --args="online online"
```
or
```
gradle run --args="online offline"
```
or
```
gradle run --args="offline online"
```
or
```
gradle run --args="offline offline"
```

**Clean**

```
gradle clean
```

# Major_Project

## Major_Project Commit Version And Chosen functions
Input API: Currency scoop  
Output API: Imgur  
Claimed Tier: Distinction  
Credit Optional Feature 1: About  
Credit Optional Feature 2: Splash page  
Credit Optional Feature 3: Theme song  
Distinction Optional Feature: Progress indicator  
High Distinction Optional Feature:  

Milestone 1 Submission:  
SHA: < 37e7caa7e69967f343c2d15a9432e9bbcfe4e640 >  
URI: <https://github.sydney.edu.au/shli5792/SCD2_2022/tree/37e7caa7e69967f343c2d15a9432e9bbcfe4e640/major_project>  

Milestone 1 Re-Submission:  
SHA: < 37e7caa7e69967f343c2d15a9432e9bbcfe4e640 >  
URI: <https://github.sydney.edu.au/shli5792/SCD2_2022/tree/37e7caa7e69967f343c2d15a9432e9bbcfe4e640/major_project>  

Milestone 2 Submission:  
SHA: < e442ada2abfcf5a0097d20da37cb8b05ba82ff40 >  
URI: <https://github.sydney.edu.au/shli5792/SCD2_2022/tree/e442ada2abfcf5a0097d20da37cb8b05ba82ff40/major_project>  

Milestone 2 Re-Submission:  
SHA: < ad76884971d26658f21f6974f52daf02cbae675d >  
URI: <https://github.sydney.edu.au/shli5792/SCD2_2022/tree/ad76884971d26658f21f6974f52daf02cbae675d/major_project>  

Exam Base Commit:  
SHA: <>  
URI: <>  

Exam Submission Commit:  
SHA: <>  
URI: <>

## Resubmission for milestone2 changed
### milestone2 feedback
```
The resources folders is in the wrong spot - it should be in the same folder as the 'java' folder. 
Empty nested packages (that aren't URIs) are usually a bad sign. E.g. model-> api. 
Remember that javadocs should be used for classes, not just methods. 
You have some model knowledge in your view - the process required to clear the cache is something the cache should know, not the view. 
1 abstracted method call. 
Design is otherwise effective. 
Perhaps a little overcomplicated inside the API package.
```

### fixed the problems
```
1. Moved the resources folder to the right spot.
2. Removed the empty nested packages "API"
3. Complete the javadoc with all classes and methods.
4. Fixed the cache problem.
5. Changed the similar JSON formate to more visible formate.
```

## New Features in milestone 2
### Credit 
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

### Distinct
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

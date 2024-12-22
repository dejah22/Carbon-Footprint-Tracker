# Carbon Footprint Tracker
🪴End-to-end browser app that calculates and tracks your carbon footprint, with the aim of fostering a greener planet🌿

## What Is It?
Your carbon footprint is the total amount of greenhouse gases you generate because of your everyday actions. A consequence of our increasingly industrialised lives, this leads to rise in global temperatures and impacts climate change by a colossal margin.
Lowering individual carbon footprints doesn’t happen overnight!
By making small changes to our actions, like carpooling, eating less meat, taking fewer connecting flights and line drying our clothes, we can start making a big difference.

## What Does It Do?
The Carbon Footprint Tracker WebApp - takes users' everyday activities as input, stores it in the database, calculates their daily, weekly and monthly carbon footprints, and displays visual statistics in the form of graphs and bar charts.
A user can be a standalone citizen, or part of an organisation such as a school or corporate office, thereby facilitating big businesses to raise awareness among its people. 

## Tech Used

* **Execution Environment** - Apache Tomcat Servlet Container (JSP 2.0 Servlet Container)
* **Backend** - Java BackEnd, JDBC (API), MySQL Database
* **Frontend** - HTML, CSS, JavaScript
* **Structure and Use-case Design** - ArgoUML

Tested and documented entirely (in [here](https://github.com/dejah22/Carbon-Footprint-Tracker/blob/main/SRS%20Document.pdf)), using appropriate UML diagrams, and has been refined using Refactoring methods, with appropriate Object-Oriented Design Patterns applied to facilitate reusability.

## Instructions
1. Clone the repository using `git clone <URL>`
2. Make sure to setup Apache Tomcat 5.5 and MySQL 8.0 Database Manager in your local system.
3. Create the DB Structure by running the [.SQL file](https://github.com/dejah22/Carbon-Footprint-Tracker/blob/main/DBSchema.sql)
    - All the metrics used for calculation of carbon footprint are also mentioned here
4. Run the Tomcat Server, and open [index.html](https://github.com/dejah22/Carbon-Footprint-Tracker/blob/main/App/build/web/index.html) in your browser.


## Acknowledgements
The detailed SRS document for the entire application can be found in [SRS Document.pdf](https://github.com/dejah22/Carbon-Footprint-Tracker/blob/main/SRS%20Document.pdf), elaborating the development process and testing. Please copy the path of this file and paste in your browser to access it.

I also thank my friends Adithi Shankar and Aishwarya Krishnakumar for working with me on this project. :)

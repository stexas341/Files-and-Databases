
function createTableFromJSON(data) {
    var html = "<table><tr><th>Category</th><th>Value</th></tr>";
    for (const x in data) {
        var category = x;
        var value = data[x];
        html += "<tr><td>" + category + "</td><td>" + value + "</td></tr>";
    }
    html += "</table>";
    return html;

}



function getUser() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#ajaxContent").html(createTableFromJSON(JSON.parse(xhr.responseText)));
          //  $("#ajaxContent").html("Successful Login");
        } else if (xhr.status !== 200) {
             $("#ajaxContent").html("User not exists or incorrect password");
        }
    };
    var data = $('#loginForm').serialize();
    xhr.open('GET', 'GetKeeper?'+data);
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}


function initDB() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
              $("#ajaxContent").html("Successful Initialization");
        } else if (xhr.status !== 200) {
             $("#ajaxContent").html("Error Occured");
        }
    };

    xhr.open('GET', 'InitDB');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

function deleteDB() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
              $("#ajaxContent").html("Successful Deletion");
        } else if (xhr.status !== 200) {
             $("#ajaxContent").html("Error Occured");
        }
    };

    xhr.open('GET', 'DeleteDB');
    xhr.setRequestHeader('Content-type','application/x-www-form-urlencoded');
    xhr.send();
}

//STELIOS START

function addVehile() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<h2>Select type of vehicle</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"addCar();\">Add Car</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"addMotorcycle();\">Add Motorcycle</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"addBike();\">Add Bike</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"addScooter();\">Add Scooter</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"showmenu();\">Back</button>");
}

function addCar() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"addVehile();\">Back</button>");
    $("#ajaxContent").load("carform.html");
}

function addCarPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Error in ");
        }
    };
    var data = $('#addCarForm').serialize();
    xhr.open('POST', 'AddCar');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function addMotorcycle() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"addVehile();\">Back</button>");
    $("#ajaxContent").load("motorcycleform.html");
}

function addMotorcyclePOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't add");
        }
    };
    var data = $('#addMotorcycleForm').serialize();
    xhr.open('POST', 'addmotorcycle');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function addBike() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"addVehile();\">Back</button>");
    $("#ajaxContent").load("bikeform.html");
}

function addBikePOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't add");
        }
    };
    var data = $('#addBikeForm').serialize();
    xhr.open('POST', 'addBike');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function addScooter() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"addVehile();\">Back</button>");
    $("#ajaxContent").load("scooterform.html");
}

function addScooterPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html("Successfully added");
        } else if (xhr.status !== 200) {
            $("#error").html("Can't add");
        }
    };
    var data = $('#addScooterForm').serialize();
    xhr.open('POST', 'addScooter');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function seeAvailable() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<h2>Select type of vehicle</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"availableCarsGET();\">Available Cars</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"availableMotorcyclesGET();\">Available Motorcycles</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"availableBikesGET();\">Available Bikes</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"availableScootersGET();\">Available Scooters</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"showmenu();\">Back</button>");
}

function availableCarsGET() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='seeAvailable()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('GET', 'availableCars');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function availableMotorcyclesGET() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='seeAvailable()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('GET', 'availableMotorcycles');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function availableBikesGET() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='seeAvailable()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('GET', 'availableBikes');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function availableScootersGET() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='seeAvailable()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('GET', 'availableScooters');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function hasDamage() {
    $("#buttons").html("");
    $("#ajaxContent").html("");
    $("#buttons").append("<button onclick='accidentPOST()' class='button' >Accident</button><br>");
    $("#buttons").append("<button onclick='repairmentPOST()' class='button' >Repairment</button><br>");
    $("#buttons").append("<button onclick='showmenu()' class='button' >Back</button><br>");
}

function accidentPOST() {
    $("#ajaxContent").load("damagedform.html");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='hasDamage()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'accident');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function repairmentPOST() {
    $("#ajaxContent").load("damagedform.html");
    $("#buttons").html("");
    $("#buttons").append("<button onclick='hasDamage()' class='button' >Back</button><br>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("Can't return the books");
        }
    };
    xhr.open('POST', 'repairment');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

function showmenu() {
    $("#buttons").html("");
    $("#op").html("Output");
    $("#ajaxContent").html("");
    $("#buttons").append("<h2>Select action</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"addVehile();\">Add Vehicle</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"seeAvailable();\">See Available Vehicles</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"hasDamage();\">Damaged Vehicle</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"questions();\">Questions</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"sqlinput();\">Submit SQL query</button>");
}
function rent(){
    $("#ajaxContent").load("register.html");
}
function rent1(){
    $("#ajaxContent").load("register1.html");
}
//STELIOS END 

document.addEventListener('DOMContentLoaded', function() {
    // Your function logic here
    console.log("Document is fully loaded");
    // Call any function you want to run on load
    initDB();
});

function sqlinput() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showmenu();\">Back</button>");
    $("#ajaxContent").load("sql.html");
}

function sqlPOST() {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("SQL failed");
        }
    };
    var data = $('#sqlForm').serialize();
    xhr.open('POST', 'SQL');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send(data);
}

function questions() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"showmenu();\">Back</button><br>");
    $("#ajaxContent").html("");
    $("#buttons").append("<button class=\"button\" onclick=\"seeAvailableq();\">Available vehicles</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"mmaPOST();\">Rents in time period</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"incform();\">Max/Min/Average Rent Duration</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"einfoform();\">Income from rent in time period</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"ptotalsPOST();\">Total Expences in time period</button><br>");
    $("#buttons").append("<button class=\"button\" onclick=\"ptotalsPOST();\">Most Popular vehicle</button><br>");
}
function seeAvailableq() {
    $("#ajaxContent").html("");
    $("#buttons").html("");
    $("#buttons").append("<h2>Select type of vehicle</h2>");
    $("#buttons").append("<button class=\"button\" onclick=\"avaGET();\">Cars</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"avmGET();\">Motorcycles</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"avbGET();\">Bikes</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"avsGET();\">Scooters</button>");
    $("#buttons").append("<button class=\"button\" onclick=\"showmenu();\">Back</button>");
}

function avaGET() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"seeAvailableq();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("PaymentLog failed");
        }
    };
    xhr.open('GET', 'ava');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function avmGET() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"seeAvailableq();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("PaymentLog failed");
        }
    };
    xhr.open('GET', 'avm');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function avbGET() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"seeAvailableq();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("PaymentLog failed");
        }
    };
    xhr.open('GET', 'avb');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}
function avsGET() {
    $("#buttons").html("<h2>Select action</h2> <button class=\"button\" onclick=\"seeAvailableq();\">Back</button>");
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            $("#op").html("");
            $("#ajaxContent").html(xhr.responseText);
        } else if (xhr.status !== 200) {
            $("#error").html("PaymentLog failed");
        }
    };
    xhr.open('GET', 'avs');
    xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    xhr.send();
}

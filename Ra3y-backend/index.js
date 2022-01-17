// We now import the connection object we exported in db.js.

//const fb = require("./firebase");
const db = require("./db");

const SHA256 = require("crypto-js/sha256");

//const FirebaseApp = fb.FirebaseApp;
//const FirebaseAuthentication = fb.FirebaseAuthentication;

// More libraries…
const express = require("express");
const bodyParser = require("body-parser");
const {
query
} = require("express");
const {
restart
} = require("nodemon");
const {
send
} = require("process");
const {
STATUS_CODES
} = require("http");

const router = express.Router();

router.use(bodyParser.json()); // Automatically parse all POSTs as JSON.
router.use(bodyParser.urlencoded({
extended: true
})); // Automatically parse URL parameters

// Authenticated request example:
// All you need to do is add this line first:


/* ********************** */
// API 1
// Insert 
/* ********************** */

// Then implement your API normally.

// registerowner HERE


/* ********************** */
// API 2 
// Insert sitter user to database
/* ********************** */
router.post("/registerSitter", function(req,res){

// """ This APIs inserts record of a user of type sitter into the database"""
// get the body message contents
let body = req.body;

let uid = body.uid;
let fname = body.fname;
let lname = body.lname;
let email= body.email;
let pass = body.pass;
// storing the has of the password
let hashpass = SHA256(pass);

let phonenumber = body.phonenumber;

let sqlQuery = "INSERT INTO `sitter` (`UID`, `fname`,`lname`,`email`,`pass`, `phonenumber`) VALUES('"+uid+"','"+fname+"', \
'"+lname+"','"+email+"', '"+hashpass+"', '"+phonenumber+"' );"

db.query(sqlQuery,function(err,result){
if(err){
console.log("Error+ '"+err+"'");
return res.send(err);
}
else{
console.log("Result", JSON.stringify(result));
try{
let data = {
"Status": "Success",
result
}
res.json(data);
}
catch(exception){
res.status(500).json(JSON.stringify(exception));
}
}

});
});

/* ********************** */
// API 3 
// Get All Sitter Users
/* ********************** */

router.get("/getallsitterusers", function (req, res) {

// """ This API gets all the sitterusers from the database """
sqlQuery = "SELECT * FROM sitter";

db.query(sqlQuery, function (err, result) {

if (err) {
res.send(err);
throw err;
} else {
console.log("Result", JSON.stringify(result));
try {
let data = {
"Status": "Success",
result
};
res.json(data);
} catch (exception) {
res.status(500).json(JSON.stringify(exception));
}
}
})

});

/* ********************** */
// API 4
// Get All owner Users
/* ********************** */

router.get("/getallownerusers", function (req, res) {

// """ This API gets all the owner users from the database """
sqlQuery = "SELECT * FROM owner";

db.query(sqlQuery, function (err, result) {

if (err) {
res.send(err);
throw err;
} else {
console.log("Result", JSON.stringify(result));
try {
let data = {
"Status": "Success",
result
};
res.json(data);
} catch (exception) {
res.status(500).json(JSON.stringify(exception));
}
}
})
});

/* ********************** */
// API 4
// Get All owner Users
/* ********************** */


// Export the created router
module.exports = router;


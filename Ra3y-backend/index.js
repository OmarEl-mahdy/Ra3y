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


/* ******** */
// API 1
// Insert owner into database
/* ******** */
router.post("/registerOwner", function (req, res) {

    // """ This APIs inserts record of a user of type sitter into the database"""
    // get the body message contents
    let body = req.body;

    let uid = body.uid;
    let fname = body.fname;
    let lname = body.lname;
    let email = body.email;
    let pass = body.pass;
    // storing the has of the password
    let hashpass = SHA256(pass);

    let phonenumber = body.phonenumber;

    let sqlQuery = "INSERT INTO `owner` (`UID`, `fname`,`lname`,`email`,`pass`, `phonenumber`) VALUES('" + uid + "','" + fname + "', \
    '" + lname + "','" + email + "', '" + hashpass + "', '" + phonenumber + "' );"

    db.query(sqlQuery, function (err, result) {
        if (err) {
            console.log("Error+ '" + err + "'");
            return res.send(err);
        } else {
            console.log("Result", JSON.stringify(result));
            try {
                let data = {
                    "Status": "Success",
                    result
                }
                res.json(data);
            } catch (exception) {
                res.status(500).json(JSON.stringify(exception));
            }
        }

    });
});



// Then implement your API normally.

// registerowner HERE


/* ******** */
// API 2 
// Insert sitter user to database
/* ******** */
router.post("/registerSitter", function (req, res) {

    // """ This APIs inserts record of a user of type sitter into the database"""
    // get the body message contents
    let body = req.body;

    let uid = body.uid;
    let fname = body.fname;
    let lname = body.lname;
    let email = body.email;
    let pass = body.pass;
    // storing the has of the password
    let hashpass = SHA256(pass);


    let phonenumber = body.phonenumber;
    let yearsOfExperience = body.yearsOfExperience;
    let priceperhour = body.priceperhour;

    let sqlQuery = "INSERT INTO `sitter` (`UID`, `fname`,`lname`,`email`,`pass`, `phonenumber`,`yearsOfExperience`,`priceperhour`) VALUES('" + uid + "','" + fname + "', \
'" + lname + "','" + email + "', '" + hashpass + "', '" + phonenumber + "', '"+yearsOfExperience+"', '"+priceperhour+"' );"

    db.query(sqlQuery, function (err, result) {
        if (err) {
            console.log("Error+ '" + err + "'");
            return res.send(err);
        } else {
            console.log("Result", JSON.stringify(result));
            try {
                let data = {
                    "Status": "Success",
                    result
                }
                res.json(data);
            } catch (exception) {
                res.status(500).json(JSON.stringify(exception));
            }
        }

    });
});

/* ******** */
// API 3 
// Get All Sitter Users
/* ******** */

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

/* ******** */
// API 4
// Get All owner Users
/* ******** */

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

/* ******** */
// API 5
// set record for the request in the database 
/* ******** */
router.post("/makeRequest", function (req, res) {


    //""" This API is responsible for creating record for request between owner and sitter """

    let body = req.body;

    let sitteruid = req.body.sitteruid;
    let owneruid = req.body.owneruid;
    let info = req.body.info;

    let sqlQuery = "INSERT INTO `ownersitterrequest`(`sitteruid`,`owneruid`,`info`) VALUES('" + sitteruid + "', '" + owneruid + "','" + info + "');"
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
    });





});




router.post("/getmakeRequest", function (req, res) {


    //""" This API is responsible for creating record for request between owner and sitter """

    let body = req.body;

    let sitteruid = req.body.sitteruid;
 		  
    let sqlQuery = "SELECT * FROM `ownersitterrequest` WHERE sitteruid = '"+sitteruid+"';"
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
    });





});


/* ******** */
// API 7
// get info of the owner 
/* ******** */

router.post("/getOwnerInfo",function(req,res){

    // """ A """

    let uid = req.body.uid;

    let sqlQuery = "SELECT * FROM owner WHERE UID = ?;"
    db.query(sqlQuery,[uid], function(err,result){
        if(err){
            res.send(err)
        }
        else{
            res.json(result);
        }

    });

});
/* ******** */
// API 8
// get info of the sitter 
/* ******** */

router.post("/getSitterInfo",function(req,res){

    // """ A """

    let uid = req.body.uid;

    let sqlQuery = "SELECT * FROM sitter WHERE UID = ?;"
    db.query(sqlQuery,[uid], function(err,result){
        if(err){
            res.send(err)
        }
        else{
  
            res.json(result);
        }

    });

});

/* **** */
// API 9
// post location of sitter
/* **** */
router.post("/addSitterLocation", function (req, res) {
    
    let uid = req.body.uid;
    let latitudes=req.body.latitudes;
    let longitudes=req.body.longitudes;
    var sqlQuery = "UPDATE sitter SET latitudes = ?, longitudes = ?  WHERE UID =?;";

    db.query(sqlQuery,[latitudes,longitudes,uid],function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
            return res.json(result);
        }
    }); 
    });
/* **** */
// API 10
// get location of sitters
/* **** */
router.get("/getLocation", function (req, res) {
   
   let uid = req.body.uid;
    let sql = "SELECT latitudes,longitudes FROM sitter WHERE UID = ?;";

    db.query(sql,[uid] ,function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
          
        
            // Your code here 
            // You can use res.json(result); to send all data as a response 
            return res.json(result);
        }
    });
});

/* **** */
// API 11
// add address info of sitters
/* **** */
router.post("/addaddress", function (req, res) {
   
   let uid = req.body.uid;
   let addr = req.body.addr
    let sql = "UPDATE sitter SET address = ? WHERE UID = ?";

    db.query(sql,[addr,uid],function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
          
        
            // Your code here 
            // You can use res.json(result); to send all data as a response 
            return res.json(result);
        }
    });
});


/* **** */
// API 12
// get address info of sitters
/* **** */
router.post("/getaddress", function (req, res) {
   
   let uid = req.body.uid;
   let sql = "SELECT address FROM sitter WHERE UID = ?;";
    db.query(sql,[uid],function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
          
        
            // Your code here 
            // You can use res.json(result); to send all data as a response 
            return res.json(result);
        }
    });
});

/* **** */
// API 13
// get nearest location
/* **** */
router.post("/getNearestSitters", function (req, res) {
   

   let longitude = req.body.longitude;
   let latitude = req.body.latitude;
   let sql = "SELECT sitter.uid, ( 6371 * acos( cos( radians( '" + latitude + "') ) * cos( radians( sitter.latitudes ) ) * cos( radians( sitter.longitudes ) - radians( '" + longitude + "') ) + sin( radians('" +latitude+"') ) * sin( radians( sitter.latitudes ) ) ) ) AS distance FROM sitter HAVING distance < 30 ORDER BY distance LIMIT 0 , 20;"

    db.query(sql,function (err, result) {
        console.log("Result: " + JSON.stringify(result));
        if (err) {
            return res.send(err);
        } else {
          
        
            // Your code here 
            // You can use res.json(result); to send all data as a response 
            return res.json(result);
        }
    });
});






// Export the created router
module.exports = router;
